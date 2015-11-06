/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.dao;

import com.directmessagebot.entity.AccountManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Mendon Ashwini All the methods from interface AccountManagerDao are implemented in this class
 */
public class AccountManagerDaoImpl implements AccountManagerDao {

    private SimpleJdbcInsert accountManagerInsert;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String site, Integer age) {
        String SQL = "insert into launch_data (site, product) values (?, ?)";

        jdbcTemplateObject.update(SQL, site, "testing");
        System.out.println("Created Record Name = " + site + " Age = " + age);

    }

    @Override
    public AccountManager getAccountManager(Integer id) {
        String SQL = "select * from launch_data where id = ?";
        AccountManager student = (AccountManager) jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new BeanPropertyRowMapper(AccountManager.class));
        return student;
    }

    @Override
    public List<AccountManager> listAccountManager() {
        String SQL = "select * from account_manager";
        List<AccountManager> listAccountManager = null;
        try {
            listAccountManager = jdbcTemplateObject.query(SQL,
                    new BeanPropertyRowMapper(AccountManager.class));
        } catch (DataAccessException dataAccessException) {
        }
        return listAccountManager;
    }

    @Override
    public void delete(Integer id) {
        String SQL = "delete from Student where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
        return;
    }

    @Override
    public void update(Integer id, Integer age) {
        String SQL = "update Student set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, age, id);
        System.out.println("Updated Record with ID = " + id);
        return;
    }

    @Override
    public int insertAccountManager(AccountManager objAccountManager) {
        int insertstatus=0;
        String SQL = "insert into account_manager (USERNAME, PASSWORD, PROXY_IP, PROXY_PORT, PROXY_USERNAME, PROXY_PASSWORD, LOGIN_STATUS) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplateObject.update(SQL, objAccountManager.getUsername(), objAccountManager.getPassword(), objAccountManager.getProxyIp(), objAccountManager.getProxyPort(), objAccountManager.getProxyUsername(), objAccountManager.getProxyPassword(), 0 );
            System.out.println("LaunchData inserted");
            insertstatus=1;
        } catch (DataAccessException dataAccessException) {
           
        }
        return insertstatus;

    }
    
    //insert batch example
public void insertBatch(final List<AccountManager> listAccountManager){
		
  String sql = "insert into account_manager (USERNAME, PASSWORD, PROXY_IP, PROXY_PORT, PROXY_USERNAME, PROXY_PASSWORD, LOGIN_STATUS) values (?, ?, ?, ?, ?, ?, ?)";
			
  jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		AccountManager objAccountManager = listAccountManager.get(i);        
        ps.setString(1, objAccountManager.getUsername());
        ps.setString(2, objAccountManager.getPassword());
        ps.setString(3, objAccountManager.getProxyIp());
        ps.setString(4, objAccountManager.getProxyPort());
        ps.setString(5, objAccountManager.getProxyUsername());
        ps.setString(6, objAccountManager.getProxyPassword());
        ps.setInt(7, 0);
	}
			
	@Override
	public int getBatchSize() {
		return listAccountManager.size();
	}


  });
}


    @Override
    public int deleteAll() {
        String SQL = "delete from account_manager";
        int val = jdbcTemplateObject.update(SQL);
        return val;
    }

    @Override
    public List<AccountManager> listAccountManager(Date selectedDate) {
        String launchDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
        String SQL = "select * from launch_data where LAUNCH_DATE = ?";
        List<AccountManager> listLaunchData = jdbcTemplateObject.query(SQL, new Object[]{launchDate},
                new BeanPropertyRowMapper(AccountManager.class));
        return listLaunchData;
    }

    @Override
    public void createTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS `account_manager` (\n"
                    + "  `ID` integer primary key autoincrement,\n"
                    + "  `USERNAME` varchar(100) NOT NULL,\n"
                    + "  `PASSWORD` varchar(100) NOT NULL,\n"
                    + "  `PROXY_IP` varchar(30) DEFAULT NULL,\n"
                    + "  `PROXY_PORT` varchar(10) DEFAULT NULL,\n"
                    + "  `PROXY_USERNAME` varchar(100) DEFAULT NULL,\n"
                    + "  `PROXY_PASSWORD` varchar(100) DEFAULT NULL,\n"
                    + "  `LOGIN_STATUS` int(11) NOT NULL DEFAULT '0')";
            jdbcTemplateObject.execute(sql);
            System.out.println("table created");

        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
    }
}
