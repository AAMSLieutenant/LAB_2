/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Oracle;

import interfaces.EmployeeDaoble;
import java.util.List;
import logic.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Rostislav Stakhov <rst1991@ukr.net>
 */

public class EmployeeDao implements EmployeeDaoble {
    
    private final Connection connection;//Объект соединения с БД
    private String role;//Текущая роль пользователя
    
    public EmployeeDao(Connection connection){
        this.connection = connection;
    }
    
    
    @Override
    public void create(Employee employee) throws Exception{
        
        if((consider(getRole(), 'w', 15))==1){
        
        
            System.out.println("Employee create()");
            

            int count=0;
            String statement="SELECT COUNT(*) FROM Objects";
            PreparedStatement ps=connection.prepareStatement(statement);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                count=rs.getInt(1);
            }
            System.out.println("Number of objects: "+count);
            count++;


            ps=connection.prepareStatement("INSERT INTO Objects (object_id, object_name, type_id, parent_object_id) "
            + "VALUES ((select to_number('3" + count + "' || (to_char(sysdate, 'DDMMYYHH24MI')))from dual), ?, ?, ?) ");

            ps.setString(1, employee.getEmpName());
            ps.setInt(2, 15);
            ps.setObject(3, null);
            
            if(employee.getParObjId()!=null){
                ps.setLong(3, employee.getParObjId());
            }
          
            ps.executeUpdate();
            connection.commit();

            statement="INSERT INTO PARAMETERS (attr_id, object_id, number_info) "
                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
            ps=connection.prepareStatement(statement);
            ps.setInt(1, 21);
            ps.setString(2, employee.getEmpName());
            ps.setInt(3, employee.getEmpNum());
            ps.executeUpdate();
            connection.commit();

            statement="INSERT INTO PARAMETERS (attr_id, object_id, text_info) "
                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
            ps=connection.prepareStatement(statement);
            ps.setInt(1, 22);
            ps.setString(2, employee.getEmpName());
            ps.setString(3, employee.getEmpName());
            ps.executeUpdate();
            connection.commit();

            statement="INSERT INTO PARAMETERS (attr_id, object_id, text_info) "
                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
            ps=connection.prepareStatement(statement);
            ps.setInt(1, 23);
            ps.setString(2, employee.getEmpName());
            ps.setString(3, employee.getEmpJob());
            ps.executeUpdate();
            connection.commit();

            java.sql.Date d=new java.sql.Date(employee.getEmpHireDate().getTime());
            System.out.println(d);
            statement="INSERT INTO PARAMETERS (attr_id, object_id, date_info) "
                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
            ps=connection.prepareStatement(statement);
            ps.setInt(1, 24);
            ps.setString(2, employee.getEmpName());
            ps.setDate(3, d);
            ps.executeUpdate();
            connection.commit();

            statement="INSERT INTO PARAMETERS (attr_id, object_id, number_info) "
                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
            ps=connection.prepareStatement(statement);
            ps.setInt(1, 25);
            ps.setString(2, employee.getEmpName());
            ps.setDouble(3, employee.getEmpSalary());
            ps.executeUpdate();
            connection.commit();

              try{
                  ps.close();
              }
              catch(SQLException e){
                  e.printStackTrace();
              }
          
        }
        
    }

    @Override
    public Employee read(long key) throws Exception{
        
        if((consider(getRole(), 'r', 15))==-1){
            return null;
        }
        
        System.out.println("Employee read()");
        
                
        
        String statement="select o.object_id, o.object_name, p.text_info, p.number_info, p.date_info, o.parent_object_id, a.attr_id"
                          +" from objects o"
                          +" inner join attributes a on a.TYPE_ID=15"
                          +" inner join parameters p on p.object_id = o.object_id"
                          +" and a.ATTR_ID=p.ATTR_ID"
                          +" and o.OBJECT_ID=?"
                          + "order by OBJECT_ID, ATTR_ID";

        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setLong(1, key);
        ResultSet rs=ps.executeQuery();
        Employee e=new Employee();
        while(rs.next()){
                                   
            e.setObjectId(rs.getLong("object_id"));
           
            e.setObjName(rs.getString("object_name"));
            
            e.setParObjId(rs.getLong("parent_object_id"));
            
            if(rs.getInt("attr_id")==21) e.setEmpNum(rs.getInt("number_info"));
            
            if(rs.getInt("attr_id")==22) e.setEmpName(rs.getString("text_info"));
            
            if(rs.getInt("attr_id")==23) e.setEmpJob(rs.getString("text_info"));
            
            if(rs.getInt("attr_id")==24) e.setEmpHireDate(rs.getDate("date_info"));
            
            if(rs.getInt("attr_id")==25) e.setEmpSalary(rs.getDouble("number_info"));
            
            
        }
        System.out.println("id:"+e.getObjectId());
        System.out.println("number:"+e.getEmpNum());
        System.out.println("name:"+e.getEmpName());
        System.out.println("job:"+e.getEmpJob());
        System.out.println("hiredate:"+e.getEmpHireDate());
        System.out.println("salary:"+e.getEmpSalary());


        System.out.println("------------------------------");
        
        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return e;
        
    }

    
    @Override
    public void update(long key, Employee employee) throws Exception{
        
        if((consider(getRole(), 'w', 15))==1){
        
            System.out.println("Employee update()");

            boolean flag=false;
            String statement="SELECT object_id FROM Objects";
            PreparedStatement ps=connection.prepareStatement(statement);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getLong(1)==key){
                    flag=true;
                    System.out.println("MATCH: "+rs.getLong(1));
                    break;
                }
            }

            if(flag==true){

                System.out.println("Update started");
                System.out.println(employee.getObjName());
                statement="UPDATE objects SET object_name=?, parent_object_id=? WHERE object_id=?";

                ps=connection.prepareStatement(statement);
                ps.setString(1, employee.getObjName());
                ps.setObject(2, null);
                if(employee.getParObjId()!=null){
                    ps.setLong(2, employee.getParObjId());
                }
                ps.setLong(3, key);
                ps.executeUpdate();
                connection.commit();

                statement="UPDATE parameters"
                        + " SET number_info=?"
                        + " WHERE object_id=?"
                        + " AND attr_id=21";
                ps=connection.prepareStatement(statement);
                ps.setInt(1, employee.getEmpNum());
                ps.setLong(2, key);
                ps.executeUpdate();
                connection.commit();
           
                statement="UPDATE parameters"
                        + " SET text_info=?"
                        + " WHERE object_id=?"
                        + " AND attr_id=22";
                ps=connection.prepareStatement(statement);
                ps.setString(1, employee.getEmpName());
                ps.setLong(2, key);
                ps.executeUpdate();
                connection.commit();
             
                statement="UPDATE parameters"
                        + " SET text_info=?"
                        + " WHERE object_id=?"
                        + " AND attr_id=23";
                ps=connection.prepareStatement(statement);
                ps.setString(1, employee.getEmpJob());
                ps.setLong(2, key);
                ps.executeUpdate();
                connection.commit();
            
                statement="UPDATE parameters"
                        + " SET date_info=?"
                        + " WHERE object_id=?"
                        + " AND attr_id=24";
                ps=connection.prepareStatement(statement);

                java.sql.Date d=new java.sql.Date(employee.getEmpHireDate().getTime());
                ps.setDate(1, d);
                ps.setLong(2, key);
                ps.executeUpdate();
                connection.commit();
                
                statement="UPDATE parameters"
                        + " SET number_info=?"
                        + " WHERE object_id=?"
                        + " AND attr_id=25";
                ps=connection.prepareStatement(statement);
                ps.setDouble(1, employee.getEmpSalary());
                ps.setLong(2, key);
                ps.executeUpdate();
                connection.commit();       

            }

            try{
                ps.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        
    }

    
    @Override
    public void delete(long key) throws Exception{
        
        if((consider(getRole(), 'w', 15))==1){
        
        
            System.out.println("Employee delete()");


            boolean flag=false;
            String statement="SELECT object_id FROM Objects";
            PreparedStatement ps=connection.prepareStatement(statement);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                if(rs.getLong(1)==key){
                    flag=true;
                    System.out.println("MATCH: "+rs.getLong(1));
                    break;
                }
            }

            if(flag==true){

                ps=connection.prepareStatement("DELETE FROM Parameters WHERE object_id=?");
                ps.setLong(1, key);
                ps.executeUpdate();


                ps=connection.prepareStatement("DELETE FROM Objects WHERE object_id=?");
                ps.setLong(1, key);
                ps.executeUpdate();

                connection.commit();

            }
            else{
                System.out.println("WRONG OBJECT ID");
            }


            try{
                ps.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        
        }
    
        
    }

    
    @Override
    public List<Employee> getAll() throws Exception{
        
        
        if((consider(getRole(), 'r', 15))==-1){
            
            return null;
        }
        
        
        List<Employee> objects=new ArrayList<>();
        List<Long> arr=new ArrayList<>();
        
        System.out.println("Employee getAll()");
        
        String statement="SELECT object_id FROM Objects where type_id=15";

        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){

            arr.add(rs.getLong("object_id"));   
        }



        for(int i=0;i<arr.size();i++){
            objects.add(read(arr.get(i)));
        }

        return objects;
        
        
        
    }
    
    @Override
    public void quit(){
        
        try
        {
            this.connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public String getRole(){return this.role;}
    
    @Override
    public void setRole(String role){
        this.role=role;
    }
    
    @Override
    public int consider(String role, char mode, int type){
       
        String check=new String();
        String column=new String();
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        int result=0;
        
        
        if(role.equals("admin")){
            
            if(mode=='r'){
                column="AREAD";
            }
            if(mode=='w'){
                column="AWRITE";
            }
        }
        
        if(role.equals("manager")){
            
            if(mode=='r'){
                column="MREAD";
            }
            if(mode=='w'){
                column="MWRITE";
            }
        }
        
        if(role.equals("worker")){
            
            if(mode=='r'){
                column="DREAD";
            }
            if(mode=='w'){
                column="DWRITE";
            }
        }
        
        System.out.println(column);
        
        try{
            
            check="SELECT "+column+" FROM types WHERE type_id=15";
            ps=connection.prepareStatement(check);
            //System.out.println(check);
            rs=ps.executeQuery();
            
            while(rs.next()){
                //System.out.println("RESULT:"+rs.getInt(column));
                result=rs.getInt(column);
            }
            
            //System.out.println("result_1:"+result);
            
            if(result==0){
                
                check="SELECT "+column+" FROM types WHERE type_id=12";
                ps=connection.prepareStatement(check);
                //System.out.println(check);
                rs=ps.executeQuery();
                
                while(rs.next()){
                    result=rs.getInt(column);
                }
                
                //System.out.println("result_2:"+result);
            }
            
            if(result==0){
                
                check="SELECT "+column+" FROM types WHERE type_id=11";
                ps=connection.prepareStatement(check);
                //System.out.println(check);
                rs=ps.executeQuery();
                
                while(rs.next()){
                    result=rs.getInt(column);
                }
                
                //System.out.println("result_3:"+result);
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        if(result==1){
            System.out.println("APPROWED");
        }
        
        if(result==-1){
            System.out.println("DENIED");
        }
        
        return result;
    }
}
