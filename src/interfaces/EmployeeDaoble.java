/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import java.sql.SQLException;
import java.util.List;
import logic.Employee;

/**
 *
 * @author Rostislav Stakhov <rst1991@ukr.net>
 */


/**
 * Интерфейс для управления персистентным состоянием объекта Employee
 * @author Rostislav Stakhov <rst1991@ukr.net>
 */
public interface EmployeeDaoble {
    
    /**
     * Создает новую запись и соответствующий ей объект 
     * @param employee
     * @throws Exception 
     */
    public void create(Employee employee) throws Exception;

    /**
     * Возвращает объект типа Employee соответствующий записи с первичным ключом key или null 
     * @param key - номер объекта в БД
     * @return объект типа Employee
     * @throws Exception 
     */
    public Employee read(long key) throws Exception;
    
    /**
     * Сохраняет состояние объекта Employee в базе данных 
     * @param key - номер объекта в БД
     * @param employee - объект типа Employee
     * @throws Exception 
     */
    public void update(long key, Employee employee) throws Exception;
    
    /**
     * Удаляет запись об объекте из базы данных 
     * @param key - номер объекта в БД
     * @throws Exception
     */
    public void delete(long key) throws Exception;

    /**
     * Возвращает список объектов соответствующих всем записям в БД 
     * @return список объектов типа Employee - объект типа List
     * @throws Exception 
     */
    public List<Employee> getAll() throws Exception;
    
    /**
     * Закрытие соединения с БД
     */
    public void quit();
    
    /**
     * Возвращает роль текущего пользователя базы данных (администратор, менеджер, рядовой сотрудник)
     * @return роль текущего пользователя (адинистратор, менеджер, рядовой сотрудник)
     */
    public String getRole();
    
    /**
     * Задает роль пользователя в базе данных (администратор, менеджер, рядовой сотрудник)
     * @param role - роль текущего пользователя (адинистратор, менеджер, рядовой сотрудник)
     */ 
    public void setRole(String role);
    
    /** Проверяет, может ли сотрудник с текущей ролью выполнять текущий запрос
     * @param role - роль текущего пользователя (администратор, менеджер, рядовой сотрудник)
     * @param mode - вид запроса (чтение - запись)
     * @param type - тип объекта, с которым проводятся манипуляции
     * @return целочисленное значение: 
     * 1 - действия разрешены для конкретного объекта в текущей роли
     * -1 - действия запрещены для конкретного объекта в текущей роли
     */
    public int consider(String role, char mode, int type);
    
}
