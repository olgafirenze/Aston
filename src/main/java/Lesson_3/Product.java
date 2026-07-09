package Lesson_3;


public class Product{

    static void main(String [] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("Samsung S23", "17.11.2023",
                "Samsung Corp.", "Korea", 3355, false);
        productsArray[2] = new Product("iPhone 15 Plus", "10.01.2024",
                "Apple Corp.", "China", 7999, true);
        productsArray[3] = new Product("iPhone 15 Pro", "03.06.2024",
                "Apple Corp.", "USA", 9999, false);
        productsArray[4] = new Product("Nokia 3310", "01.01.2001",
                "Nokia Inc.", "Hungary", 2000, false);
        for (int i = 0; i < productsArray.length; i++) {
            productsArray[i].info();
        }
    }

    private String name;
    private String productionDate;
    private String producer;
    private String country;
    private int price;
    private boolean isBooked;
    public Product(String name, String productionDate, String producer, String country, int price, boolean isBooked) {
        this.name = name;
        this.productionDate = productionDate;
        this.producer = producer;
        this.country = country;
        this.price = price;
        this.isBooked = isBooked;
    }

    public void info(){
        System.out.printf("%s, %s, %s, %s, %d, %b\n", name, productionDate, producer, country, price, isBooked);
    }
}
