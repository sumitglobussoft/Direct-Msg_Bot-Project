/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.thread;

import com.directmessagebot.crawler.InstagramCrawler;
import com.directmessagebot.dao.AccountManagerDao;
import com.directmessagebot.entity.AccountManager;
import com.directmessagebot.form.UsernameMessageForm;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author GLB-130
 */
public class AccountLoginThread implements Callable<String> {

    AccountManager objAccountManager = null;
    List<UsernameMessageForm> listUsernameMessageForm = null;
    AccountManagerDao objAccountManagerDao = null;

    public AccountLoginThread(AccountManager objAccountManager, List<UsernameMessageForm> listUsernameMessageForm, AccountManagerDao objAccountManagerDao) {

        this.objAccountManager = objAccountManager;
        this.listUsernameMessageForm = listUsernameMessageForm;
        this.objAccountManagerDao = objAccountManagerDao;
    }

    @Override
    public String call() throws Exception {
        long messageSent = 0;
        
        InstagramCrawler objInstagramCrawler = new InstagramCrawler();

        int loggedInStatus = objInstagramCrawler.LoginbyInconosquare(objAccountManager);
        //Each message will go for selected user
        for (UsernameMessageForm usernameMessage : listUsernameMessageForm) {
            
            Thread.sleep(40000);

            if (loggedInStatus == 1) {
                messageSent = objInstagramCrawler.sendMessagebyInconosquare(usernameMessage.getUsername(), usernameMessage.getMessage(), objAccountManager);
            }
            if (messageSent == 1) {
                System.out.println("Message Sent");
            } else {
                System.out.println("userID" + messageSent);
                System.out.println("Message Not sent");
                objInstagramCrawler.followUserSendMessageUnfollowbyInconosquare(usernameMessage.getUsername(), usernameMessage.getMessage(), objAccountManager);
            }
        }

        return "done";

    }

}
