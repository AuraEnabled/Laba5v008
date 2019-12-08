package sample;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.ENUMs.Procedures;
import sample.classes.Patient;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

//import java.awt.*;

public class Main extends Application {

    TableView<Patient> table;
    TextField nameInput, idInput, priceInput;
    ChoiceBox<String> choiceBoxInput;
    private Procedures procedure;       //ENUM Procedures
    private String procedureStr;
    @Override
    public void start(Stage primaryStage) throws Exception{

//        Name column
        TableColumn<Patient, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

//        ID column
        TableColumn<Patient, Integer> idColumn = new TableColumn<>("Номер карты");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

//        Procedure column      @params risky
        TableColumn<Patient, String> procedureColumn = new TableColumn<>("Процедура");
        procedureColumn.setMinWidth(100);
        procedureColumn.setCellValueFactory(new PropertyValueFactory<>("procedure"));

//        Price column
        TableColumn<Patient, Double> priceColumn = new TableColumn<>("Цена");
        priceColumn.setMinWidth(98);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

//        Name input
        nameInput = new TextField();
        nameInput.setPromptText("Имя");
        nameInput.setMinWidth(200);

//        ID input
        idInput = new TextField();
        idInput.setPromptText("Номер карточки");
        idInput.setMinWidth(100);

//        Choice Box procedure input
        choiceBoxInput = new ChoiceBox<>();
        choiceBoxInput.getItems().add(procedure.Кастрация.toString());
        choiceBoxInput.getItems().add(procedure.Чистка.toString());
        choiceBoxInput.getItems().add(procedure.Пломбирование.toString());
        choiceBoxInput.getItems().add(procedure.Протезирование.toString());
        choiceBoxInput.getItems().add(procedure.Кремация.toString());
        choiceBoxInput.getSelectionModel().select(0);

//        Price input
        priceInput = new TextField();
        priceInput.setPromptText("Цена");
        priceInput.setMinWidth(100);


//        The add button
        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {addButtonOnClick(); });

//        The delete button
        Button delButton = new Button("Удалить");
        delButton.setOnAction(e -> delButtonOnClick());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, idInput, choiceBoxInput, priceInput, addButton, delButton);

        table = new TableView<>();
        table.setItems(getPatient());
        table.getColumns().addAll(nameColumn,idColumn, procedureColumn, priceColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, table);

//        Scene release
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Инто-сана");
        primaryStage.setScene(new Scene(vBox, 800, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    //    ObservableList
    public ObservableList<Patient> getPatient(){
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        patients.add(new Patient("Жмышенко Валерий",            1, Procedures.Кастрация.toString(),        200));
        patients.add(new Patient("Ананасов Александер",         2, Procedures.Кремация.toString(),         150));
        patients.add(new Patient("Белоглазов Анатолий",         3, Procedures.Пломбирование.toString(),    500));
        patients.add(new Patient("Ткаченко Григорий" ,          4 , Procedures.Протезирование.toString(),  300));
        patients.add(new Patient("Трипавловских Александер",    5, Procedures.Чистка.toString(),           125));
        return patients;
    }

    //    Add button clicked
    public void addButtonOnClick(){
        Patient patient = new Patient();
        patient.setName(nameInput.getText());
        patient.setId(parseInt(idInput.getText()));
        patient.setProcedure(getChoice(choiceBoxInput));
        patient.setPrice(parseDouble(priceInput.getText()));
        table.getItems().add(patient);
        nameInput.clear();
        idInput.clear();
        choiceBoxInput.getSelectionModel().select(0);
        priceInput.clear();
    }

    //    Delete button clicked
    public void delButtonOnClick(){
        ObservableList<Patient> selectedPatients, allPatients;
        allPatients = table.getItems();
        selectedPatients = table.getSelectionModel().getSelectedItems();
        selectedPatients.forEach(allPatients::remove);
    }
    //    Getting the value of ChoiceBox
    private String getChoice(ChoiceBox<String> choiceBoxInput) {
        procedureStr = choiceBoxInput.getValue();
        return procedureStr;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
