package org.matteo;

import java.util.Scanner;

public class Main {
    TaskDAO tasks = new TaskDAO();
    public static void main(String[] args) {
        Main app = new Main();
        Boolean on; 
        app.run();
    }

   
    public void run() {
        Scanner sc = new Scanner(System.in);
        // start the TaskManager with a welcome message and first setup.
        System.out.println("Welcome to the Task Manager!");
        System.out.println("Please create your first Task");
        System.out.println("Enter Task name");
        System.out.print(">");
        String taskName = sc.nextLine();
        System.out.println("Please enter task description: ");
        String taskDescription = sc.nextLine();
        Task task = new Task(0, taskName, taskDescription, true);
        tasks.createTask(task);
        sc.close();
    }


}
