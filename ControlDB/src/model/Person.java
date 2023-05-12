package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private int id;
    private StringProperty firstnameProperty;
    private StringProperty lastNameProperty;
    private IntegerProperty GebdatProperty;
    private StringProperty nationalitaetProperty;
    private StringProperty emailProperty;
    private StringProperty positionProperty;
    private IntegerProperty marktwertProperty;
    private IntegerProperty mannschaftidProperty;

    public Person(int id, String lastName, String firstName, int gebdat, String nationalitaet, String email, String position, int martwert, int mannschaftid) {
        this.id = id;
        this.lastNameProperty = new SimpleStringProperty(lastName);
        this.firstnameProperty = new SimpleStringProperty(firstName);
        this.GebdatProperty = new SimpleIntegerProperty(gebdat);
        this.nationalitaetProperty = new SimpleStringProperty(nationalitaet);
        this.emailProperty = new SimpleStringProperty(email);
        this.positionProperty = new SimpleStringProperty(position);
        this.marktwertProperty = new SimpleIntegerProperty(martwert);
        this.mannschaftidProperty = new SimpleIntegerProperty(mannschaftid);
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastNameProperty.get();
    }

    public StringProperty lastNameProperty() {
        return lastNameProperty;
    }

    public void setLastName(String lastName) {
        this.lastNameProperty.set(lastName);
    }

    public String getFirstName() {
        return firstnameProperty.get();
    }

    public StringProperty firstNameProperty() {
        return firstnameProperty;
    }

    public void setFirstName(String firstName) {
        this.firstnameProperty.set(firstName);
    }

    public int getGebdat() {
        return GebdatProperty.get();
    }

    public IntegerProperty gebdatProperty() {
        return GebdatProperty;
    }

    public void setGebdat(int gebdat) {
        this.GebdatProperty.set(gebdat);
    }

    public String getNationalitaet() {
        return nationalitaetProperty.get();
    }

    public StringProperty nationalitaetProperty() {
        return nationalitaetProperty;
    }

    public void setNationalitaet(String nationalitaet) {
        this.nationalitaetProperty.set(nationalitaet);
    }

    public String getEmail() {
        return emailProperty.get();
    }

    public StringProperty emailProperty() {
        return emailProperty;
    }

    public void setEmail(String email) {
        this.emailProperty.set(email);
    }

    public String getPosition() {
        return positionProperty.get();
    }

    public StringProperty positionProperty() {
        return positionProperty;
    }

    public void setPosition(String position) {
        this.positionProperty.set(position);
    }

    public int getMarktwert() {
        return marktwertProperty.get();
    }

    public IntegerProperty marktwertProperty() {
        return marktwertProperty;
    }

    public void setMartwert(int martwert) {
        this.marktwertProperty.set(martwert);
    }

    public int getMannschaftid() {
        return mannschaftidProperty.get();
    }

    public IntegerProperty mannschaftidProperty() {
        return mannschaftidProperty;
    }

    public void setMannschaftid(int mannschaftid) {
        this.mannschaftidProperty.set(mannschaftid);
    }
}