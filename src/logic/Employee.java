/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.text.*;
import java.util.Date;

/**
 * 
 * @author Rostislav Stakhov <rst1991@ukr.net>
 */


//Класс, представляющий объект типа Employee - все работники
public class Employee {
        
    
    private Long id;//Номер объекта в БД
    private String obj_name;//Название объекта в БД
    private Integer emp_num;//Номер служащего
    private String emp_name;//Имя служашего
    private String emp_job;//Название должности служащего
    private Date hire_date;//Дата найма на работу
    private Double salary;//Значение заработной платы
    private Long parent_object_id;//ID родительского объекта (офис, в котором он работает)
    
    //Конструктор без параметров
    public Employee(){
       
    }
    
    //Конструктор с параметрами
    public Employee(String obj_name, int emp_num, String emp_name, String emp_job, String hire_date, Double salary, Long parent_object_id){
       
        try{
            this.obj_name=obj_name;
            this.emp_num=emp_num;
            this.emp_name=emp_name;
            this.emp_job=emp_job;
            this.hire_date=new SimpleDateFormat("dd.mm.yyyy").parse(hire_date);
            this.salary=salary;
            this.parent_object_id=parent_object_id;
        }
        catch(ParseException pe){
              pe.printStackTrace();
        }
       
    }
    
    //Гетеры - сетеры на скрытые поля
    
    //Номер объекта
    public Long getObjectId(){
        return id;
    }
    
    public void setObjectId(Long id){
        
        this.id=id;
    }
    
    //Имя объекта
    public String getObjName(){
        
        return obj_name;
    }
    
    public void setObjName(String obj_name){
        
        this.obj_name=obj_name;
    }
    
    //Номер служащего
    public int getEmpNum() {
        return emp_num;
    }

    public void setEmpNum(int emp_num) { 
        this.emp_num = emp_num;  
    }
    
    //Имя служащего
    public String getEmpName() {
        return emp_name;
    }

    public void setEmpName(String emp_name) { 
        this.emp_name = emp_name;  
    }
    
    //Должность служащего
    public String getEmpJob() {
        return emp_job;
    }

    public void setEmpJob(String emp_job) { 
        this.emp_job = emp_job;  
    }
    
    //Дата найма служащего
    public Date getEmpHireDate() {
        return hire_date;
    }

    public void setEmpHireDate(Date hire_date) { 
        this.hire_date = hire_date;  
    }
    
    //Зарплата служащего
    public Double getEmpSalary(){
        return salary;
    }
    
    public void setEmpSalary(Double salary){
        this.salary=salary;
    }
    
    //Номер род объекта служащего (офиса)
    public Long getParObjId() {
        return parent_object_id;
    }

    public void setParObjId(Long parent_object_id) { 
        this.parent_object_id = parent_object_id;  
    }

   
}
