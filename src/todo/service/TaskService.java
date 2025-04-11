package todo.service;

import db.Database;
import db.exception.InvalidEntityException;
import todo.entity.Task;
import todo.entity.Task.Status;

import java.util.Date;

public class TaskService {
    public static void setAsCompleted(int taskId) throws InvalidEntityException{
     try {
         Task task = (Task) Database.get(taskId);
         if (task == null) {
             throw new InvalidEntityException("Entity cannot be null.");
         } else {
             task.setStatus(Status.COMPLETED);
             Database.update(task);
         }
     }
     catch (RuntimeException e){
         throw new RuntimeException("Task class not found.");
     }
    }

    public static void createTask(String title, String description, Date dueDate) throws InvalidEntityException{
        Task task = new Task(title, description, dueDate);
        Database.add(task);
    }

    public static void setAsInProgress(int taskId) throws InvalidEntityException{
        Task task = (Task) Database.get(taskId);
        if(task == null){
            throw new InvalidEntityException("Entity cannot be null.");
        }
            task.setStatus(Status.INPROGRESS);
            Database.update(task);

    }
}
