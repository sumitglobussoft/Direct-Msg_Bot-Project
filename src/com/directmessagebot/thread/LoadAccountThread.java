/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.thread;

import com.directmessagebot.dao.AccountManagerDao;
import com.directmessagebot.entity.AccountManager;
import static com.directmessagebot.ui.MainPage.loggerTextArea;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author GLB-130
 */
public class LoadAccountThread implements Callable<String> {
    
    List<AccountManager> listAccountManager=null;
    AccountManagerDao objAccountManagerDao = null;

    public LoadAccountThread(List<AccountManager> listAccountManager, AccountManagerDao objAccountManagerDao) {
        this.listAccountManager=listAccountManager;
        this.objAccountManagerDao = objAccountManagerDao;
    }
    
    

    @Override
    public String call(){
        
        objAccountManagerDao.insertBatch(listAccountManager);
        loggerTextArea.append("\nDone Loading All the accounts");
        
        
       return "done";
    }
    
}
