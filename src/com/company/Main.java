package com.company;

import java.util.*;

// Если имеем доступ к классу User
class User implements Comparable<User>{
    int age;

    public User(int age){ //конструктор
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }

    @Override // Для нормального вывода на экран, без o.age; вдруг он приватный?
    public  String toString(){
        return "Age "+age;
    }

}

// Если не имеем доступ к классу Person
class Person {
    private String name;
    private int age;

    public Person(String n, int a){
        name=n;
        age=a;
    }

    String getName(){
        return name;
    }

    int getAge(){
        return age;
    }
}

// Если не имеем доступ к классу Person
class PersonNameComparator implements Comparator<Person>{ //сравниваем имена String
    public int compare(Person man_1, Person man_2){
        return man_1.getName().compareTo(man_2.getName());
    }
}
class PersonAgeComparator implements Comparator<Person>{ //сравниваем возраст int
    public int compare(Person man_1, Person man_2){
        return man_1.getAge()-man_2.getAge();
        /*
        if(a.getAge()> b.getAge())
            return 1;
        else if(a.getAge()< b.getAge())
            return -1;
        else
            return 0;
         */
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("---Если имеем доступ к классу User---");
        Set s = new TreeSet(); //отвортированная коллекция
        s.add(new User(20));
        s.add(new User(23));
        s.add(new User(37));
        s.add(new User(18));
        for (Object o:s){
            System.out.println(o);
        }

        System.out.println("---Если не имеем доступ к классу Person---");
        Comparator pcomp = new PersonNameComparator().thenComparing(new PersonAgeComparator()); //Создаем сортировку Comparator<Person> pcomp = new PersonNameComparator();
        TreeSet<Person> people = new TreeSet(pcomp);                                            //но так как у нас 2 условия проверки, то .thenComparing(new PersonAgeComparator());
        people.add(new Person("Nick",47));
        people.add(new Person("Tom", 23));
        people.add(new Person("Nick",34));
        people.add(new Person("Tom",10));
        people.add(new Person("Bill",14));
        for(Person  p : people){
            System.out.println(p.getName() + " " + p.getAge());
        }
    }
}
