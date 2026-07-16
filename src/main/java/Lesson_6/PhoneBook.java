package Lesson_6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    TreeMap<String, List<String>> book;

    public PhoneBook() {
        this.book = new TreeMap<>();
    }

    public void add(String surname, String phone) {
        if (book.containsKey(surname)) {
            List<String> numbers =  book.get(surname);
            numbers.add(phone);
            System.out.println("Для фамилия " + surname + " добавлен номер " + phone);
        } else {
            ArrayList<String> numbers = new ArrayList<>();
            numbers.add(phone);
            book.put(surname, numbers);
            System.out.println("Добавлена запись: фамилия " + surname + ", номер " + phone);
        }
    }

    public List<String> get(String surname) {
        if (!book.containsKey(surname)){
            System.out.println("Фамилии " + surname + " нет в справочнике.");
            return new ArrayList<>();
        }
        else return book.get(surname);
    }

    public void printBySurname(String surname) {
        List<String> numbers = book.get(surname);
        System.out.println("Для фамилии " + surname + " найдено: ");
        for (String number : numbers) {
            System.out.println(number);
        }
    }

    public void printAll() {
        for (Map.Entry<String, List<String>> entry : book.entrySet()) {
           String surname = entry.getKey();
           List<String> numbers = entry.getValue();
           System.out.println(surname);
           for (String number : numbers) {
               System.out.println(number);
           }

        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "+71234567890");
        phoneBook.add("Иванов", "+71000000000");
        phoneBook.add("Петров", "+71233227809");
        phoneBook.add("Сидоров", "+79991234567");
        System.out.println();
        phoneBook.printBySurname("Сидоров");
        System.out.println();
        phoneBook.printAll();


    }
}


