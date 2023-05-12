package model;

/**
 *
 * @author tc
 */
public class Country {
    private int id;
    private String country;
    private String countryCode;
    private int area;

    public Country(int id, String country, String countryCode, int area) {
        this.id = id;
        this.country = country;
        this.countryCode = countryCode;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return country;
    }
    
    
}
