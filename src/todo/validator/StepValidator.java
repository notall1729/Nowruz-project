package todo.validator;

import db.Database;
import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

public class StepValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException{
        if(!(entity instanceof Step)){
          throw new IllegalArgumentException("Entity must be type of Step.");
        }
         Step step = (Step) entity;
        if(step.getTitle() == null || step.getTitle().trim().isEmpty()){
            throw new InvalidEntityException("Title cannot be null or empty.");
        }
        if(Database.get(step.getTaskRef()) == null){
            throw new InvalidEntityException("Cannot find task with ID = " + step.getTaskRef());
        }
    }
}
