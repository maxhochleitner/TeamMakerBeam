package controls;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Country;
import model.CountryDAO;
import model.Person;
import model.PersonDAO;
import personed.FXML_PersonedController;

/**
 *
 * @author tc
 */
public class FXMLDocumentController implements Initializable {
    private Person actPerson;
    private String []departments = {"IT", "Sales", "Finance"};
    //private RadioButton []radios = new RadioButton[departments.length];
    //private ToggleGroup deptRadioGrp = new ToggleGroup();
    private ObservableList<Person> myPersonList;
    private ListProperty<Person> listProperty = new SimpleListProperty(); 

    @FXML
    private ComboBox<Person> cbPersons;
    @FXML
    private MenuItem miEdit;
    @FXML
    private MenuItem miShow;
    @FXML
    private TableView<Person> tvPersons;
    @FXML               //Zeilenobjektklasse, Memberklasse
    private TableColumn<Person, String> tcLastName;
    @FXML
    private TableColumn<Person, String> tcFirstName;
    @FXML
    private TableColumn<Person, Integer> tcYob;
    @FXML
    private TableColumn<Person, Boolean> tcFullTime;
    @FXML
    private TableColumn<Person, String> tcCountry;
    
    
    
    //Zeitpunkt: Öffnen der GUI, nachdem alle
    //  Controls erzeugt wurden
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        //cbPersons.setItems(PersonDAO.getPersons()); 
        myPersonList = PersonDAO.getPersons();
        //ComboBox an die Liste binden
        cbPersons.itemsProperty().bind(listProperty);
        listProperty.setValue(myPersonList);
        
        //Spalten der TableView an die Member binden
        tcLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        tcYob.setCellValueFactory(new PropertyValueFactory("yob"));
        tcFullTime.setCellValueFactory(new PropertyValueFactory("fullTime"));
        tcCountry.setCellValueFactory(new PropertyValueFactory("country"));
        tvPersons.setItems(myPersonList);
        
    }    

    @FXML
    private void handleCbPersonsAction(ActionEvent event) {
        //merke den aktuellen Datensatz
        actPerson = cbPersons.getSelectionModel().getSelectedItem();
        //getSelectedItem liefert eine Referenz auf ein Personenobjekt !!!
        //lblName.setText(cbPersons.getSelectionModel().getSelectedItem().getLastName());
    }

    @FXML
    private void handleMiEditAction(ActionEvent event) {
        int inx = cbPersons.getSelectionModel().getSelectedIndex();
        if (inx == -1)
            return;
        actPerson = myPersonList.get(inx);
        
        try {
            FXMLLoader loader = new FXMLLoader();       //Loader Objekt
            loader.setLocation(getClass().getResource("/personed/FXML_Personed.fxml"));
            Parent root = loader.load();                            //Wurzelcontrol
            FXML_PersonedController ctrl = loader.getController();  //ref. Controlerobj
            ctrl.setPerson(actPerson);
            Stage stage = new Stage();                              //neue Bühne
            stage.initModality(Modality.WINDOW_MODAL);              //im Vordergrund
            stage.setScene(new Scene(root));
            stage.showAndWait();                                    //Anzeige
            System.out.println("after Dialog: " + actPerson);
            myPersonList.set(inx, actPerson);

            //cbPersons.setItems(PersonDAO.getPersons());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleMiSaveAction(ActionEvent event) {
        int inx = cbPersons.getSelectionModel().getSelectedIndex();
        if (inx == -1)
            return;
        actPerson = myPersonList.get(inx);
    }

    @FXML
    private void handleMiShowAction(ActionEvent event) {
    }
    
}

       /*
        if (cbNames.getItems() == null)
            System.out.println("zu Beginn null");
        cbNames.getItems().add("Hansi");
        cbNames.getItems().add("Fritz");
        cbNames.getItems().add("Sepp");
        cbNames.getItems().add("Maria");
        //cbNames.setItems(value);
        ObservableList<String> namesList = FXCollections.observableArrayList(
                "Vinzent","Mario","Rene"
        );
        cbNames.setItems(namesList);
        cbNames.setVisible(false);          //unsichtbar
*/
        
