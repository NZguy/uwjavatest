package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn = "";
  private boolean propertyChangeFired = false;
  private static int counter = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
	counter++;
  }

  public int getAge(){
	  return this.age;
  }
  
  public void setAge(int age){
	if(age < 0){
		throw new IllegalArgumentException("Age can not be less than 0");
	}else{
		this.age = age; 
	}
  }
  
  public String getName(){
	  return this.name;
  }
  
  public void setName(String name){
	if(name == null){
		throw new IllegalArgumentException("Name can not be null");
	}else{
		this.name = name;
	}
  }
  
  public double getSalary(){
	  return this.salary;
  }
  
  public void setSalary(double salary){
	  this.salary = salary;
  }
  
  public String getSSN(){
	  return this.ssn;
  }
  
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }
  
  public static int count(){
	  return counter;
  }
  
  static List<Person> getNewardFamily(){
	  List<Person> people = new ArrayList<Person>();
	  people.add(new Person("Ted", 41, 250000));
	  people.add(new Person("Charlotte", 43, 150000));
	  people.add(new Person("Michael", 22, 10000));
	  people.add(new Person("Matthew", 15, 0));
	  return people;
  }
  
  @Override
  public boolean equals(Object o){
	  if(o instanceof Person){
		  Person other = (Person) o;
		  if(this.getName().equals(other.getName()) && this.getAge() == other.getAge()){
			  return true;
		  }
	  }
	  return false;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public int compareTo(Person other){
	  return (int)(other.getSalary() - this.getSalary());
  }
  
  @Override
  public String toString() {
    return "[Person name:"+this.name+" age:"+this.age+" salary:"+this.salary+"]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
  
  static class AgeComparator implements Comparator<Person>{
	  
	  @Override
	  public int compare(Person p1, Person p2){
		  return (p1.getAge() - p2.getAge());
	  } 
	  
  }
  
}
