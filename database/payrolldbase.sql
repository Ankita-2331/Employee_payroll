-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 31, 2016 at 09:03 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `payrolldbase`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS employee (
employeename varchar(30) NOT NULL,
 employeeid int (11) NOT NULL,
 salary double(10,2) NOT NULL,
  designation varchar(30) NOT NULL,
  contactnumber int(10) NOT NULL,
userid int(11) NOT NULL,
status varchar(10) NOT NULL,
password int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO employee (employeename,employeeid,salary,designation,contactnumber,userid,status,password) VALUES
('employee1',2, 50000.00, 'desc1', '998877',1,'status1',123);

-- --------------------------------------------------------

--
-- Table structure for table `leave`
--

CREATE TABLE IF NOT EXISTS leaves (
leaveid int(11) NOT NULL,
employeeid int(11) NOT NULL,
reason varchar(100) NOT NULL,
leavedate date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leave`
--

INSERT INTO leaves(leaveid, employeeid, reason, leavedate) VALUES
(1, 2, 'Sick', '2016-10-27');

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE IF NOT EXISTS salary (
salaryid int(11) NOT NULL,
employeeid int(11) NOT NULL,
month varchar(10) NOT NULL,
 
  incentive double(10,2) NOT NULL,
  leavecount int(11) NOT NULL,
  deduction double(10,2) NOT NULL,
  salary double(10,2) NOT NULL,
  netsalary double(10,2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary`
--

INSERT INTO salary (salaryid, employeeid, month,incentive, leavecount, deduction, salary, netsalary) VALUES
(1, 2,'month',2000.00, 1, 1000.00, 20000.00, 10000.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS user (
userid int(11) NOT NULL,
  username varchar(25) NOT NULL,
  password int(11) NOT NULL,
  status varchar(11) NOT NULL DEFAULT 'Active',
  usertype varchar(25) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO user (userid, username, password, status, usertype) VALUES
(1, 'admin', 123, 'Active', 'Admin'),
(2, 'emp1', 123, 'Active', 'Employee');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE employee
 ADD PRIMARY KEY (employeeid);

--
-- Indexes for table `leave`
--
ALTER TABLE leaves
 ADD PRIMARY KEY (leaveid);

--
-- Indexes for table `salary`
--
ALTER TABLE salary
 ADD PRIMARY KEY (salaryid);

--
-- Indexes for table `users`
--
ALTER TABLE user
 ADD PRIMARY KEY (userid), ADD UNIQUE KEY username (username);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE employee
MODIFY employeeid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `leave`
--
ALTER TABLE leaves
MODIFY leaveid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `salary`
--
ALTER TABLE salary
MODIFY salaryid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE user
MODIFY userid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
