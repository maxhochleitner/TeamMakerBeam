/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personed;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import model.Country;
import model.CountryDAO;
import model.Person;
import model.PersonDAO;

/**
 * FXML Controller class
 *
 * @author TC
 */
public class FXML_PersonedController implements Initializable {
    private Person actPerson;
    
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;
    @FXML
    private MenuItem miSave;
    @FXML
    private Spinner<Integer> spYob;
    @FXML
    private RadioButton rbIt;
    @FXML
    private ToggleGroup tgDepartments;
    @FXML
    private RadioButton rbSales;
    @FXML
    private RadioButton rbFinance;
    @FXML
    private CheckBox chbFullTime;
    @FXML
    private ComboBox<Country> cbCountries;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spYob.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1950,2020)
        );
        cbCountries.setItems(CountryDAO.getCountries());
    }    

    @FXML
    private void handleMiSaveAction(ActionEvent event) {
        actPerson.setLastName(tfLastName.getText());
        actPerson.setFirstName(tfFirstName.getText());
        actPerson.setYob(spYob.getValue());
        actPerson.setDepartment(((RadioButton)tgDepartments.getSelectedToggle()).getText());
        actPerson.setFullTime(chbFullTime.isSelected());
        actPerson.setCountryId(cbCountries.getSelectionModel().getSelectedItem().getId());
        System.out.println("set country: " + CountryDAO.getCountryById(actPerson.getId()));
        actPerson.setCountry( CountryDAO.getCountryById(actPerson.getCountryId()));
        PersonDAO.update (actPerson);

    }
    
    public void setPerson (Person actPerson) {
        this.actPerson = actPerson;
        tfLastName.setText(actPerson.getLastName());
        tfFirstName.setText(actPerson.getFirstName());
        spYob.getValueFactory().setValue(actPerson.getYob());
        rbIt.setSelected(true);  //Defaultauswahl
        for (Toggle rb : tgDepartments.getToggles()) {
            if (((RadioButton)rb).getText().equals(actPerson.getDepartment()))
                tgDepartments.selectToggle(rb);
        }
        chbFullTime.setSelected(actPerson.isFullTime());
        for (Country country : cbCountries.getItems())
            if (country.getId() == actPerson.getCountryId())
                cbCountries.getSelectionModel().select(country);        
    }
}
