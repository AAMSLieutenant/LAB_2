/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracledb;


import Oracle.*;
import logic.Office;
import logic.Employee;
import interfaces.*;


import java.util.*;
import java.sql.*;
import java.math.BigInteger;



import java.util.Locale;
import java.sql.Date;





public class OracleDB {

    public static final String ADMIN_ROLE="admin";//Администратор - можно все
    public static final String MANAGER_ROLE="manager";//Менеджер - можно считывать все и перезаписывать почти все
    public static final String WORKER_ROLE="worker";//Рядовой служащий - возможно только чтение
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Locale.setDefault(Locale.US);
        try{
            
            //Получение объекта связи с БД
            OracleDaoFactory df=new OracleDaoFactory();
            //Получение объекта для работы с персистентным состоянием Office
            OfficeDaoble of=df.getOfficeDao();
            
            //Установка роли пользователя (администратор, менеджер, рядовой сотрудник)
            //of.setRole(ADMIN_ROLE);
            //of.setRole(MANAGER_ROLE);
            //of.setRole(WORKER_ROLE);
            
            //Методы работы с данными Office
            //of.create(new Office("NetBeans", 66, "NetBeansLocation"));
            //of.read(id);
            //of.update(id, new Office("WashingtonOffice", 100, "KisusyIEV"));
            //of.delete(id);
            //of.getAll();
            //of.quit();
            
            
            
            //Получение объекта для работы с персистентным состоянием Office          
            EmployeeDaoble em=df.getEmployeeDao();
            
            //Установка роли пользователя (администратор, менеджер, рядовой сотрудник)
            //em.setRole(ADMIN_ROLE);
            //em.setRole(MANAGER_ROLE);
            //em.setRole(WORKER_ROLE);
            
            //Методы работы с данными Employee
            //em.create(new Employee("MARK", 13, "Markus", "Developer", "12.12.2013", 332222d, null));
            //em.read(id);
            //em.update(id, new Employee("Maks", 1111, "Maksim", "Manager", "11.11.2001", 5555d, null));
            //em.delete(id);
            //em.getAll();
            //of.quit();
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
        
}
