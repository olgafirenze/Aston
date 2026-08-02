package Lesson_6;

import java.util.HashSet;
import java.util.Set;

public class Student {
    String name;
    int group;
    int course;
    int math;
    int physics;
    int lang;
    double averageMark;

    public Student() {
    };

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int group, int course, int math, int physics, int lang) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.math = math;
        this.physics = physics;
        this.lang = lang;
        this.averageMark = (double) (math + physics + lang) / 3;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public int getMath() {
        return math;
    }

    public int getPhysics() {
        return physics;
    }

    public int getLang() {
        return lang;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Студент: " + name + ", группа: " + group + ", курс: " + course + ", средний балл: " + averageMark;
    }

}

