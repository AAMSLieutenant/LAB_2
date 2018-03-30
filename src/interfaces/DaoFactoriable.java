package interfaces;

import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Rostislav Stakhov <rst1991@ukr.net>
 */


/** 
 * Фабрика объектов для работы с базой данных 
 */
public interface DaoFactoriable 
{

    /** 
     * Возвращает подключение к базе данных 
     * @return объект подключения к БД
     * @throws java.sql.SQLException 
     */
    public Connection getConnection() throws SQLException;

    /** 
     * Возвращает объект для управления персистентным состоянием объекта Employee 
     * @return Объект подключения для Employee
     * @throws java.lang.Exception 
     */
    public EmployeeDaoble getEmployeeDao() throws Exception;

    /** 
     * Возвращает объект для управления персистентным состоянием объекта Office 
     * @return Объект подключения для Employee
     * @throws java.lang.Exception 
     */
    public OfficeDaoble getOfficeDao() throws Exception;
}


