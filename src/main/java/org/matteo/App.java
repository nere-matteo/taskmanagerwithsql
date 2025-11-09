package org.matteo;

import java.io.File;
import java.util.Scanner;
import java.util.List;

public class App {
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
    boolean on = true;
    Scanner sc = new Scanner(System.in);

    while (on) {
      System.out.println("======*Task Manager*=======");
      System.out.println("1. List all tasks");
      System.out.println("2. Add task");
      System.out.println("3. Mark task done");
      System.out.println("4. Exit");
      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          listTasks();
          continue;
        case 2:
          addTask();
          continue;
        case 3:
          // markTask();
          continue;
        case 4:
          on = false;
          break;
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
  }

  private void addTask() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Please enter the task name");
    System.out.println(">");
    String taskName = sc.nextLine();
    System.out.println("Please enter a task description");
    System.out.println(">");
    String taskDescription = sc.nextLine();
    Task task = new Task(1, taskName, taskDescription, false);

    dao.createTask(task);
  }
}
