package com.sgu.springTask.constant;

public interface UrlConstant {
    String SING_IN = "/users/signin";
    String SING_UP = "/users/signup";
    String GET_ALL_USERS = "/users/info/all";

    String GET_ALL_ACCOUNTS = "accounts/getAllAccounts";
    String CREATE_ACCOUNT = "accounts/createAccount";
    String INCREASE_AMOUNT = "accounts/increase";
    String TRANSACT = "accounts/transact";

    String GET_ALL_OPERATIONS_BY_USER = "/operations/getAllByUser";
}
