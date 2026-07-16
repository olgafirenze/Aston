package Lesson_6;

import java.util.HashSet;
import java.util.Set;

public class Students {
    HashSet<Student> students;

    public Students() {
        students = new HashSet<>();
    }

    public Students(Student... students) {
        this.students = new HashSet<>();
        for(Student s : students) {
            this.students.add(s);
        }
    }

    public void deleteLoosers() {
        HashSet<Student> toRemove = new HashSet<>();
        for (Student student: students) {
            if (student.getAverageMark() < 3) toRemove.add(student);
        }
        students.removeAll(toRemove);
    }

    public void nextCourse() {
        for (Student student : students) {
            if (student.getAverageMark() >= 3) {
                if (student.getCourse() <= 5) {
                    student.setCourse(student.getCourse() + 1);
                    student.setGroup(student.getGroup() + 100);
                }
                else student.setCourse(0);
            }
        }
    }

    public  void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.getCourse() == course) System.out.println(student.getName());
        }
    }

    public void info() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void add(Student student) {
        students.add(student);
    }

    public static void main(String[] args) {
        Student ivanov = new Student("Иванов", 301, 3, 5, 4, 4);
        Student petrov = new Student("Петров", 101, 1, 3, 3, 4);
        Student sidorov = new Student("Сидоров", 312, 3, 3, 2, 3);

        Students unit = new Students(ivanov, petrov, sidorov);

        unit.printStudents(unit.students, 3);

        unit.deleteLoosers();
        unit.info();

        unit.nextCourse();
        unit.info();
    }
}


