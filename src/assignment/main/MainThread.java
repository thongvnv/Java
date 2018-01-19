/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.main;

import assignment.controller.StudentController;
import java.util.Scanner;

/**
 *
 * @author daolinh
 */
public class MainThread {        
    
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        while(true){
            MainThread menu = new MainThread();
            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            System.out.println("===============Student Manager==============");
            System.out.println("1. Student list.");
            System.out.println("2. Add student.");
            System.out.println("3. Edit student.");
            System.out.println("4. Delete student.");
            System.out.println("5. Detail student.");
            System.out.println("6. Exit.");
            System.out.println("Please enter your choice: ");
            choice = scanner.nextInt();

            switch (choice){
                case 1: 
                    controller.printStudentList();
                    break;
                case 2: 
                    controller.addStudent();
                    break;
                case 3: 
                    controller.editStudent();
                    break;
                case 4: 
                    controller.deleteStudent();
                    break;
                case 5:
                    controller.detailStudent();
                    break;
                case 6: 
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter choice from 1 to 5.");
                    break;
            }           
        }
        
    }
      
}
