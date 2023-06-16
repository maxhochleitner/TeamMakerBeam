package controls;

import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    @FXML
    private Button confirmbtn1;

    private boolean isNewEntry = false;

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
            currentIndex.set(-1); // Setze den currentIndex auf -1, um nicht automatisch den ersten Datensatz anzuzeigen
        }
    }

    @FXML
    private void handleConfirmButton(ActionEvent event) {
        if (isNewEntry) {
            // Neuer Datensatz
            actPerson = new Person(
                0, // spielerId (you can set it to 0 or provide a unique identifier)
                firstname.getText(), // vorname
                lastname.getText(), // nachname
                Integer.parseInt(gebdat.getText()), // geburtsdatum
                natio.getText(), // Nationalitaet
                email.getText(), // email
                position.getText(), // position
                Integer.parseInt(marktvalue.getText()), // marktwert
                Integer.parseInt(mannschaftid.getText()) // mannschaftId
            );

            PersonDAO.addPerson(actPerson);
            myPersonList = PersonDAO.getPersons(); // Aktualisiere die Liste der Personen
            listProperty.set(FXCollections.observableArrayList(myPersonList));

            currentIndex.set(myPersonList.indexOf(actPerson)); // Setze den currentIndex auf den Index der neu hinzugefügten Person

            isNewEntry = false;
        } else {
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

            myPersonList = PersonDAO.getPersons(); // Aktualisiere die Liste der Personen
            listProperty.set(FXCollections.observableArrayList(myPersonList));
        }
    }

    @FXML
    private void handleShowButton(ActionEvent event) {
        myPersonList = PersonDAO.getPersons(); // Aktualisiere die Liste der Personen
        listProperty.set(FXCollections.observableArrayList(myPersonList));

        if (!myPersonList.isEmpty()) {
            currentIndex.set(0); // Zeige den ersten Datensatz an
        } else {
            currentIndex.set(-1); // Setze den currentIndex auf -1, falls die Liste leer ist
        }
        isNewEntry = false;
    }


    @FXML
    private void handleForwardButton(ActionEvent event) {
        if (currentIndex.get() < myPersonList.size() - 1) {
            currentIndex.set(currentIndex.get() + 1);
        }
        isNewEntry = false;
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        if (currentIndex.get() > 0) {
            currentIndex.set(currentIndex.get() - 1);
        }
        isNewEntry = false;
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        if (actPerson != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Datensatz löschen");
            confirmationAlert.setHeaderText("Möchtest du den Datensatz wirklich löschen?");
            confirmationAlert.setContentText("Vorname: " + actPerson.getVorname() + "\nNachname: " + actPerson.getNachname());

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                PersonDAO.deletePerson(actPerson);
                int currentIndexValue = currentIndex.get();
                myPersonList.remove(actPerson);

                if (currentIndexValue >= myPersonList.size()) {
                    // Wenn der gelöschte Datensatz der letzte war, gehe zum vorherigen Datensatz
                    currentIndexValue--;
                }

                if (currentIndexValue >= 0 && currentIndexValue < myPersonList.size()) {
                    // Zeige den nächsten Datensatz an
                    currentIndex.set(currentIndexValue);
                } else {
                    // Liste ist leer oder currentIndex ist außerhalb des gültigen Bereichs
                    currentIndex.set(-1);
                    clearPersonDetails();
                }
            }
        }
    }

    private void clearPersonDetails() {
        firstname.clear();
        lastname.clear();
        gebdat.clear();
        natio.clear();
        email.clear();
        position.clear();
        marktvalue.clear();
        mannschaftid.clear();
        actPerson = null;
    }

    @FXML
    private void handleNewButton(ActionEvent event) {
        isNewEntry = true;
        firstname.clear();
        lastname.clear();
        gebdat.clear();
        natio.clear();
        email.clear();
        position.clear();
        marktvalue.clear();
        mannschaftid.clear();
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