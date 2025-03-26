package db;

import db.exception.EntityNotFoundException;
import java.util.ArrayList;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();

    public static void add(Entity e){
        e.id = entities.size() + 1;
        entities.add(e);
    }

    public static Entity get(int id) throws EntityNotFoundException{
        for (Entity entity : entities){
            if(entity.id == id)
                return entity;
        }

        throw new EntityNotFoundException(id);
    }
}
