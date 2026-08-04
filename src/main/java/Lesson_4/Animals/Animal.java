package Lesson_4.Animals;

public abstract class Animal {
    protected String name;
    protected int runDistance;
    protected int swimDistance;
    private static int animalCounter = 0;

    public Animal(String name) {
        this.name = name;
        animalCounter++;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);

    public static int getAnimalCounter() {
        return animalCounter;
    }

}



