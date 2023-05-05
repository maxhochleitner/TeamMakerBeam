package model;

/**
 *
 * @author tc
 */
public class Person {
    private int id;
    private String lastName;
    private String firstName;
    private int yob;
    private String country;

    public Person(int id, String lastName, String firstName, int yob, String country) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.yob = yob;
        this.country = country;
    }


    public Person(int id, String lastName, String firstName, int yob) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.yob = yob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + yob; 
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    

}
