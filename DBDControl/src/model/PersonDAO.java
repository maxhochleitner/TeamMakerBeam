package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;
import util.DML_DAO;

public class PersonDAO {
    public static ObservableList<Person> getPersons () {
        ObservableList<Person> persons = FXCollections.observableArrayList();
        Connection con;
        
        try {
            con = DBConnector.connect();    //Verbindung zur DB
            String sql = "SELECT id, last_name, first_name, Year(dob) as year, country "
                    + " FROM persons";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Person p = new Person (
                    rs.getInt("id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getInt("year"),
                    rs.getString("country")                        
                );
                persons.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return persons;
    }
    public static void update (Person person) {
        String sql = "UPDATE persons SET "
                + " last_name = '" + person.getLastName() + "', "
                + " first_name = '" + person.getFirstName() + "', "
                + " dob = '" + person.getYob() + "-01-01', "
                + " country = '" + person.getCountry() + "' "
                + " WHERE id = " + person.getId();
        System.out.println("sql :" + sql);
        DML_DAO.executeDML(sql);
    }
}
