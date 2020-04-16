package com.sgu.springTask.service;

import com.sgu.springTask.mvc.model.Account;
import com.sgu.springTask.mvc.model.User;
import com.sgu.springTask.repositiry.AccountRepository;
import com.sgu.springTask.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public List<Account> getAllUserAccounts(Long userId) {
        return accountRepository.findByClientId(userId);
    }

    public void create(Account account) {
        accountRepository.save(account);
    }


    public String getValutaById(Long accountId) {
        return accountRepository.findById(accountId).get().getAccCode();
    }

    public boolean increaseAmount(Long accountId, Double newAmount) {
        Account account = accountRepository.findById(accountId).get();
        account.setAmount(account.getAmount() + newAmount);
        accountRepository.save(account);
        return true;
    }

    public Long getAccountIdByUserPhoneAndAccCode(String phone, String accCode) {
        User user = userRepository.findByPhone(phone);
        Account account = accountRepository.findByClientIdAndAccCode(user.getId(), accCode);
        return account.getId();
    }

    public Double getAccountAmount(Long accountId) {
        Account account = accountRepository.findById(accountId).get();
        return account.getAmount();
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).get();
    }
}
