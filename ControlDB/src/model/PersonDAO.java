package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;
import util.DML_DAO;

/**
 * DAO-Klasse für Personen.
 */
public class PersonDAO {
    
    /**
     * Gibt eine Liste aller Personen aus der Datenbank zurück.
     * 
     * @return ObservableList von Personen
     */
    public static ObservableList<Person> getPersons() {
        ObservableList<Person> personsList = FXCollections.observableArrayList();
        Connection con = null;
        
        try {
            con = DBConnector.connect();
            String sql = "SELECT * FROM Spieler";
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                personsList.add(new Person(
                    rs.getInt("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getInt("gebdat"),
                    rs.getString("nationalität"),
                    rs.getString("email"),
                    rs.getString("position"),
                    rs.getInt("marktwert"),
                    rs.getInt("manschaftid")
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        
        return personsList;
    }
    
    /**
     * Aktualisiert die Informationen einer Person in der Datenbank.
     * 
     * @param actPerson die aktualisierte Person
     */
    public static void update(Person actPerson) {
        String sql = "UPDATE Spieler SET "
            + "firstname = '" + actPerson.getFirstName() + "', "
            + "lastname = '" + actPerson.getLastName() + "', "
            + "gebdat = " + actPerson.getGebdat() + ", "
            + "nationalität = '" + actPerson.getNationalitaet() + "', "
            + "email = '" + actPerson.getEmail() + "', "
            + "position = '" + actPerson.getPosition() + "', "
            + "marktwert = " + actPerson.getMarktwert() + ", "
            + "manschaftid = " + actPerson.getMannschaftid() + " "
            + "WHERE id = " + actPerson.getId();
        
        System.out.println("sql: " + sql);
        DML_DAO.executeDML(sql);
    }
}