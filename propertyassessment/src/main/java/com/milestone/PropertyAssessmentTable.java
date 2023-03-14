/* Name: Salah Mohamed
   Date: 12/03/23
   ID: 3044504
   Course: CMPT 305 Milestone 2
   Program: Run a JavaFX program
 */

 // Importing necessary libraries
package com.milestone;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.*;

/*Java class that extends the Application class and is run as a JavaFX application. */
public class PropertyAssessmentTable extends Application {
    /*/ Initializing an ObservableList to hold PropertyAssessment objects, 
    and a TableView to display them */
    private final ObservableList<PropertyAssessment> data = FXCollections.observableArrayList();
    private final TableView<PropertyAssessment> table = new TableView<>();

    /*Initializing JavaFX UI elements for the application */
    private Label propertyAssessmentTitle;
    private Label filterDataSource;
    private Label findPropertyAssessment;
    private Label accountNumberLabel;
    private Label addressLabel;
    private Label neighbourhoodLabel;
    private Label assessedValueLabel;
    private Label assessmentClassLabel;

    private TextField accountNumberField;
    private TextField addressField;
    private TextField neighbourhoodField;
    private TextField minValueField;
    private TextField maxValueField;

    private Button readDataButton;
    private Button searchButton;
    private Button resetButton;

    private MenuButton dataSourceMenuButton;
    private MenuButton assessmentClassButton;

    private BorderPane layout;

    private static Scene scene;

    /*Application is launching */
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        //Initializing the table
        initializeTable(data);

        //Initializing the labels
        initializeLabels();

        //Initializing the Text fields
        initializeTextFields();

        //Initializing the buttons
        initializeButtons();

        //Initializing the Menu buttons
        initializeMenuButtons();

        //Actions when button is clicked
        actionButtons();

        //Placing the layouts
        placementLayout();

