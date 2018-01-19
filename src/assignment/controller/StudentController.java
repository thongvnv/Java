/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.controller;

import assignment.entity.Student;
import assignment.model.StudentModel;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author daolinh
 */
public class StudentController {

    private final StudentModel model = new StudentModel();

    public void printStudentList() {
        ArrayList<Student> list = model.getListStudent();
        System.out.println("==========Student list==========");
        System.out.format("%5s | ", "Id");
        System.out.format("%10s | ", "Rollnumber");
        System.out.format("%20s | ", "Name");
        System.out.format("%12s | ", "Phone");
        System.out.format("%20s | ", "Email");
        System.out.format("%10s |%n ", "Status");
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            System.out.println("==============================================================================================");
            System.out.format("%5d | ", student.getId());
            System.out.format("%10s | ", student.getRollNumber());
            System.out.format("%20s | ", student.getName());
            System.out.format("%12s | ", student.getPhone());
            System.out.format("%20s | ", student.getEmail());
            System.out.format("%10s |%n ", student.getStatus());
        }
        System.out.println("==============================================================================================");
    }

    public void addStudent() {
        Student student = new Student();
        // Dùng scanner yêu cầu người dùng nhập vào từ bàn phím.
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==========Student add==========");
        
        while (true) {
            System.out.println("Please enter your roll number: ");
            String value = scanner.nextLine();

            if (value != null && value.length() <= 6) {                
                student.setRollNumber(value);
                break;
            }
            System.out.println("Rollnumber's required.");
        }
        
        while (true) {
            System.out.println("Please enter your name: ");
            String value = scanner.nextLine();

            if (value != null && value.length() > 2) {                
                student.setName(value);
                break;
            }
            System.out.println("Name's required.");
        }
                
        while (true) {
            System.out.println("Please enter your phone: ");
            String value = scanner.nextLine();

            if (value != null && value.length() > 0) {                
                student.setPhone(value);
                break;
            }
            System.out.println("Phone's required.");
        }
        
        while (true) {
            System.out.println("Please enter your email: ");
            String value = scanner.nextLine();

            if (value != null && value.length() > 0) {                
                student.setEmail(value);
                break;
            }
            System.out.println("Email's required.");
        }
        
        model.insert(student);
        System.out.println("============================");
        
    }   

    public void editStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========Student edit==========");
        System.out.println("Please enter student id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student existStudent = model.getById(id);
        if (existStudent == null) {
            System.out.println("Student is not exists or has been deleted.");
        } else {
            System.out.println("=======Student information======");
            System.out.format("%5s | ", "Id");
            System.out.format("%10s | ", "Rollnumber");
            System.out.format("%20s | ", "Name");
            System.out.format("%12s | ", "Phone");
            System.out.format("%20s | ", "Email");
            System.out.format("%10s |%n ", "Status");
            System.out.println("==============================================================================================");
            System.out.format("%5d | ", existStudent.getId());
            System.out.format("%10s | ", existStudent.getRollNumber());
            System.out.format("%20s | ", existStudent.getName());
            System.out.format("%12s | ", existStudent.getPhone());
            System.out.format("%20s | ", existStudent.getEmail());
            System.out.format("%10s |%n ", existStudent.getStatus());
            System.out.println("==============================================================================================");
            System.out.println("Please enter new rollnumber: ");
            String rollNumber = scanner.nextLine();
            System.out.println("Please enter new name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter new phone: ");
            String phone = scanner.nextLine();
            System.out.println("Please enter new email: ");
            String email = scanner.nextLine();
            
            existStudent.setRollNumber(rollNumber);
            existStudent.setName(name);
            existStudent.setPhone(phone);
            existStudent.setEmail(email);
            model.update(existStudent, id);
            System.out.println("Update success!");
        }
    }
    
    public void detailStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========Student detail==========");
        System.out.println("Please enter student id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student existStudent = model.getById(id);
        if (existStudent == null) {
            System.out.println("Student is not exists or has been deleted.");
        } else {
            System.out.println("=======Student information======");
            System.out.format("%5s | ", "Id");
            System.out.format("%10s | ", "Rollnumber");
            System.out.format("%20s | ", "Name");
            System.out.format("%12s | ", "Phone");
            System.out.format("%20s | ", "Email");
            System.out.format("%10s |%n ", "Status");
            System.out.println("==============================================================================================");
            System.out.format("%5d | ", existStudent.getId());
            System.out.format("%10s | ", existStudent.getRollNumber());
            System.out.format("%20s | ", existStudent.getName());
            System.out.format("%12s | ", existStudent.getPhone());
            System.out.format("%20s | ", existStudent.getEmail());
            System.out.format("%10s |%n ", existStudent.getStatus());
            System.out.println("==============================================================================================");
        }
    }
    
    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========Student delete==========");
        System.out.println("Please enter student id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student existStudent = model.getById(id);
        if (existStudent == null) {
            System.out.println("Student is not exists or has been deleted.");
        } else {
            model.delete(existStudent, id);
            System.out.println("Student's status changed to 0.");
        }
    }
}
