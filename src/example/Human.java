package example;

public class Human extends db.Entity {
    public static final int HUMAN_ENTITY_CODE = 14;
    public String name;
    public int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Human copy() {
        Human copyHuman = new Human(name, age);
        copyHuman.id = id;

        return copyHuman;
    }

    public int getEntityCode(){
        return HUMAN_ENTITY_CODE;
    }

}
