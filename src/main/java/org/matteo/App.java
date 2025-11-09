package org.matteo;

import java.io.File;

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
        System.out.println("======*Task Manager*=======");
        System.out.println("1. List all tasks");
        System.out.println("2. Add task");
        System.out.println("3. Mark task done");
        System.out.println("4. Exit");

    }
}
