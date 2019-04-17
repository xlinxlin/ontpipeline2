package mbio.ncct.ont.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mbio.ncct.ont.MainApp;

public class PipelineOverviewController {
  
  @FXML
  private Button btnBasecalling;
  
  private MainApp mainApp;

  private Stage dialogStage;
  private boolean okClicked = false;
  
  @FXML
  private CheckBox cBasecalling;
  @FXML
  private CheckBox cReadsFilter;
  @FXML
  private CheckBox cAssembly;
  @FXML
  private CheckBox cPolishing;
  @FXML
  public ChoiceBox<String> cbFlowcellId = new ChoiceBox<String>() ;
  @FXML
  public ChoiceBox<String> cbKitNumber = new ChoiceBox<String>() ;
  @FXML
  public ChoiceBox<String> cbMode = new ChoiceBox<String>() ;
  @FXML
  public ChoiceBox<String> cbMethod = new ChoiceBox<String>() ;
  @FXML
  public TextField tfWorkspace;
  @FXML
  public TextField tfThreads;
  
  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   * @throws IOException 
   */
  @FXML
  private void initialize() throws IOException {   
    ObservableList<String> olFlowcellIds = FXCollections.observableArrayList(getFlowcellIds());
    cbFlowcellId.setItems(olFlowcellIds);
    cbFlowcellId.getSelectionModel().selectFirst();
    //cbFlowcellId.getSelectionModel().
    //cbFlowcellId.getSelectionModel().se
    ObservableList<String> olKitNumbers = FXCollections.observableArrayList(getKitNumbers());
    cbKitNumber.setItems(olKitNumbers);
    //cbKitNumber.getSelectionModel().selectFirst();
    if(olKitNumbers.contains("SQK-LSK109")) {
      cbKitNumber.getSelectionModel().select("SQK-LSK109");
    } else {
      cbKitNumber.getSelectionModel().selectFirst();
    }
    ArrayList<String> alMode = new ArrayList<String>();
    alMode.add("conservative");
    alMode.add("normal");
    alMode.add("bold");
    ObservableList<String> olMode = FXCollections.observableArrayList(alMode);
    cbMode.setItems(olMode);
    
    ArrayList<String> alMethod = new ArrayList<String>();
    alMethod.add("Long-read-only assembly");
    alMethod.add("Hybrid assembly");
    ObservableList<String> olMethod = FXCollections.observableArrayList(alMethod);
    cbMethod.setItems(olMethod);
    
    cBasecalling.setSelected(true);
    cReadsFilter.setSelected(true);
    cAssembly.setSelected(true);
    cPolishing.setSelected(true);
    
    tfThreads.setText("4");
  }

  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param mainApp
   */
  public void setMainApp(MainApp mainApp) {
      this.mainApp = mainApp;

      // Add observable list data to the table
      //personTable.setItems(mainApp.getPersonData());
  }
  
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }
  
  public boolean isOkClicked() {
    return okClicked;
  }
  
  @FXML
  private void handleAdvancedBasecalling() {
      //boolean okClicked = mainApp.handleAdvancedBasecalling();
    mainApp.handleAdvancedBasecalling();
      /*
      if (okClicked) {
          //mainApp.getPersonData().add(tempPerson);
        System.out.println("OK");
      }
      */
  }
  
  @FXML
  private void handleAdvancedReadsFilter() {
      //boolean okClicked = mainApp.handleAdvancedBasecalling();
    mainApp.handleAdvancedReadsFilter();
      /*
      if (okClicked) {
          //mainApp.getPersonData().add(tempPerson);
        System.out.println("OK");
      }
      */
  }
  
  @FXML
  private void handleStartPipeline() {
      //boolean okClicked = mainApp.handleAdvancedBasecalling();
    mainApp.handleStartPipeline();
      /*
      if (okClicked) {
          //mainApp.getPersonData().add(tempPerson);
        System.out.println("OK");
      }
      */
  }
  
  @FXML
  private void handleAdvancedAssembly() {
      //boolean okClicked = mainApp.handleAdvancedBasecalling();
    mainApp.handleAdvancedAssembly();
      /*
      if (okClicked) {
          //mainApp.getPersonData().add(tempPerson);
        System.out.println("OK");
      }
      */
  }
  
  @FXML
  private void handleAdvancedPolishung() {
    mainApp.handleAdvancedPolishing();
  }
  
  private ArrayList<String> getFlowcellIds() throws IOException {
    String s = null;
    ArrayList<String> arFlowcellIds = new ArrayList<String>();
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_basecaller --print_workflows | awk 'NR>2 {print $1}' | sort | uniq" });
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    while ((s = stdInput.readLine()) != null ) {
      if (s.isEmpty() == false) {
        arFlowcellIds.add(s);
      }
    }
    return arFlowcellIds;
  }
  
  private ArrayList<String> getKitNumbers() throws IOException {
    String s = null;
    ArrayList<String> arKitNumbers = new ArrayList<String>();
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_basecaller --print_workflows | awk 'NR>2 {print $2}' | sort | uniq" });
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    while ((s = stdInput.readLine()) != null ) {
      if (s.isEmpty() == false) {
        arKitNumbers.add(s);
      }
    }
    return arKitNumbers;
  }
  
  @FXML
  private void selectWorkspaceHandler() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    
    File[] f = null;
    
    if (selectedDirectory != null) {
      tfWorkspace.setText(selectedDirectory.toString());
      f = selectedDirectory.listFiles();
    } 
    
    //gsModel.setWorkspace(tfWorkspace.getText());
    //File[] f = selectedDirectory.listFiles();
    if (f!=null) {
      String extension = f[f.length-1].getName().contains(".") ? f[f.length-1].getName().split("\\.")[1].toLowerCase() : "";
      if(!extension.equals("fast5")&&!extension.equals("fastq")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Can not find .fast5 or .fastq file in this folder.");
        alert.showAndWait();
        tfWorkspace.setText("");
      }
    }
  }
}