        //Initializing the scene
        initializeScene(stage);
    }
    /*Create the table columns and associate them with the data properties */
    private void initializeTable(final ObservableList<PropertyAssessment> data) {

        //A column width for the table
        int COLUMN_WIDTH = 150;

        TableColumn<PropertyAssessment, String> accountNumberCol = new TableColumn<>("Account Number");
        accountNumberCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().intGetAccountNumber()));
        accountNumberCol.setPrefWidth(COLUMN_WIDTH);

        TableColumn<PropertyAssessment, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        addressCol.setPrefWidth(COLUMN_WIDTH);

        TableColumn<PropertyAssessment, String> assessedValueCol = new TableColumn<>("Assessed Value");
        assessedValueCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAssessedDollarFormat()));
        assessedValueCol.setPrefWidth(COLUMN_WIDTH);

        TableColumn<PropertyAssessment, String> assessmentClassCol = new TableColumn<>("Assessment Class");
        assessmentClassCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAssessmentClass()));
        assessmentClassCol.setPrefWidth(COLUMN_WIDTH);

        TableColumn<PropertyAssessment, String> neighbourhoodCol = new TableColumn<>("Neighbourhood");
        neighbourhoodCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNeighbourhood()));
        neighbourhoodCol.setPrefWidth(COLUMN_WIDTH);

        TableColumn<PropertyAssessment, String> locationCol = new TableColumn<>("(Latitude, Longitude)");
        locationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        locationCol.setPrefWidth(COLUMN_WIDTH);

        TableColumn<PropertyAssessment, String> garageCol = new TableColumn<>("Garage");
        garageCol.setCellValueFactory(new PropertyValueFactory<>("garage"));
        garageCol.setPrefWidth(COLUMN_WIDTH);

        //Displaying the tabular data
        table.getColumns().addAll(accountNumberCol, addressCol, assessedValueCol, assessmentClassCol,
         neighbourhoodCol, locationCol, garageCol);
    }
    /*Setting up the labels for the filter boxes, return the labels as an arraylist */
    private List<Label> initializeLabels() {

        propertyAssessmentTitle = new Label("Edmonton Property Assessments (2022)");
        Font propertyAssessmentCurrentFont = propertyAssessmentTitle.getFont();
        double propertyAssessmentFontSize = propertyAssessmentCurrentFont.getSize() + 4; // increase font size by 2
        Font propertyAssessmentNewFont = Font.font(propertyAssessmentCurrentFont.getFamily(), FontWeight.BOLD,
         FontPosture.REGULAR, propertyAssessmentFontSize);
        propertyAssessmentTitle.setFont(propertyAssessmentNewFont);

        filterDataSource = new Label("Select Data Source");
        Font dataSourceCurrentFont = filterDataSource.getFont();
        double dataSourceFontSize = dataSourceCurrentFont.getSize() + 4; // increase font size by 2
        Font dataSourceNewFont = Font.font(dataSourceCurrentFont.getFamily(), FontWeight.BOLD, 
        FontPosture.REGULAR, dataSourceFontSize);
        filterDataSource.setFont(dataSourceNewFont);

        findPropertyAssessment = new Label("Find Property Assessment");
        Font findPropertyAssessmentCurFont = findPropertyAssessment.getFont();
        double findPropertyAssessmentFontSize = findPropertyAssessmentCurFont.getSize() + 4; // increase font size by 2
        Font findPropertyAssessmentNewFont = Font.font(findPropertyAssessmentCurFont.getFamily(), FontWeight.BOLD,
         FontPosture.REGULAR, findPropertyAssessmentFontSize);
        findPropertyAssessment.setFont(findPropertyAssessmentNewFont);

        accountNumberLabel = new Label("Account Number:");
        addressLabel = new Label("Address (#suite #house street)");
        neighbourhoodLabel = new Label("Neighbourhood:");
        assessedValueLabel = new Label("Assessed Value Range:");
        assessmentClassLabel = new Label("Assessment Class:");

        return Arrays.asList(propertyAssessmentTitle, filterDataSource, findPropertyAssessment, accountNumberLabel, addressLabel, neighbourhoodLabel, assessedValueLabel, assessmentClassLabel);
    }
    /*Setting up the text fields, return the text fields as an arraylist */
    private List<TextField> initializeTextFields(){
        int TEXT_WIDTH = 100;

        accountNumberField = new TextField();
        accountNumberField.setPrefWidth(TEXT_WIDTH);

        addressField = new TextField();
        addressField.setPrefWidth(TEXT_WIDTH);

        neighbourhoodField = new TextField();
        neighbourhoodField.setPrefWidth(TEXT_WIDTH);

        minValueField = new TextField();
        minValueField.setPrefWidth(TEXT_WIDTH);
        minValueField.setPromptText("Min Value");

        maxValueField = new TextField();
        maxValueField.setPrefWidth(TEXT_WIDTH);
        maxValueField.setPromptText("Max Value");

        return Arrays.asList(accountNumberField, addressField, neighbourhoodField, minValueField, maxValueField);
    }
    /*Setting up the buttons, return the buttons as an arraylist */
    private List<Button> initializeButtons(){
        int BUTTON_WIDTH = 200;
        readDataButton = new Button("Read Data");
        readDataButton.setPrefWidth(BUTTON_WIDTH);

        searchButton = new Button("Search");
        searchButton.setPrefWidth(BUTTON_WIDTH/2);

        resetButton = new Button("Reset");
        resetButton.setPrefWidth(BUTTON_WIDTH/2);

        return Arrays.asList(readDataButton, searchButton, resetButton);
    }
    /*Setting up the menu buttons, return the menu buttons as an arraylist */
    private List<MenuButton> initializeMenuButtons(){
        int MENUBUTTON_WIDTH = 200;

        dataSourceMenuButton = new MenuButton("Select Data Source");
        dataSourceMenuButton.setPrefWidth(MENUBUTTON_WIDTH);
        dataSourceMenuButton.getItems().addAll(
        new MenuItem("CSV File"), 
        new MenuItem("Edmonton's Open Data Portal")
        );

        assessmentClassButton = new MenuButton("");
        assessmentClassButton.setPrefWidth(MENUBUTTON_WIDTH);
        assessmentClassButton.getItems().addAll(
        new MenuItem("RESIDENTIAL"), 
        new MenuItem("COMMERCIAL"),
        new MenuItem("FARMLAND"),
        new MenuItem("OTHER RESIDENTIAL")
        );

        // Add event listeners to update the text of the menuButton
        dataSourceMenuButton.getItems().get(0).setOnAction(e -> {
        dataSourceMenuButton.setText("CSV File");
        });

        dataSourceMenuButton.getItems().get(1).setOnAction(e -> {
        dataSourceMenuButton.setText("Edmonton's Open Data Portal");
        });

        assessmentClassButton.getItems().get(0).setOnAction(e -> {
            assessmentClassButton.setText("RESIDENTIAL");
        });

        assessmentClassButton.getItems().get(1).setOnAction(e -> {
            assessmentClassButton.setText("COMMERCIAL");
            });

        assessmentClassButton.getItems().get(2).setOnAction(e -> {
            assessmentClassButton.setText("FARMLAND");
        });

        assessmentClassButton.getItems().get(3).setOnAction(e -> {
        assessmentClassButton.setText("OTHER RESIDENTIAL");
        });
        return Arrays.asList(dataSourceMenuButton, assessmentClassButton);
    }
    /*Setting up the actions when buttons are clicked, return the buttons as an arraylist */
    private List<Button> actionButtons(){
        readDataButton.setOnAction(e -> {
            // Clear the existing data
            data.clear();

            // Get the selected data source from the menu button
            String dataSource = dataSourceMenuButton.getText();

            // Check the selected data source and load the data accordingly
            if (dataSource.equals("CSV File")) {
                CsvPropertyAssessmentDAO dao = new CsvPropertyAssessmentDAO("C:\\Users\\salah\\OneDrive\\Documents\\CMPT305-MilestoneProject\\propertyassessment\\src\\main\\java\\com\\milestone\\q7d6-ambg.csv");
                List<PropertyAssessment> propertyAssessments;
                try {
                    propertyAssessments = dao.getAll();
                    data.addAll(propertyAssessments);
                    table.setItems(data);
                } catch (IOException | InterruptedException e1) {
                    e1.printStackTrace();
                }

            } else if (dataSource.equals("Edmonton's Open Data Portal")) {
                ApiPropertyAssessmentDAO dao = new ApiPropertyAssessmentDAO("https://data.edmonton.ca/resource/q7d6-ambg.csv");
                List<PropertyAssessment> propertyAssessments;
                try {
                    propertyAssessments = dao.getAll();
                    data.addAll(propertyAssessments);
                    
                    table.setItems(data);
                } catch (IOException | InterruptedException e1) {
                    e1.printStackTrace();
                }
                
            }
            dataSourceMenuButton.setText(dataSource);
        });

        searchButton.setOnAction(e -> {
            String searchAddress = addressField.getText().trim().toLowerCase();
            String searchAccountNumber = accountNumberField.getText().trim().toLowerCase();
            String searchNeighbourhood = neighbourhoodField.getText().trim().toLowerCase();
            String searchMinValue = minValueField.getText().trim().toLowerCase();
            String searchMaxValue = maxValueField.getText().trim().toLowerCase();
            String assessmentClasses = assessmentClassButton.getText().trim().toLowerCase();

            if (searchAddress.isEmpty() && searchAccountNumber.isEmpty() && 
            searchNeighbourhood.isEmpty() && searchMinValue.isEmpty() && 
            searchMaxValue.isEmpty() && assessmentClasses.isEmpty()) {
                // If the search term is empty, show all data
                table.setItems(data);
            } else {
                // Filter the data based on the search term
                ObservableList<PropertyAssessment> filteredData = FXCollections.observableArrayList();
                for (PropertyAssessment assessment : data) {
                    String assessed = assessment.getAssessed();
                    assessed = assessed.replaceAll("\"", "");
                    if (assessment.getAddress().toLowerCase().contains(searchAddress) && 
                        assessment.getAccountNumber().contains(searchAccountNumber) && 
                        assessment.getNeighbourhood().toLowerCase().contains(searchNeighbourhood.toLowerCase())
                        && (searchMinValue.isEmpty() || Integer.parseInt(assessed) >= Integer.parseInt(searchMinValue))
                        && (searchMaxValue.isEmpty() || Integer.parseInt(assessed) <= Integer.parseInt(searchMaxValue))
                        && assessment.getAssessmentClass().toLowerCase().contains(assessmentClasses.toLowerCase())) {
                        filteredData.add(assessment);

                    }
                }
                // Show an alert dialog box message if the search did not produce results
                table.setItems(filteredData);
                if (filteredData.isEmpty()) {
                    table.setItems(data);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Oops, did not find anything!");
                    alert.showAndWait();
                }
            }
        });
        // Reset the tex fields and display the default result
        resetButton.setOnAction(event -> {
            accountNumberField.setText("");
            addressField.setText("");
            neighbourhoodField.setText("");
            minValueField.setText("");
            maxValueField.setText("");
            assessmentClassButton.setText("");
            table.setItems(data);
        });
        return Arrays.asList(readDataButton, searchButton, resetButton);
    }
    /*Setting up the placement of the layout, return the layout */
    private BorderPane placementLayout(){
        HBox hBoxSearchResetButton = new HBox(10);
        hBoxSearchResetButton.getChildren().addAll(searchButton, resetButton);

        HBox hboxValues = new HBox(10);
        hboxValues.getChildren().addAll(minValueField, maxValueField);

        // Center the label in the top of the layout
        BorderPane.setAlignment(propertyAssessmentTitle, Pos.CENTER);
        layout = new BorderPane();

        layout.setTop(propertyAssessmentTitle);
        layout.setPadding(new Insets(10));

        // Create a VBox to hold the filter boxes
        VBox filterBox = new VBox(10, filterDataSource, dataSourceMenuButton, readDataButton,
        findPropertyAssessment, accountNumberLabel, accountNumberField, addressLabel, addressField,
        neighbourhoodLabel, neighbourhoodField, assessmentClassLabel, assessmentClassButton, assessedValueLabel, hboxValues, hBoxSearchResetButton);

        layout.setLeft(filterBox);
        layout.setPadding(new Insets(40));

        // Add the table to the center of the layout
        layout.setCenter(table);
        layout.setPadding(new Insets(20));
        return layout;

    }
    /*Create the scene and show the stage*/
    private void initializeScene(Stage stage){
         Scene scene = new Scene(layout);
         stage.setScene(scene);
         stage.setTitle("Property Assessments");
         stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PropertyAssessmentTable.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}