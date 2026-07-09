package Lesson_3;

import java.util.ArrayList;


public class Park {

    private String parkName;
    private String parkTime;
    private String address;
    private ArrayList<Attraction> attractions = new ArrayList<>();

    public Park(String parkName, String parkTime, String address) {
        this.parkName = parkName;
        this.parkTime = parkTime;
        this.address = address;
    }

    public void addAttraction(String name, String time, int price) {
        Attraction attraction = this.new Attraction(name, time, price);
        attractions.add(attraction);
    }

    public void info() {
        System.out.println(parkName + "\nВремя работы " + parkTime + "\n" + address + "\n\nАттракционы:\n");
        for (Attraction a: attractions) {
            a.info();
        }
    }

    public class Attraction {

        private String name;
        private String time;
        private int price;

        public Attraction () {}

        public Attraction(String name, String time, int price) {
            this.name = name;
            this.time = time;
            this.price = price;
        }

        public void info() {
            System.out.println(name + "\nВремя работы " + time + "\nСтоимость " + price + "руб.\n");
        }

    }

    public static void main() {
        Park park = new Park("ЦПКиО им. Горького", "10:00 - 22:00", "Москва, Зубовский будьвар, д.1");
        park.addAttraction("Колесо обозрения", "10:00 - 19:00", 1000);
        park.addAttraction("Американские горки", "10:00 - 20:00", 1300);
        park.addAttraction("Комната страха", "10:00 - 22:00", 900);
        park.info();
    }

}

