package db;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();

    public static void add(Entity e){
        e.id = entities.size() + 1;
        entities.add(e);
    }
}
