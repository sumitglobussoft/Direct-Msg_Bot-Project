/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.thread;

import com.directmessagebot.dao.AccountManagerDao;
import com.directmessagebot.entity.AccountManager;
import com.directmessagebot.form.UsernameMessageForm;
import static com.directmessagebot.ui.DirectMessagePage.StarrButton;
import static com.directmessagebot.ui.DirectMessagePage.importButton;
import static com.directmessagebot.ui.DirectMessagePage.logger2textArea;
import static com.directmessagebot.ui.DirectMessagePage.messageTextArea;
import static com.directmessagebot.ui.DirectMessagePage.usernameTextField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author GLB-130
 */
public class GetAllAccountsThread implements Callable<String> {

    List<UsernameMessageForm> objUsernameMessageForm = null;
    AccountManagerDao objAccountManagerDao = null;

    public GetAllAccountsThread(List<UsernameMessageForm> objUsernameMessageForm, AccountManagerDao objAccountManagerDao) {

        this.objUsernameMessageForm = objUsernameMessageForm;
        this.objAccountManagerDao = objAccountManagerDao;
    }

    @Override
    public String call() throws Exception {

        //get all the accounts
        List<AccountManager> listAccount = objAccountManagerDao.listAccountManager();

        /////
        List<Future<String>> list = new ArrayList<Future<String>>();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (AccountManager list1 : listAccount) {
            {
                try {
                    Callable worker = new AccountLoginThread(list1, objUsernameMessageForm, objAccountManagerDao);
                    Future<String> future = executor.submit(worker);
                    list.add(future);
                } catch (Exception exx) {
                    System.out.println(exx);
                }

            }
        }
        for (Future<String> fut : list) {
            try {
                    //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException ep) {
                ep.printStackTrace();
            }
        }
        //shut down the executor service now
        executor.shutdown();

        logger2textArea.append("\n\n==========================================");
        logger2textArea.append("\nDone Sending message to the selected Uers\n");
        usernameTextField.setText("");
            messageTextArea.setText("");
        StarrButton.setEnabled(true);
            importButton.setEnabled(true);
            usernameTextField.setEnabled(true);
            messageTextArea.setEnabled(true);
        return "done";

    }
}
