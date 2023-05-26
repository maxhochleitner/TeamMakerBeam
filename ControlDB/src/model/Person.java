package model;

import javafx.beans.property.*;

public class Person {
    private int spielerId;
    private StringProperty vornameProperty;
    private StringProperty nachnameProperty;
    private IntegerProperty geburtsdatumProperty;
    private StringProperty NationalitaetProperty;
    private StringProperty emailProperty;
    private StringProperty positionProperty;
    private IntegerProperty marktwertProperty;
    private IntegerProperty mannschaftIdProperty;

    public Person(int spielerId, String vorname, String nachname, int geburtsdatum, String Nationalitaet, String email, String position, int marktwert, int mannschaftId) {
        this.spielerId = spielerId;
        this.vornameProperty = new SimpleStringProperty(vorname);
        this.nachnameProperty = new SimpleStringProperty(nachname);
        this.geburtsdatumProperty = new SimpleIntegerProperty(geburtsdatum);
        this.NationalitaetProperty = new SimpleStringProperty(Nationalitaet);
        this.emailProperty = new SimpleStringProperty(email);
        this.positionProperty = new SimpleStringProperty(position);
        this.marktwertProperty = new SimpleIntegerProperty(marktwert);
        this.mannschaftIdProperty = new SimpleIntegerProperty(mannschaftId);
    }

    public int getSpielerId() {
        return spielerId;
    }

    public String getVorname() {
        return vornameProperty.get();
    }

    public StringProperty vornameProperty() {
        return vornameProperty;
    }

    public void setVorname(String vorname) {
        this.vornameProperty.set(vorname);
    }

    public String getNachname() {
        return nachnameProperty.get();
    }

    public StringProperty nachnameProperty() {
        return nachnameProperty;
    }

    public void setNachname(String nachname) {
        this.nachnameProperty.set(nachname);
    }

    public int getGeburtsdatum() {
        return geburtsdatumProperty.get();
    }

    public IntegerProperty geburtsdatumProperty() {
        return geburtsdatumProperty;
    }

    public void setGeburtsdatum(int geburtsdatum) {
        this.geburtsdatumProperty.set(geburtsdatum);
    }

    public String getNationalitaet() {
        return NationalitaetProperty.get();
    }

    public StringProperty nationalitaetProperty() {
        return NationalitaetProperty;
    }

    public void setNationalitaet(String nationalitaet) {
        this.NationalitaetProperty.set(nationalitaet);
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

    public void setMarktwert(int marktwert) {
        this.marktwertProperty.set(marktwert);
    }

    public int getMannschaftId() {
        return mannschaftIdProperty.get();
    }

    public IntegerProperty mannschaftIdProperty() {
        return mannschaftIdProperty;
    }

    public void setMannschaftId(int mannschaftId) {
        this.mannschaftIdProperty.set(mannschaftId);
    }
}
