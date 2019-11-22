package com.student.dao.util;

public class Queries {
	public static final String GET_STUDENT_BY_EMAIL_PASSWORD = "SELECT pk_id,email FROM user WHERE (email=? OR mobile_no=? OR login_id=? )AND password=? ";

	public static final String VALIDATING_STUDENT_EXISTS="SELECT s.name,s.gender,s.dob,s.standard,sh.hobbies FROM students s Join student_hobbies sh ON s.pk_id=sh.student WHERE name=?,gender=?,dob=?,standard=?,hobbies=?";
	public static final String INSERT_STUDENTS = "INSERT INTO `students`(user,name,gender,dob,standard,description) VALUES(?,?,?,?,?,?)";
	public static final String INSERT_STUDENT_HOBBIES = "INSERT INTO `student_hobbies`(hobbies,student) VALUES(?,?)";
	public static final String UPDATE_STUDENTS = "UPDATE students  SET name =?,gender =?, dob =?,standard =? WHERE pk_id =?";
	public static final String UPDATE_STUDENT_HOBBIES = "UPDATE student_hobbies SET hobbies=? WHERE student=?";
	public static final String STUDENT_USER_VALIDATION = "SELECT GROUP_CONCAT(h.name) hobbies,s.pk_id,s.name AS name,s.gender AS gender,s.dob AS dob ,c.name AS standard,s.description AS description FROM students s LEFT JOIN standard c ON s.standard = c.pk_id LEFT JOIN student_hobbies sh ON s.pk_id = sh.student  JOIN hobbies h ON sh.hobbies = h.pk_id WHERE s.user = ? AND s.pk_id = ? GROUP BY s.pk_id";
	public static final String STUDENTS_LIST = "SELECT s.pk_id,s.name AS name,s.gender AS gender,s.dob AS dob,c.name AS standard FROM students s JOIN standard c ON s.standard=c.pk_id;";
	public static final String DELETE_STUDENT = "DELETE FROM students where pk_id=?";
	public static final String DELETE_STUDENT_HOBBIES = "DELETE FROM student_hobbies WHERE student=?";

	public static final String CLASS_LIST = "SELECT * FROM standard";
	public static final String CLASS_USER_VALIDATION = "SELECT * FROM standard WHERE user=? AND pk_id=?";
	public static final String INSERT_CLASS = "INSERT INTO `standard`(user,name) VALUES(?,?)";
	public static final String UPDATE_CLASS = "UPDATE standard SET name=? WHERE  pk_id=?";
	public static final String DELETE_CLASS = "DELETE FROM standard where pk_id=?";

	public static final String HOBBIES_LIST = "SELECT * FROM hobbies";
	public static final String HOBBIES_USER_VALIDATION = "SELECT * FROM hobbies WHERE user=? AND pk_id=?";
	public static final String INSERT_HOBBIES = "INSERT INTO `hobbies`(user,name) VALUES(?,?)";
	public static final String UPDATE_HOBBIES = "UPDATE hobbies SET name=? WHERE  pk_id=?";
	public static final String DELETE_HOBBY_HOBBIES = "DELETE FROM hobbies where pk_id=?";
	
	public static final String STUDENT_HOBBIES_LIST="SELECT hobbies FROM student_hobbies WHERE student=?";
	
	public static final String CHECK_STUDENTS_HAVE_HOBBY="SELECT student FROM student_hobbies WHERE hobbies=?";
	public static final String DELETE_HOBBY_STUDENT_HOBBIES="DELETE FROM student_hobbies WHERE hobbies=? ";
	
	public static final String CHECK_STUENTS_BEFORE_DELETING_CLASS="SELECT * FROM students WHERE standard=?";
	public static final String CHECK_CLASS_BELONG_TO_USER="SELECT * FROM hobbies WHERE pk_id=? AND user=?";
	public static final String DELETE_PREVIOUS_HOBBY="DELETE FROM student_hobbies WHERE hobbies=? AND student=?";
	
	public static final String INSERT_NEW_HOBBY="INSERT INTO student_hobbies(hobbies,student) VALUES(?,?)";
	
	public static final String DELETE_HOBBIES_WHILE_UPDATION="DELETE FROM student_hobbies WHERE hobbies=? AND student=?";
}
