<------------Welcome to Employee Payroll Service Database---------------->
<--UC1 create  Payroll service database-->
mysql> create database payroll_service;
Query OK, 1 row affected (0.04 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| addressbookservice |
| information_schema |
| mysql              |
| payroll_service    |
| performance_schema |
| sakila             |
| sys                |
| world              |
+--------------------+
8 rows in set (0.00 sec)

<--UC2 create Table employee_payroll with certain fields-->
mysql> create Table employee_payroll(
    -> Id INT AUTO_INCREMENT PRIMARY KEY,
    -> name varchar(255) NOT NULL,
    -> salary INT,
    -> startDate varchar(255));
Query OK, 0 rows affected (0.12 sec)

<--UC3 Populating field values of Table-->
mysql> insert into employee_payroll(name,salary,startDate)
    -> values("Vivek",100000,"14/12/2023");
Query OK, 1 row affected (0.02 sec)
mysql> insert into employee_payroll(name,salary,startDate)
    -> values("Ranjan",200000,"15/12/2023");
Query OK, 1 row affected (0.00 sec)
mysql> insert into employee_payroll(name,salary,startDate)
    -> values("Amit",300000,"16/12/2023");
Query OK, 1 row affected (0.00 sec)
mysql> select * from employee_payroll;
+----+--------+--------+------------+
| Id | name   | salary | startDate  |
+----+--------+--------+------------+
|  1 | Vivek  | 100000 | 14/12/2023 |
|  2 | Ranjan | 200000 | 15/12/2023 |
|  3 | Amit   | 300000 | 16/12/2023 |
+----+--------+--------+------------+
3 rows in set (0.01 sec)