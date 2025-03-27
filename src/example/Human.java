package example;

public class Human extends db.Entity {
    public String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public Human copy() {
        Human copyHuman = new Human(name);
        copyHuman.id = id;

        return copyHuman;
    }
}
