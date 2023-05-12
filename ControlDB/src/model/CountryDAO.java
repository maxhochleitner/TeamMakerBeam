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
public class CountryDAO {
    public static ObservableList<Country> getCountries () {
        ObservableList<Country> countries = 
                FXCollections.observableArrayList();
        Connection con;
        Country country;
        
        try {
            con = DBConnector.connect();
            String sql = "SELECT * FROM countries";
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                country = new Country(
                     rs.getInt("id"),
                     rs.getString("country"),
                     rs.getString("country_code"),
                     rs.getInt("area")
                );
                countries.add(country);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return countries;
    }

    public static String getCountryById (int id) {
        Connection con;
        String country = "no country";
        
        try {
            con = DBConnector.connect();
            String sql = "SELECT * FROM countries WHERE id = " + id;
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            rs.first();
            country = rs.getString ("country");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return country;
    }
    
}
