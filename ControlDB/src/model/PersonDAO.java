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
                    rs.getInt("SpielerId"),
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getInt("Geburtsdatum"),
                    rs.getString("Nationalitaet"),
                    rs.getString("Email"),
                    rs.getString("Position"),
                    rs.getInt("Marktwert"),
                    rs.getInt("MannschaftId")
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
            + "Vorname = '" + actPerson.getVorname() + "', "
            + "Nachname = '" + actPerson.getNachname() + "', "
            + "Geburtsdatum = " + actPerson.getGeburtsdatum() + ", "
            + "Nationalitaet = '" + actPerson.getNationalitaet() + "', "
            + "Email = '" + actPerson.getEmail() + "', "
            + "Position = '" + actPerson.getPosition() + "', "
            + "Marktwert = " + actPerson.getMarktwert() + ", "
            + "ManschaftId = " + actPerson.getMannschaftId() + " "
            + "WHERE SpielerId = " + actPerson.getSpielerId();
        
        System.out.println("sql: " + sql);
        DML_DAO.executeDML(sql);
    }
}