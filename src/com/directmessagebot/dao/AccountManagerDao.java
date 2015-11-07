/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.directmessagebot.dao;

import com.directmessagebot.entity.AccountManager;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Mendon Ashwini
 * Interface where all the database operation methods are declared 
 */
public interface AccountManagerDao {
   /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
     * @param ds
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
     * @param id
     * @return 
    */
   public AccountManager getAccountManager(Integer id);
   /** 
    * This is the method to be used to list down
    * all the records from the Student table.
     * @return 
    */
   public List<AccountManager> listAccountManager();
   /** 
    * This is the method to be used to delete
    * a record from the Student table corresponding
    * to a passed student id.
     * @param id
    */
   public void delete(Integer id);   
   
   public int insertAccountManager(AccountManager objLaunchData);
   
   /*
   *insert the list of AccountManager using spring batch update
   */
   public void insertBatch(final List<AccountManager> listAccountManager);
   
   public int deleteAll();
   
   
   public void createTable();

}