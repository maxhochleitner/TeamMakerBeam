package controlsdemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import model.Person;
import model.PersonDAO;

/**
 *
 * @author tc
 */
public class FXMLDocumentController implements Initializable {
    private ListProperty<Person> listProperty = new SimpleListProperty();
    private ObservableList<Person> persons;
    /*
    = FXCollections.observableArrayList(
                new Person(1, "Agg", "Stefan", 1985, "AUT"), 
                new Person(2, "Boandl", "Rene", 1984, "IT"),
                new Person(3, "Fuchs", "Klaus", 1984, "DE")
    );
    */
    @FXML
    private ComboBox<Person> cbPersons;
    @FXML
    private MenuItem miEdit;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private Button btnSave;
    @FXML
    private Spinner<Integer> spYob;
    private ToggleGroup tgCountries;
    @FXML
    private ComboBox<?> cbcountries;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        //cbPersons.setItems(persons);
        persons = PersonDAO.getPersons();
        cbPersons.itemsProperty().bind(listProperty);
        listProperty.setValue(persons);
        
        spYob.setValueFactory(
                new IntegerSpinnerValueFactory(1958, 2005)
        );
        
        
    }    

    @FXML
    private void handleCbPersonsAction(ActionEvent event) {
        Person p = cbPersons.getSelectionModel().getSelectedItem();
        System.out.println(p.getId() + " " + p);
    }

    @FXML
    private void handleMiEditAction(ActionEvent event) {
        Person p = cbPersons.getSelectionModel().getSelectedItem();
        //check if selected
        if (p == null)
            return;
        //set controls
        tfFirstName.setText(p.getFirstName());
        tfLastName.setText(p.getLastName());
        spYob.getValueFactory().setValue(p.getYob());
        for (Toggle toggle : tgCountries.getToggles()) {
            if (((RadioButton)toggle).getText().equals(p.getCountry()))
                tgCountries.selectToggle(toggle);
        }
    }

    @FXML
    private void handleBtnSaveAction(ActionEvent event) {
        Person p = cbPersons.getSelectionModel().getSelectedItem();
        //check if selected
        if (p == null)
            return;

        p.setFirstName(tfFirstName.getText());
        p.setLastName(tfLastName.getText());
        p.setYob(spYob.getValue());
        p.setCountry(((RadioButton)tgCountries.getSelectedToggle()).getText());
        //observableList Änderung übergeben
        int inx = cbPersons.getSelectionModel().getSelectedIndex();
        persons.set(inx, p);
        PersonDAO.update(p);
    }
    
}
