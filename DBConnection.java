package com.bridgeLabz.Main;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
public class DBConnection {
    public static void main(String[] args) {
        String jdbcURL="jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName="root";
        String password="Vivek123";
        Connection connection;
        try{ // mysql driver is loaded or not
            /*
            When a Driver class is loaded, it should create an instance of itself and register it with the DriverManager.
             This means that a user can load and register a driver by calling: Class.forName("foo.bah.Driver")
             */
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        listDrivers();
        //connecting to the database
        try{
            System.out.println("connecting to database: "+jdbcURL);
            connection=DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connection is succesfull!!!  "+connection);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    // logging out all the drivers available
    /*
    The Java SQL framework allows for multiple database drivers.
Each driver should supply a class that implements the Driver interface.
The DriverManager will try to load as many drivers as it can find and then for any given connection request,
it will ask each driver in turn to try to connect to the target URL
     */
    private static void listDrivers(){
        /*
        An object that implements the Enumeration interface generates a series of elements, one at a time.
        Successive calls to the nextElement method return successive elements of the series.
         */
        Enumeration<Driver> driverList= DriverManager.getDrivers();
        while(driverList.hasMoreElements()){
            Driver driverclass=(Driver) driverList.nextElement();
            System.out.println(" "+driverclass.getClass().getName());
        }
    }
}
