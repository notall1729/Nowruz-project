package db;

import db.exception.EntityNotFoundException;
import java.util.ArrayList;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();

    static void add(Entity e){
        e.id = entities.size() + 1;
        entities.add(e);
    }

    static Entity get(int id) throws EntityNotFoundException{
        for (Entity entity : entities){
            if(entity.id == id)
                return entity;
        }

        throw new EntityNotFoundException();
    }

    static void delete(int id){
     for (int i = 0; i < entities.size(); i++){
         if(entities.get(i).id == id){
             entities.remove(i);
             return;
         }
     }
     throw new EntityNotFoundException();
    }

    static void update(Entity e) throws EntityNotFoundException{
      for (int i = 0; i < entities.size(); i++){
          if(entities.get(i).id == e.id){
              entities.set(i, e);
              return;
          }
      }
      throw new EntityNotFoundException();
    }
}
