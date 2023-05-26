package controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Person;
import model.PersonDAO;

public class FXMLDocumentController implements Initializable {
    private Person actPerson;
    private ObservableList<Person> myPersonList;
    private ListProperty<Person> listProperty = new SimpleListProperty<>();
    private ReadOnlyIntegerWrapper currentIndex = new ReadOnlyIntegerWrapper(-1);

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
    private TextField natio;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myPersonList = PersonDAO.getPersons();
        listProperty.set(FXCollections.observableArrayList(myPersonList));

        currentIndex.addListener((obs, oldIndex, newIndex) -> {
            if (newIndex.intValue() >= 0 && newIndex.intValue() < myPersonList.size()) {
                showPersonDetails(myPersonList.get(newIndex.intValue()));
            }
        });

        // Enable/disable navigation buttons based on the current index
        BooleanBinding isFirstPerson = currentIndex.isEqualTo(0);
        BooleanBinding isLastPerson = currentIndex.isEqualTo(myPersonList.size() - 1);
        backbtn.disableProperty().bind(isFirstPerson);
        forwardbtn.disableProperty().bind(isLastPerson);

        // Zeige die Details der ersten Person an (falls vorhanden)
        if (!myPersonList.isEmpty()) {
            currentIndex.set(0);
    }
}
    

        @FXML
        private void handleConfirmButton(ActionEvent event) {
                // Aktualisiere die Daten der aktuell ausgewählten Person
                actPerson.setVorname(firstname.getText());
                actPerson.setNachname(lastname.getText());
                actPerson.setGeburtsdatum(Integer.parseInt(gebdat.getText()));
                actPerson.setNationalitaet(natio.getText());
                actPerson.setEmail(email.getText());
                actPerson.setPosition(position.getText());
                actPerson.setMarktwert(Integer.parseInt(marktvalue.getText()));
                actPerson.setMannschaftId(Integer.parseInt(mannschaftid.getText()));

                // Speichern der aktualisierten Person in der Datenbank oder Datenquelle
                PersonDAO.update(actPerson);
}

    @FXML
    private void handleShowButton(ActionEvent event) {
        if (!myPersonList.isEmpty()) {
            currentIndex.set(0);
        }
    }

    @FXML
    private void handleForwardButton(ActionEvent event) {
        if (currentIndex.get() < myPersonList.size() - 1) {
            currentIndex.set(currentIndex.get() + 1);
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        if (currentIndex.get() > 0) {
            currentIndex.set(currentIndex.get() - 1);
        }
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {

    }


    private void showPersonDetails(Person person) {
        firstname.setText(person.getVorname());
        lastname.setText(person.getNachname());
        gebdat.setText(String.valueOf(person.getGeburtsdatum()));
        natio.setText(person.getNationalitaet());
        email.setText(person.getEmail());
        position.setText(person.getPosition());
        marktvalue.setText(String.valueOf(person.getMarktwert()));
        mannschaftid.setText(String.valueOf(person.getMannschaftId()));
        
        actPerson = person; // Initialisiere das actPerson-Objekt mit der übergebenen Person
    }
}
