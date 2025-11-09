package org.matteo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
  public TaskDAO() {
    try (Connection conn = Database.getConnection();
        Statement stmt = conn.createStatement()) {
      stmt.execute("""
          CREATE TABLE IF NOT EXISTS tasks(
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              name TEXT NOT NULL,
              description TEXT NOT NULL,
              status BOOLEAN DEFAULT 0
          )
          """);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void createTask(Task task) {
    String sql = "INSERT INTO tasks(name, description, status) VALUES (?, ?, ?)";
    try (Connection conn = Database.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, task.getName());
      pstmt.setString(2, task.getDescription());
      pstmt.setBoolean(3, task.getStatus());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Task> getAllTasks() {
    List<Task> tasks = new ArrayList<>();
    String sql = "SELECT * FROM tasks";

    try (Connection conn = Database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        tasks.add(new Task(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getBoolean("status")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tasks;
  }

  public void deleteAllTasks() {
    String sql = "DELETE FROM tasks";

    try (Connection conn = Database.getConnection();
        Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void markAsDone(int id) {
    String sql = "UPDATE tasks SET done = 1 WHERE id = ?";
    try (Connection conn = Database.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteTask(int id) {
    String sql = "DELETE FROM tasks WHERE id = ?";
    try (Connection conn = Database.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
