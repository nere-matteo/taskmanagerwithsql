package org.matteo;

import java.io.File;
import java.util.Scanner;
import java.util.List;

public class App {
  Scanner sc = new Scanner(System.in);
  private TaskDAO dao = new TaskDAO();

  public void run() {
    // create the initial file if it;
    if (isFirstLaunch()) {
      showWelcomeScreen();
      setupDefaults();
    }
    showMainMenu();
  }

  private boolean isFirstLaunch() {
    File file = new File("tasks.db");
    return !file.exists();
  }

  private void showWelcomeScreen() {
    System.out.println("Welcome to the Task Manager!");
    System.out.println("Let's set up some things");
  }

  private void setupDefaults() {
    dao.createTask(new Task(0, "Example Task", "This is your first task", false));
    System.out.println("Default task created");
  }

  private void showMainMenu() {
    sc.nextLine();
    boolean on = true;
    Scanner sc = new Scanner(System.in);
    while (on) {
      System.out.println("======*Task Manager*=======");
      System.out.println("1. List all tasks");
      System.out.println("2. Add task");
      System.out.println("3. Mark task done");
      System.out.println("4. Delete a task");
      System.out.println("5. Exit");
      System.out.println("6. Delete All Tasks");
      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          listTasks();
          continue;
        case 2:
          addTask();
          break;
        case 3:
          markTask();
          break;
        case 4:
          deleteTask();
          break;
        case 5:
          on = false;
          break;
        case 6:
          deleteAllTasks();
          continue;
        default:
          System.out.println("Wrong input, please enter a number between 1-6");
      }
    }
    sc.close();
  }

  private void listTasks() {
    List<Task> tasks = dao.getAllTasks();

    if (tasks.isEmpty()) {
      System.out.println("No Tasks available");
      return;
    }

    System.out.println("---> Your Tasks <---");
    // list all the tasks that are in the tasks lists
    for (Task t : tasks) {
      String status = t.getStatus() ? "Done" : "Not Done";
      System.out.printf("Id: %d \nName: %s\n Description: %s \n Status: %s%n", t.getId(), t.getName(),
          t.getDescription(), status);
    }
  }

  private void addTask() {

    System.out.println("Please enter the task name");
    System.out.print(">");
    String taskName = sc.nextLine();
    System.out.println("Please enter a task description");
    System.out.print(">");
    String taskDescription = sc.nextLine();
    Task task = new Task(1, taskName, taskDescription, false);

    dao.createTask(task);

    System.out.println("Added a task with name " + taskName + " and task description: " + taskDescription);
    sc.nextLine();
  }

  private void markTask() {
    List<Task> tasks = dao.getAllTasks();
    if (tasks.isEmpty()) {
      System.out.println("No tasks available to mark");
      return;
    }
    System.out.println("Please select a task to mark");
    listTasks();

    int choice;
    boolean valid = false;

    while (true) {
      System.out.print("> ");

      if (!sc.hasNextInt()) {
        System.out.println("Please enter a valid number");
        sc.nextInt();
        continue;
      }

      choice = sc.nextInt();
      sc.nextLine();

      for (Task t : tasks) {
        if (t.getId() == choice) {
          valid = true;
          break;
        }
      }

      if (valid) {
        dao.markAsDone(choice);
        System.out.println("Marked the task as done");
        break;
      } else {
        System.out.println("Please re-enter a valid ID");
      }
    }
  }

  private void deleteTask() {
    System.out.println("Please select which Task to delete");
    System.out.print(">");
    listTasks();
    int choice = sc.nextInt();
    dao.deleteTask(choice);
    sc.nextLine();
  }

  private void deleteAllTasks() {
    dao.deleteAllTasks();
  }
}
