package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;


public class DemoApp {
    public static void main(String[] args) {
        var accounts = Accounts.generateAccountList(10);
        var maxBalanceAccount = max(accounts, comparing(Account::getBalance));
        System.out.println(maxBalanceAccount);
    }
    
    // todo: DISABLE AI Assistant
    // todo: implement a custom method comparing
    // todo: implement a custom method max
    // todo: post a screenshot of your code to zoom chat
}
