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
    -> salary varchar(255),
    -> startDate varchar(255));
Query OK, 0 rows affected (0.12 sec)