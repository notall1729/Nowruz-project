package todo.service;

import db.Database;
import db.exception.InvalidEntityException;
import todo.entity.Step;

public class StepService {
    public static void saveStep(int taskRef, String title) throws InvalidEntityException {
        Step step = new Step(title, taskRef);
        Database.add(step);
    }

    public static void completeStep(int stepId) throws InvalidEntityException{
        Step step = (Step)Database.get(stepId);
        if(step != null)
            step.setStatus(step.status.COMPLETED);

        Database.update(step);
    }
}
