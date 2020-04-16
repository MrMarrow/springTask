package com.sgu.springTask.service;

import com.sgu.springTask.mvc.model.Account;
import com.sgu.springTask.mvc.model.Operation;
import com.sgu.springTask.repositiry.AccountRepository;
import com.sgu.springTask.repositiry.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationService {

    @Autowired
    OperationRepository operationRepository;
    @Autowired
    AccountRepository accountRepository;

    public void createOperation(Operation operation) {
        operationRepository.save(operation);
    }

    public List<Operation> getAllOperations(Long userId) {
        List<Account> accountList = accountRepository.findByClientId(userId);

        List<Operation> operationList = new ArrayList<>();
        for (Account account : accountList) {
            operationList.addAll(operationRepository.findAllByIdAccountFrom(account.getId()));
            operationList.addAll(operationRepository.findAllByIdAccountTo(account.getId()));
        }
        return operationList;
    }
}
