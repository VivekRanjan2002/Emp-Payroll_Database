package com.bridgeLabz.Main.service;

import com.bridgeLabz.Main.service.entity.EmployeePayrollData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
    private static EmployeePayrollDBService employeePayrollDBService;
    private PreparedStatement employeePayrollDataStatement;
    private EmployeePayrollDBService(){}
    //singleton instance
    public static EmployeePayrollDBService getInstance(){
        if(employeePayrollDBService==null){
            employeePayrollDBService= new EmployeePayrollDBService();
        }
        return  employeePayrollDBService;
    }
    /*@desc:read data from payroll_service database
      @return:EmployeePayrollData List
      @throw: SQL Exception
     */
    public List<EmployeePayrollData> readData(){
        String sqlQuery= "SELECT * FROM employee_payroll";
        List<EmployeePayrollData> employeePayrollList= new ArrayList<>();
       try(Connection connection = this.getConnection()) {
           Statement statement = connection.createStatement(); //statement for executing sql queries
           ResultSet resultSet= statement.executeQuery(sqlQuery);
      employeePayrollList = this.getEmployeePayrollData(resultSet);

       }
       catch (SQLException e){
           e.printStackTrace();
       }
       return employeePayrollList ;
    }
    /*
    @desc: update certain employee salary  on the database using statement
    @return: integer
     */
    public int updateEmployeeData(String name, int salary) {
    return this.updateEmployeeDataUsingStatement(name,salary);
    }
    /*
    @desc: initialise preparestatement if not and then excute the query
     */
    public List<EmployeePayrollData> getEmployeePayrollData(String name){
      List<EmployeePayrollData> employeePayrollList= null;
      if(this.employeePayrollDataStatement==null)
          this.prepareStatementForEmployeeData();
      try{
          employeePayrollDataStatement.setString(1,name);
          ResultSet resultSet= employeePayrollDataStatement.executeQuery();
          employeePayrollList= this.getEmployeePayrollData(resultSet);

      }
      catch(SQLException e){
          e.printStackTrace();
      }
      return employeePayrollList;
    }
    // using resultset to populate employeePayrollList
    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) {
      List<EmployeePayrollData> employeePayrollList= new ArrayList<>();


        try{
        while(resultSet.next()) { // as resultset contain multiple records
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
            employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
        }
        return employeePayrollList;
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return employeePayrollList;
    }
    // initialise preparestatement for employeedata
    private void prepareStatementForEmployeeData() {
   try{
       Connection connection= this.getConnection();
       String sql= "Select * from employee_payroll where name =?";
       employeePayrollDataStatement=connection.prepareStatement(sql);
   }
   catch(SQLException e){
       e.printStackTrace();
   }
    }
    // updatae employee's salary with name  data using statement
    private int updateEmployeeDataUsingStatement(String name, int salary) {
     String sql= String.format("update employee_payroll set salary= %d where name='%s';",salary,name);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement(); //statement for executing sql queries
            return  statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    // establish connection with database jdbc url
    private Connection  getConnection(){
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "****";
        Connection connection;
        try {
            connection= DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connection established!!!");
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }

        return connection;
    }

}
