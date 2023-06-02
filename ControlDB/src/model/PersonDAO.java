package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                        rs.getInt("Geburtsjahr"),
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
        String sql = "UPDATE spieler SET "
                + "Vorname = '" + actPerson.getVorname() + "', "
                + "Nachname = '" + actPerson.getNachname() + "', "
                + "Geburtsjahr = " + actPerson.getGeburtsdatum() + ", "
                + "Nationalitaet = '" + actPerson.getNationalitaet() + "', "
                + "Email = '" + actPerson.getEmail() + "', "
                + "Position = '" + actPerson.getPosition() + "', "
                + "Marktwert = " + actPerson.getMarktwert() + ", "
                + "MannschaftId = " + actPerson.getMannschaftId() + " "
                + "WHERE SpielerId = " + actPerson.getSpielerId();

        DML_DAO.executeDML(sql);
    }

    /**
     * Fügt eine neue Person zur Datenbank hinzu.
     *
     * @param newPerson die hinzuzufügende Person
     */
    public static void addPerson(Person newPerson) {
        String sql = "INSERT INTO spieler (Vorname, Nachname, Geburtsjahr, Nationalitaet, Email, Position, Marktwert, MannschaftId) "
                    + "VALUES ('" 
                    + newPerson.getVorname() + "', '"
                    + newPerson.getNachname() + "', " 
                    + newPerson.getGeburtsdatum() + ", '"
                    + newPerson.getNationalitaet() + "', '" 
                    + newPerson.getEmail() + "', '" 
                    + newPerson.getPosition() + "', "
                    + newPerson.getMarktwert() + ", " 
                    + newPerson.getMannschaftId() + ")";
        
        DML_DAO.executeDML(sql);
    }
}
