/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Oracle;


import interfaces.DaoFactoriable;
import interfaces.EmployeeDaoble;
import interfaces.OfficeDaoble;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;


/**
 * 
 * @author Rostislav Stakhov <rst1991@ukr.net>
 */
public class OracleDaoFactory implements DaoFactoriable{
    
    private String user = "admin";//Логин пользователя
    private String password = "admin";//Пароль пользователя
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";//URL адрес
    private String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера
    
    
    public OracleDaoFactory() 
    {
        try 
        {
            System.out.println("-------- Oracle JDBC Connection Testing ------");
            Class.forName(driver);
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        }
        System.out.println("Oracle JDBC Driver Registered");   
    }
    
    @Override
    public Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public EmployeeDaoble getEmployeeDao() throws Exception
    {
        return new EmployeeDao(DriverManager.getConnection(url, user, password)); 
    }

    @Override
    public OfficeDaoble getOfficeDao() throws Exception
    {
        return new OfficeDao(DriverManager.getConnection(url, user, password));
    }

}
