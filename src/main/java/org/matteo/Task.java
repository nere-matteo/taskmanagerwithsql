package org.matteo;

public class Task {
  private int id;
  private String name;
  private String description;
  private Boolean status;

  public Task(int id, String name, String description, Boolean status) {
    this.id = id++;
    this.name = name;
    this.description = description;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return id + ": " + name + "[desc: " + description + " | Status " + (status ? "done" : "unfinished") + "]";
  }
}
