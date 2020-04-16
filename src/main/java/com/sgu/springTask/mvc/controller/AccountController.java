package com.sgu.springTask.mvc.controller;

import com.sgu.springTask.mvc.model.Account;
import com.sgu.springTask.mvc.model.IncreaseOperation;
import com.sgu.springTask.mvc.model.Operation;
import com.sgu.springTask.mvc.model.TransactOperation;
import com.sgu.springTask.mvc.model.User;
import com.sgu.springTask.service.AccountService;
import com.sgu.springTask.service.OperationService;
import com.sgu.springTask.service.UserService;
import com.sgu.springTask.service.ValutaService;
import com.sgu.springTask.web.exceptions.AmountException;
import com.sgu.springTask.web.exceptions.UserPhoneExeption;
import com.sgu.springTask.web.exceptions.ValutaExistsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.List;

import static com.sgu.springTask.constant.UrlConstant.CREATE_ACCOUNT;
import static com.sgu.springTask.constant.UrlConstant.GET_ALL_ACCOUNTS;
import static com.sgu.springTask.constant.UrlConstant.INCREASE_AMOUNT;
import static com.sgu.springTask.constant.UrlConstant.TRANSACT;

@Controller
@RequestMapping("accounts")
public class AccountController {

    private UserService userService;
    private AccountService accountService;
    private ValutaService valutaService;
    private OperationService operationService;

    public AccountController(UserService userService, AccountService accountService,
                             ValutaService valutaService, OperationService operationService) {
        this.userService = userService;
        this.accountService = accountService;
        this.valutaService = valutaService;
        this.operationService = operationService;
    }

    @GetMapping(value = GET_ALL_ACCOUNTS)
    public @ResponseBody
    List<Account> getAllAccounts() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        User user = userService.getByLogin(username);

        return accountService.getAllUserAccounts(user.getId());
    }

    @PostMapping(CREATE_ACCOUNT)
    public @ResponseBody
    Boolean createAccount(@RequestBody String accCode) throws ValidationException {
        if (!valutaService.isValuta(accCode)) {
            throw new ValutaExistsException();
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getByLogin(username);

        Account account = new Account(user.getId(), accCode.toLowerCase());
        accountService.create(account);
        return true;
    }

    @PutMapping(INCREASE_AMOUNT)
    public @ResponseBody
    Boolean increaseAmount(@RequestBody IncreaseOperation increaseOperation) {

        if (!valutaService.isValuta(increaseOperation.getAccCode())) {
            throw new ValutaExistsException();
        }

        Double newAmount = MoneyConverter.convert(increaseOperation.getAmount(),
                increaseOperation.getAccCode(),
                accountService.getValutaById(increaseOperation.getAccountId()));

        accountService.increaseAmount(increaseOperation.getAccountId(), newAmount);
        return true;
    }

    @PutMapping(TRANSACT)
    public @ResponseBody
    Boolean transactAmount(@RequestBody TransactOperation transactOperation) {
        transactOperation.setAccCode(transactOperation.getAccCode().toLowerCase());
        Account account = accountService.getAccount(transactOperation.getAccountId());
        Long accountIdTo =
                accountService.getAccountIdByUserPhoneAndAccCode(transactOperation.getPhone(),
                        transactOperation.getAccCode());
        if (accountIdTo == null) {
            throw new UserPhoneExeption();
        }
        if (!valutaService.isValuta(transactOperation.getAccCode())) {
            throw new ValutaExistsException();
        }
        Double accountAmount = account.getAmount();
        if (accountAmount < transactOperation.getAmount()) {
            throw new AmountException();
        }

        double newAmount =
                MoneyConverter.convert(transactOperation.getAmount(), account.getAccCode(), transactOperation.getAccCode());

        if (accountService.increaseAmount(transactOperation.getAccountId(), -newAmount)
                && accountService.increaseAmount(accountIdTo, transactOperation.getAmount())) {
            operationService.createOperation(new Operation(new Date(), transactOperation.getAccCode(),
                    account.getId(), accountIdTo, newAmount, accountAmount, accountAmount - newAmount));
            return true;
        }
        return false;
    }
}
