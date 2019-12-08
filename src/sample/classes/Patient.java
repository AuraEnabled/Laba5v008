package sample.classes;

import sample.ENUMs.Paid;
import sample.ENUMs.Procedures;

public class Patient {

//    Properties
    private String name;
    private int id;
    private String procedure;
    private double price;
    private String paid;
    private double debt;

    public Patient(){
        this.name = "";
        this.id = 0;
        this.procedure = Procedures.Кастрация.toString();
        this.price = 0;
        this.paid = Paid.True.toString();
        this.debt = 0;
    }

    public Patient(String name, int id, String procedure, double price, String paid, double debt){
        this.name = name;
        this.id = id;
        this.procedure = procedure.toString();
        this.price = price;
        this.paid = paid;
        this.debt = debt;
    }


    //    Getters
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getProcedure() {
        return procedure;
    }
    public double getPrice() {
        return price;
    }
    public String isPaid() {
        return paid;
    }
    public double getDebt() {
        return debt;
    }

//    Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPaid(String paid) {
        this.paid = paid;
    }
    public void setDebt(double debt) {
        this.debt = debt;
    }
}
