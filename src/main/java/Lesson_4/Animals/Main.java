package Lesson_4.Animals;

public class Main {
    public static void main(String[] args) {

        Dog dog1 = new Dog("Шарик");
        Dog dog2 = new Dog("Бобик");
        Dog dog3 = new Dog("Тузик");

        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Муся");

        dog1.run(113);
        dog1.swim(7);
        dog2.swim(50);
        dog3.run(600);
        cat1.run(199);
        cat1.swim(15);
        cat2.run(201);

        System.out.printf("Создано %d животных, в т.ч. %d собак, %d котов.\n", Animal.getAnimalCounter(),
                Dog.getDogCounter(), Cat.getCatCounter());

        Cat.Bowl bowl = new Cat.Bowl(15);
        bowl.addFood(45);
        System.out.println("В миске " + bowl.getFood() + " еды.");
        cat1.eat(26);

        Cat[] cats = new Cat[4];
        cats[0] = new Cat("Марсик");
        cats[1] = new Cat("Триша");
        cats[2] = new Cat("Аксель");
        cats[3] = new Cat("Ёксель");
        for (Cat a : cats) {
            a.eat(11);
            a.getSatiety();
        }

    }

}