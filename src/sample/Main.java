package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.ENUMs.Paid;
import sample.ENUMs.Procedures;
import sample.classes.Patient;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

//import java.awt.*;

public class Main extends Application {

    private TableView<Patient> table;
    private TextField nameInput, idInput, priceInput, debtInput;
    private ChoiceBox<String> proceduresChoiceBoxInput, paidChoiceBoxInput;

    private Procedures procedure;       //ENUM Procedures
    private String procedureStr;

    private Paid paid;                  //ENUM Paid
    private String paidStr;

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
        procedureColumn.setMinWidth(150);
        procedureColumn.setCellValueFactory(new PropertyValueFactory<>("procedure"));

//        Price column
        TableColumn<Patient, Double> priceColumn = new TableColumn<>("Цена");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

//        Paid column      @params risky
        TableColumn<Patient, String> paidColumn = new TableColumn<>("Статус");
        paidColumn.setMinWidth(150);
        paidColumn.setCellValueFactory(new PropertyValueFactory<>("paid"));

//        Debt column
        TableColumn<Patient, Double> debtColumn = new TableColumn<>("Долг");
        debtColumn.setMinWidth(100);
        debtColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));

//        Name input
        nameInput = new TextField();
        nameInput.setPromptText("Имя");
        nameInput.setMinWidth(200);

//        ID input
        idInput = new TextField();
        idInput.setPromptText("Номер карточки");
        idInput.setMinWidth(100);

//        Choice Box procedure input
        proceduresChoiceBoxInput = new ChoiceBox<>();
        proceduresChoiceBoxInput.getItems().add(Procedures.Кастрация.toString());
        proceduresChoiceBoxInput.getItems().add(Procedures.Чистка.toString());
        proceduresChoiceBoxInput.getItems().add(Procedures.Пломбирование.toString());
        proceduresChoiceBoxInput.getItems().add(Procedures.Протезирование.toString());
        proceduresChoiceBoxInput.getItems().add(Procedures.Кремация.toString());
        proceduresChoiceBoxInput.getSelectionModel().select(0);

//        Price input
        priceInput = new TextField();
        priceInput.setPromptText("Цена");
        priceInput.setMinWidth(100);

//        Choice Box is paid
        paidChoiceBoxInput = new ChoiceBox<>();
        paidChoiceBoxInput.getItems().add(Paid.True.toString());
        paidChoiceBoxInput.getItems().add(Paid.False.toString());
        paidChoiceBoxInput.getSelectionModel().select(0);

//        Debt input
        debtInput = new TextField();
        debtInput.setPromptText("Долг");
        debtInput.setMinWidth(100);


//        The add button
        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {addButtonOnClick(); });

//        The delete button
        Button delButton = new Button("Удалить");
        delButton.setOnAction(e -> delButtonOnClick());

        HBox hBoxLVL1 = new HBox();
        hBoxLVL1.setPadding(new Insets(10, 10, 10, 10));
        hBoxLVL1.setSpacing(10);
        hBoxLVL1.getChildren().add(nameInput);
        hBoxLVL1.getChildren().add(idInput);
        hBoxLVL1.getChildren().add(proceduresChoiceBoxInput);
        hBoxLVL1.getChildren().add(priceInput);
        hBoxLVL1.getChildren().add(paidChoiceBoxInput);
        hBoxLVL1.getChildren().add(debtInput);
//        hBoxLVL1.getChildren().add(addButton);
//        hBoxLVL1.getChildren().add(delButton);
//        hBoxLVL1.getChildren().addAll(nameInput, idInput, proceduresChoiceBoxInput, priceInput, paidChoiceBoxInput, debtInput, addButton, delButton);

        HBox hBoxLVL2 = new HBox();
        hBoxLVL2.setPadding(new Insets(10, 10, 10, 10));
        hBoxLVL2.setSpacing(10);
        hBoxLVL2.getChildren().add(addButton);
        hBoxLVL2.getChildren().add(delButton);

        table = new TableView<>();
        table.setItems(getPatient());
        table.getColumns().addAll(nameColumn,idColumn, procedureColumn, priceColumn, paidColumn, debtColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBoxLVL1, hBoxLVL2, table);

//        Scene release
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Инто-сана");
        primaryStage.setScene(new Scene(vBox, 940, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    //    ObservableList
    public ObservableList<Patient> getPatient(){
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        patients.add(new Patient("Жмышенко Валерий",            1, Procedures.Кастрация.toString(),        200, Paid.True.toString(), 0));
        patients.add(new Patient("Ананасов Александер",         2, Procedures.Кремация.toString(),         150, Paid.True.toString(),0));
        patients.add(new Patient("Белоглазов Анатолий",         3, Procedures.Пломбирование.toString(),    500, Paid.False.toString(), 35));
        patients.add(new Patient("Ткаченко Григорий" ,          4, Procedures.Протезирование.toString(),  300, Paid.True.toString(), 0));
        patients.add(new Patient("Трипавловских Александер",    5, Procedures.Чистка.toString(),           125, Paid.False.toString(), 70));
        return patients;
    }

    //    Add button clicked
    public void addButtonOnClick(){
        Patient patient = new Patient();
        patient.setName(nameInput.getText());
        patient.setId(parseInt(idInput.getText()));
        patient.setProcedure(getProcedureChoice(proceduresChoiceBoxInput));
        patient.setPrice(parseDouble(priceInput.getText()));
        patient.setPaid(getPaidChoice(paidChoiceBoxInput));
        patient.setDebt(parseDouble(debtInput.getText()));
        table.getItems().add(patient);
        nameInput.clear();
        idInput.clear();
        proceduresChoiceBoxInput.getSelectionModel().select(0);
        priceInput.clear();
        paidChoiceBoxInput.getSelectionModel().select(0);
        debtInput.clear();
    }

    //    Delete button clicked
    public void delButtonOnClick(){
        ObservableList<Patient> selectedPatients, allPatients;
        allPatients = table.getItems();
        selectedPatients = table.getSelectionModel().getSelectedItems();
        selectedPatients.forEach(allPatients::remove);
    }
    //    Getting the value of procedure ChoiceBox
    private String getProcedureChoice(ChoiceBox<String> proceduresChoiceBoxInput) {
        procedureStr = proceduresChoiceBoxInput.getValue();
        return procedureStr;
    }
    //    Getting the value of paid ChoiceBox
    private String getPaidChoice(ChoiceBox<String> paidChoiceBoxInput){
        paidStr = paidChoiceBoxInput.getValue();
        return paidStr;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
