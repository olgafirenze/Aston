package Lesson_4.Animals;

public class Dog extends Animal {
    private static int dogCounter = 0;

    public Dog(String name) {
        super(name);
        dogCounter++;
    }

    @Override
    public void run(int runDistance) {
        if (runDistance <= 500) System.out.printf("%s пробежал(а) %d м\n", name, runDistance);
        else System.out.printf("%s не может пробежать %d м. Собаки бегают не больше 500 м.\n", name, runDistance);
    }

    @Override
    public void swim(int swimDistance) {
        if (swimDistance <= 10) System.out.printf("%s проплыл(а) %d м\n", name, swimDistance);
        else System.out.printf("%s не может проплыть %d м. Собаки плавают не больше 10 м.\n", name, swimDistance);
    }

    public static int getDogCounter() {
        return dogCounter;
    }
}
