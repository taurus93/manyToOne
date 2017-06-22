package entities;

import javax.persistence.*;

//@Entity
//@Table
public class Employee {
  private int id;
  private String first_name;
  private String last_name;
  private int salary;
  private Address address;

  public Employee() {}
  public Employee(String fname, String lname,
                  int salary, Address address ) {
    this.first_name = fname;
    this.last_name = lname;
    this.salary = salary;
    this.address = address;
  }

//  @Id
//  @Column
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

//  @Basic
//  @Column
  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

//  @Basic
//  @Column
  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

//  @Basic
//  @Column
  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

//  @ManyToOne
//  @Column
  public Address getAddress() {
    return address;
  }

  public void  setAddress( Address address ) {
    this.address = address;
  }
}
