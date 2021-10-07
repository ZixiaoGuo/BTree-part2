package com.zixiaoguo.cs635.assignment2.main;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * this is the class for student object with constructors, getters and setters
 */
public class Student {

    private String name;
    private int redId;
    private float gpa;

    public Student(String name, int redId, float gpa) {
        this.name = name;
        this.redId = redId;
        this.gpa = gpa;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRedId() {
        return redId;
    }

    public void setRedId(int redId) {
        this.redId = redId;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "com.zixiaoguo.cs635.assignment1.main.Student{" +
                "name='" + name + '\'' +
                ", redId=" + redId +
                ", gpa=" + gpa +
                '}';
    }
}