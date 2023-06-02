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
    private boolean isConfirmButtonPressed = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myPersonList = PersonDAO.getPersons();
        listProperty.set(FXCollections.observableArrayList(myPersonList));

        // Disable all buttons except the "Show" button
        confirmbtn.setDisable(true);
        forwardbtn.setDisable(true);
        backbtn.setDisable(true);
        deletebtn.setDisable(true);
        confirmbtn1.setDisable(true);

        // Enable the "Show" button
        showbtn.setDisable(false);
    }

    @FXML
    private void handleConfirmButton(ActionEvent event) {
        if (isNewEntry && isConfirmButtonPressed) {
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
            isConfirmButtonPressed = false;
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

            isConfirmButtonPressed = false;
        }
    }

    @FXML
    private void handleShowButton(ActionEvent event) {
        myPersonList = PersonDAO.getPersons(); // Update the list of persons
        listProperty.set(FXCollections.observableArrayList(myPersonList));

        if (!myPersonList.isEmpty()) {
            currentIndex.set(0); // Show the first record
            showPersonDetails(myPersonList.get(0)); // Show the details of the first person
        } else {
            currentIndex.set(-1); // Set the currentIndex to -1 if the list is empty
            clearPersonDetails();
        }
        isNewEntry = false;

        // Disable the "Show" button after it has been clicked
        showbtn.setDisable(true);
        // Enable other buttons
        confirmbtn.setDisable(false);
        forwardbtn.setDisable(false);
        backbtn.setDisable(false);
        deletebtn.setDisable(false);
        confirmbtn1.setDisable(false);

        // Enable/disable navigation buttons based on the current index
        BooleanBinding isFirstPerson = currentIndex.isEqualTo(0);
        BooleanBinding isLastPerson = currentIndex.isEqualTo(myPersonList.size() - 1);
        backbtn.disableProperty().bind(isFirstPerson);
        forwardbtn.disableProperty().bind(isLastPerson);
    }

    @FXML
    private void handleForwardButton(ActionEvent event) {
        if (currentIndex.get() < myPersonList.size() - 1) {
            currentIndex.set(currentIndex.get() + 1);
            showPersonDetails(myPersonList.get(currentIndex.get())); // Aktualisiere die angezeigten Details
            isNewEntry = false;
            isConfirmButtonPressed = false;
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        if (currentIndex.get() > 0) {
            currentIndex.set(currentIndex.get() - 1);
            showPersonDetails(myPersonList.get(currentIndex.get())); // Aktualisiere die angezeigten Details
            isNewEntry = false;
            isConfirmButtonPressed = false;
        }
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

        // Disable all buttons except the "Confirm" button
        confirmbtn.setDisable(false);
        forwardbtn.setDisable(true);
        backbtn.setDisable(true);
        deletebtn.setDisable(true);
        confirmbtn1.setDisable(true);

        // Set the isConfirmButtonPressed flag to true
        isConfirmButtonPressed = true;
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
