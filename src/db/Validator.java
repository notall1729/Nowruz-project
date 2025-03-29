package db;

import db.exception.InvalidEntityException;

public interface Validator {
    void validator(Entity entity) throws InvalidEntityException;
}
