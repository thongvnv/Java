/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.model;

import assignment.entity.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Làm nhiệm vụ thao tác với bảng students trong database. Các thao tác gồm có:
 * thêm mới một sinh viên, sửa sinh viên, lấy danh sách sinh viên, xoá thông tin
 * sinh viên.
 *
 * @author daolinh
 */
public class StudentModel {
    
    // Lấy danh sách sinh viên trong database và trả về một arraylist các đối tượng Student.
    public ArrayList<Student> getListStudent() {
        // Khởi tạo một array líst rỗng để chứa dữ liệu trả về.
        ArrayList<Student> listStudent = new ArrayList<>();

        // Tạo kết nối tới database, thực thi câu lệnh "select * from students";
        Connection connection = ConnectionHelper.getConnection();
        if (connection == null) {
            return listStudent;
        }
        try {
            // Tạo đối tượng statement để thực thi lệnh sql.
            Statement statement = connection.createStatement();
            // Thực thi câu lệnh sql với kết quả trả về đưa vào resultset.
            ResultSet rs = statement.executeQuery("SELECT * FROM students");
            // Với từng dòng trả về trong resultset, lấy ra các cột tương ứng và tạo đối tượng student.
            while (rs.next()) {
                int id = rs.getInt("id");
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int status = rs.getInt("status");
                // Tạo ra đối tượng student tương ứng với các trường vừa lấy ra.
                Student student = new Student(id, rollNumber, name, phone, email, status);
                // Thêm đối tượng vừa tạo vào arraylist (tạo ở trên cùng).
                listStudent.add(student);
            }
        } catch (SQLException ex) {
            System.err.println("Can not connect to database.");
        }

        // trả về kết quả default;
        return listStudent;
    }
        
    public boolean insert(Student student) {
        // Tạo kết nối tới database;
        Connection connection = ConnectionHelper.getConnection();
        if (connection == null) {
            return false;
        }
        String sqlInsert = "INSERT INTO students (rollNumber, name, phone, email) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, student.getRollNumber());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getEmail());
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println("Can not insert student.");
        }

        return false;
    }
    
    public Student getById(int id){
        Student student = null;
        
        Connection connection = ConnectionHelper.getConnection();
        if(connection == null){
            return null;
        }
        try {
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM students WHERE id = " + id);
            while(rs.next()){
                int studentId = rs.getInt("id");
                String studentRollNumber = rs.getString("rollNumber");
                String studentName = rs.getString("name");
                String studentPhone = rs.getString("phone");
                String studentEmail = rs.getString("email");
                int studentStatus = rs.getInt("status");
                student = new Student(studentId, studentRollNumber, studentName, studentPhone, studentEmail, studentStatus);
            }
        } catch (Exception e) {
            System.out.println("Can not connect to database.");
        }
        return student;
    }
    
    public boolean update(Student student, int id){
        Connection connection = ConnectionHelper.getConnection();
        if (connection == null) {
            return false;
        }
        String sqlUpdate = "UPDATE students SET rollNumber = ?, name = ?, phone = ?, email = ? WHERE id = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, student.getRollNumber());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getEmail());
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println("Can not insert student.");
        }

        return false;
    }
    
    public boolean delete(Student student, int id){
        Connection connection = ConnectionHelper.getConnection();
        if (connection == null) {
            return false;
        }
        try {
            Statement statement = connection.createStatement();        
            statement.execute("UPDATE students SET status = 0 WHERE id = " + id);
        } catch (SQLException ex) {
            System.err.println("Can not delete student.");
        }

        return false;
    }
}
