package todo.validator;

import db.Entity;
import db.Validator;
import todo.entity.Task;
import db.exception.InvalidEntityException;

public class TaskValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException{
        if(!(entity instanceof Task)){
            throw new IllegalArgumentException("Entity must be of type Task.");
        }

        Task task = (Task) entity;
        if(task.getTitle() == null || task.getTitle().isEmpty()){
           throw new InvalidEntityException("title cannot be null or empty.");
        }
    }
}
