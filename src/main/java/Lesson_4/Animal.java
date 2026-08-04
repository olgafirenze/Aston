public abstract class Animal {
    protected String name;
    protected int runDistance;
    protected int swimDistance;
    private static int animalCounter = 0;

    public Animal(String name) {
        this.name = name;
        animalCounter++;
    }

    public void run(int runDistance) {
        System.out.printf("%s пробежал(а) %d м\n", name, runDistance);
    }

    public void swim(int swimDistance) {
        System.out.printf("%s проплыл(а) %d м\n", name, swimDistance);
    }

    public static int getAnimalCounter() {
        return animalCounter;
    }

    public static class Dog extends Animal {
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

    public static class Cat extends Animal {
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


    public static void main(String[] args) {
        Animal cow = new Animal("Бурёнка");
        cow.run(130);
        cow.swim(17);

        Animal.Dog dog1 = new Animal.Dog("Шарик");
        Animal.Dog dog2 = new Animal.Dog("Бобик");
        Animal.Dog dog3 = new Animal.Dog("Тузик");

        Animal.Cat cat1 = new Animal.Cat("Барсик");
        Animal.Cat cat2 = new Animal.Cat("Муся");

        dog1.run(113);
        dog1.swim(7);
        dog2.swim(50);
        dog3.run(600);
        cat1.run(199);
        cat1.swim(15);
        cat2.run(201);

        System.out.printf("Создано %d животных, в т.ч. %d собак, %d котов.\n", cow.getAnimalCounter(), dog1.getDogCounter(),
                cat1.getCatCounter());

        Animal.Cat.Bowl bowl = new Animal.Cat.Bowl(15);
        bowl.addFood(45);
        System.out.println("В миске " + bowl.getFood() + " еды.");
        cat1.eat(26);

        Animal.Cat[] cats = new Animal.Cat[4];
        cats[0] = new Animal.Cat("Марсик");
        cats[1] = new Animal.Cat("Триша");
        cats[2] = new Animal.Cat("Аксель");
        cats[3] = new Animal.Cat("Ёксель");
        for (Animal.Cat a : cats) {
            a.eat(11);
            a.getSatiety();
        }


    }
}


