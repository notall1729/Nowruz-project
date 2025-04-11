package todo.service;

import db.Database;
import db.exception.InvalidEntityException;
import todo.entity.Task;
import db.Entity;

import javax.xml.crypto.Data;
import java.util.Date;

public class TaskService {
    public static void setAsCompleted(int taskId) throws InvalidEntityException {
      Task task = (Task) Database.get(Task.class, taskId);
      if(task == null) {
          throw new InvalidEntityException("Entity cannot be null.");
      }
      else{
          task.setStatus(Status.Completed);
          Database.update(task);
      }
    }

    public static void creatTask(String title, String description, Date dueDate){
        Task task = new Task(title, description, dueDate);
        Database.add(task);
    }

    public static void setAsInProgress(int taskId) throws InvalidEntityException{
        Task task = (Task) Database.get(Task.class, taskId);
        if(task == null){
            throw new InvalidEntityException("Entity cannot be null.");
        }
        else{
            task.setStatus(Status.InProgress);
            Database.update(task);
        }
    }
}
