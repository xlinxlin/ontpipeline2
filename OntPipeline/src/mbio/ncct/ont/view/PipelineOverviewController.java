package mbio.ncct.ont.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

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

/**
 * This is the controller of the pipeline overview settings.
 *
 * @author Yan Zhou
 * created on 2019/05/14
 */
public class PipelineOverviewController {
  
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
  @FXML
  public Button btnBasecalling;
  @FXML
  public Button btnReadsFilter;
  @FXML
  public Button btnAssembly;
  @FXML
  public Button btnPolishing;
  @FXML
  public TextField tfSelectedBarcode;
  
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
      mainApp.setKitNumber(cbKitNumber.getSelectionModel().getSelectedItem());
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
    
    tfThreads.setText("8");
    
    cBasecalling.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cBasecalling.isSelected()) {
        cbFlowcellId.setDisable(false);
        cbKitNumber.setDisable(false);
        btnBasecalling.setDisable(false);
        mainApp.setIfBasecalling(true);
      } else {
        cbFlowcellId.setDisable(true);
        cbKitNumber.setDisable(true);
        btnBasecalling.setDisable(true);
        mainApp.setIfBasecalling(false);
      }
    });
    
    cReadsFilter.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cReadsFilter.isSelected()) {
        btnReadsFilter.setDisable(false);
        mainApp.setIfReadsFilter(true);
      } else {
        btnReadsFilter.setDisable(true);
        mainApp.setIfReadsFilter(false);
      }
    });
    
    cAssembly.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cAssembly.isSelected()) {
        cbMode.setDisable(false);
        cbMethod.setDisable(false);
        btnAssembly.setDisable(false);
        mainApp.setIfAssembly(true);
      } else {
        cbMode.setDisable(true);
        cbMethod.setDisable(true);
        btnAssembly.setDisable(true);
        mainApp.setIfAssembly(false);
      }
    });
    
    cPolishing.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cPolishing.isSelected()) {
        btnPolishing.setDisable(false);
        mainApp.setIfPolishing(true);
      } else {
        btnPolishing.setDisable(true);
        mainApp.setIfPolishing(false);
      }
    });
    
    cbFlowcellId.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      mainApp.setFlowcellId(newValue);
    });
    
    cbKitNumber.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      mainApp.setKitNumber(newValue);
    });
    
    cbMode.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      mainApp.setMode(newValue);
    });
    
    cbMethod.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      mainApp.setMethod(newValue);
    });
    
    tfWorkspace.textProperty().addListener((observable, oldValue, newValue) -> {
      mainApp.setWorkspace(newValue);
    });
    
    tfThreads.textProperty().addListener((observable, oldValue, newValue) -> {
      mainApp.setThreads(newValue);
    });
    
    tfSelectedBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
      mainApp.setSelectedBarcode(newValue);
    });
  }

  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param mainApp
   */
  public void setMainApp(MainApp mainApp) {
      this.mainApp = mainApp;
  }
  
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }
  
  public boolean isOkClicked() {
    return okClicked;
  }
  
  @FXML
  private void handleAdvancedBasecalling() {
    mainApp.handleAdvancedBasecalling();
  }
  
  @FXML
  private void handleAdvancedReadsFilter() {
    mainApp.handleAdvancedReadsFilter();
  }
  
  @FXML
  private void handleStartPipeline() throws IOException {
    mainApp.handleStartPipeline();
  }
  
  @FXML
  private void handleAdvancedAssembly() {
    mainApp.handleAdvancedAssembly();
  }
  
  @FXML
  private void handleAdvancedPolishung() {
    mainApp.handleAdvancedPolishing();
  }
  
  private ArrayList<String> getFlowcellIds() throws IOException {
    String s = null;
    ArrayList<String> arFlowcellIds = new ArrayList<String>();
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_basecaller --print_workflows | awk 'NR>2 {print $1}' | sort | uniq" });
    //Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_basecaller --print_workflows | awk 'NR>2 {print $1}' | sort | uniq" });
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
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_basecaller --print_workflows | awk 'NR>2 {print $2}' | sort | uniq" });
    //Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_basecaller --print_workflows | awk 'NR>2 {print $2}' | sort | uniq" });
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
      List<String> lF = new ArrayList<>();
      
      for (int i = 0; i < f.length; i++) {
        lF.add(f.toString());
        if (f[i].isFile()) {
          System.out.println("File " + f[i].getName());
        } else if (f[i].isDirectory()) {
          //System.out.println("Directory " + f[i].getName());
          if (f[i].getName().substring(0, 1).matches("[0-9]")) {
            System.out.println("matches");
          }
        }
      }
      
      for (int i=0;i<lF.size();i++) {
        //lF.
      }
      
    } 
    
    //System.out.println("hello world");
    
    //gsModel.setWorkspace(tfWorkspace.getText());
    //File[] f = selectedDirectory.listFiles();
    /*
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
    */
    /*
    if (f!=null) {
      for(int i=0;i<f.length;i++) {
        System.out.println(f[i].getName());
      }
    }
    */
  }
}

