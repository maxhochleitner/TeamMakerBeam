package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;
import util.DML_DAO;

/**
 *
 * @author tc
 */
public class PersonDAO {
/*
    private static Person []persons = {
        new Person(1, "Agg", "Stefan", 1984, "IT", true, 1),
        new Person(2, "Boandl", "Rene", 1985, "Sales", false, 2),
        new Person(3, "Krichmayer", "Vincent", 1990, "Finance", true, 1)
    };
*/
/*    
    private static Country []countries = {
        new Country(1, "Österreich", "AUT"),
        new Country(2, "Deutschland", "DE"),
        new Country(3, "Italien", "IT"),
    };
*/
    
    public static ObservableList<Person> getPersons () {
        ObservableList<Person> personsList = 
                FXCollections.observableArrayList();
        Connection con;
        
        try {
            con = DBConnector.connect();
            String sql = "SELECT p.*, c.country FROM persons p, countries c "
                    + " WHERE p.country_id = c.id ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                char full_time = rs.getString("full_time").charAt(0);
                boolean isFullTime = full_time == 'y';
                personsList.add(new Person(
                     rs.getInt("id"),
                     rs.getString("last_name"),
                     rs.getString("first_name"),
                     rs.getInt("yob"),
                     rs.getString("department"),
                     isFullTime,
                     rs.getInt("country_id"),
                     rs.getString(("country"))
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return personsList;
    }
/*
    public static ObservableList<Country> getCountries () {
        ObservableList<Country> countryList = 
                FXCollections.observableArrayList(countries);
        return countryList;
    }
*/    
    public static void update(Person actPerson) {
        String sql = "UPDATE persons SET "
                + " first_name  = '" + actPerson.getFirstName() + "'"
                + ", last_name  = '" + actPerson.getLastName() + "'"
                + ", yob = " + actPerson.getYob()
                + ", department = '" + actPerson.getDepartment() + "'"
                + ", full_time = '" + (actPerson.isFullTime() ? 'y':'n') + "'"
                + ", country_id = " + actPerson.getCountryId()
                + " WHERE id = " + actPerson.getId();
        System.out.println("sql: " + sql);
        DML_DAO.executeDML(sql);
        /*
        for (Person p : persons) {
            if (p.getId() == actPerson.getId()) {
                p.setFirstName(actPerson.getFirstName());
                p.setLastName(actPerson.getLastName());
                p.setYob(actPerson.getYob());
                p.setFullTime(actPerson.isFullTime());
                p.setCountryId(actPerson.getCountryId());
                //System.out.println("set coutryId: " + p.getCountryId());
                break;
            }
        }
        */
        //for (Person p : persons) 
        //        System.out.println(p);
    }
    
    
}
