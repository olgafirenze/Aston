package Lesson_4.Animals;

public class Cat extends Animal {
    private static int catCounter = 0;
    boolean satiety;

    public Cat(String name) {
        super(name);
        catCounter++;
        satiety = false;
        int eatFood;
    }

    @Override
    public void run(int runDistance) {
        if (runDistance <= 200) System.out.printf("%s пробежал(а) %d м\n", name, runDistance);
        else System.out.printf("%s не может пробежать %d м. Коты бегают не больше 200 м.\n", name, runDistance);
    }

    @Override
    public void swim(int swimDistance) {
        System.out.printf("%s не может проплыть %d м. Коты не умеют плавать.\n", name, swimDistance);
    }

    public static int getCatCounter() {
        return catCounter;
    }

    public void eat(int eatFood) {
        if (eatFood <= Bowl.food) {
            Bowl.food -= eatFood;
            System.out.println(name + " съел " + eatFood + " еды. В миске осталось " + Bowl.food + ".");
            satiety = true;
        } else { System.out.println(name + " хотел съесть " + eatFood + " еды. Но в миске только " + Bowl.food +
                ". " + name + " остался(ась) голодный(ая).");
        }
    }

    public void getSatiety() {
        if (satiety) System.out.println(name + " сытый(ая).");
        else System.out.println(name + " голодный(ая).");
    }

    public static class Bowl {
        private static int food;
        private int extraFood;
        public Bowl (int food) {
            this.food = food;
        }

        public int getFood() {
            return food;
        }

        public void addFood(int extraFood) {
            food += extraFood;
        }
    }
}
