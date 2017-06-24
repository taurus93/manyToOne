package entities;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ManageEmployee {
    private static SessionFactory factory;
    public static void main(String[] args) {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ManageEmployee ME = new ManageEmployee();

      /* Let us have one address object */
        Address address = ME.addAddress("Kondapur","Hyderabad","AP","532");

      /* Add employee records in the database */
        ME.addEmployee("Manoj", "Kumar", 4000, address);

      /* Add another employee record in the database */
        ME.addEmployee("Dilip", "Kumar", 3000, address);

      /* List down all the employees */
        ME.listEmployees();

        ME.retriction();
    }

    /* Method to add an address record in the database */
    public Address addAddress(String street, String city,
                              String state, String zipcode) {
        Session session = factory.openSession();
        Transaction tx = null;
//        Integer addressID = null;
        Address address = null;
        try{
            tx = session.beginTransaction();
            address = new Address(street, city, state, zipcode);
            session.save(address);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return address;
    }

    /* Method to add an employee record in the database */
    public void addEmployee(String fname, String lname,
                               int salary, Address address){
        Session session = factory.openSession();
        Transaction tx = null;
//        Integer employeeID = null;
        try{
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary, address);
            session.save(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        //return employeeID;
    }

    /* Method to list all the employees detail */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirst_name());
                System.out.print("  Last Name: " + employee.getLast_name());
                System.out.println("  Salary: " + employee.getSalary());
                Address add = employee.getAddress();
                System.out.println("Address ");
                System.out.println("\tStreet: " +  add.getStreet_name());
                System.out.println("\tCity: " + add.getCity_name());
                System.out.println("\tState: " + add.getState_name());
                System.out.println("\tZipcode: " + add.getZipcode());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }



    public void retriction() {
        Session session = factory.openSession();
//       try {
//            tx = session.beginTransaction();
//            Criteria cr = session.createCriteria(Employee.class);
        String hql = "select * FROM EMPLOYEE E WHERE E.id > :employee_id";
        Query query = session.createNativeQuery(hql, Employee.class);
        query.setParameter("employee_id",10);
        List data = query.list();


        for(Object object : data)
        {

            System.out.println(object.toString());
        }
    }
}