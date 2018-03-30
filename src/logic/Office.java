/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.Date;

/**
 * 
 * @author Rostislav Stakhov <rst1991@ukr.net>
 */

//Класс, представляющий объект типа Office - офис 
public class Office {

    private Long id;//Номер объекта в БД
    private String obj_name;//Название объекта в БД
    private Integer emp_count;//Кол - во работников в одфисе
    private String location;//Местонахождение офиса (город)
    
    //Консруктор без параметров
    public Office(){
        
    }
    
    //Конструктор с параметрами
    public Office(String obj_name, int emp_count, String location){
        
        this.obj_name=obj_name;
        this.emp_count=emp_count;
        this.location=location;
        
    }
    
    //Гетеры - сетеры для скрытых полей
    
    //Номер объекта в БД
    public Long getObjId(){
        return id;
    }
    
    public void setObjId(Long id){
        this.id=id;
    }
    
    //Имя объекта в БД
    public String getObjName(){
        return obj_name;
    }

    public void setObjName(String obj_name){
        this.obj_name = obj_name;
    }
    
    //Кол - во сотрудников в офисе
    public Integer getEmpCount(){
        return emp_count;
    }

    public void setEmpCount(int emp_count){
        this.emp_count = emp_count;
    }

    //Местонахождение офиса
    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

}