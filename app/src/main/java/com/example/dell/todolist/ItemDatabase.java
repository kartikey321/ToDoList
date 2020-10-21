package com.example.dell.todolist;

import java.util.Date;

class ItemDatabase {
    private String subject;
    private String details;
    private int day;
    private int month;
    private int year;
    private String str;

    public ItemDatabase(String str,String subject, String details, int day, int month, int year) {
        this.str=str;
        this.subject = subject;
        this.details = details;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public ItemDatabase() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "ItemDatabase{" +
                "subject='" + subject + '\'' +
                ", details='" + details + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
