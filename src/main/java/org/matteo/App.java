package org.matteo;

import java.io.File;
import java.util.Scanner;
import java.util.List;

public class App {
  Scanner sc = new Scanner(System.in);
  private TaskDAO dao = new TaskDAO();

  public void run() {
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
          continue;
        case 3:
          markTask();
          continue;
        case 4:
          deleteTask();
          continue;
        case 5:
          on = false;
          break;
        case 6:
          deleteAllTasks();
          continue;
        default:
          System.out.println("Wrong input, please enter a number between 1-4");
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
    for (Task t : tasks) {
      String status = t.getStatus() ? "Done" : "Not Done";
      System.out.printf("%d. %s - %s (%s)%n", t.getId(), t.getName(), t.getDescription(), status);
    }
    sc.nextLine();
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
    System.out.println("Please select which Task to mark");
    System.out.print(">");
    listTasks();
    int choice = sc.nextInt();
    dao.markAsDone(choice);
    sc.nextLine();
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
