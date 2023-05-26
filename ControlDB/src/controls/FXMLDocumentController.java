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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.PersonDAO;

public class FXMLDocumentController implements Initializable {
    private Person actPerson;
    private ObservableList<Person> myPersonList;
    private ListProperty<Person> listProperty = new SimpleListProperty<>();

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField gebdat;
    @FXML
    private TextField email;
    @FXML
    private TextField position;
    @FXML
    private TextField marktvalue;
    @FXML
    private TextField mannschaftid;
    @FXML
    private Button confirmbtn;
    @FXML
    private Button showbtn;
    @FXML
    private Button forwardbtn;
    @FXML
    private Button backbtn;
    @FXML
    private Button deletebtn;
    @FXML
    private TextField nationalitaet;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myPersonList = PersonDAO.getPersons();
        listProperty.setValue(myPersonList);
        System.out.println(myPersonList);
    }


    private void handleSaveAction(ActionEvent event) {
        // Handle the sabe button action
    }
}
