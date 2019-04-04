package mbio.ncct.ont.view;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class AssemblySettingsController {
  
  @FXML
  public TextField tfRead1;
  @FXML
  public TextField tfRead2;
  @FXML
  //public final ChoiceBox<String> cbMode = new ChoiceBox<>(FXCollections.observableArrayList("conservative", "normal", "bold"));
  public ChoiceBox<String> cbMode = new ChoiceBox<String>();
  @FXML
  public ChoiceBox<String> cbMethod;
  @FXML
  public CheckBox cVCF;
  @FXML
  public ChoiceBox<String> cbTimes;
  @FXML
  public CheckBox cAssemblySettings;
  @FXML
  public CheckBox cBusco;
  @FXML
  public ChoiceBox<String> cbDatabase;
 
  
  
  public AssemblySettingsController() {
    
  }
  
  @FXML
  private void initialize() {
    
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
    
    ArrayList<String> alTimes = new ArrayList<String>();
    alTimes.add("0");
    alTimes.add("1");
    alTimes.add("2");
    alTimes.add("3");
    alTimes.add("4");
    ObservableList<String> olTimes = FXCollections.observableArrayList(alTimes);
    cbTimes.setItems(olTimes);
    
    ArrayList<String> alDatabase = new ArrayList<String>();
    alDatabase.add("Bacteria");
    alDatabase.add("Proteobacteria");
    alDatabase.add("Rhizobiales");
    alDatabase.add("Beta_proteobacteria");
    alDatabase.add("Gamma_proteobacteria");
    alDatabase.add("Enterobacteriales");
    alDatabase.add("Delta_Epsilon_proteobacteria");
    alDatabase.add("Actinobacteria");
    alDatabase.add("Cyanobacteria");
    alDatabase.add("Firmicutes");
    alDatabase.add("Clostridia");
    alDatabase.add("Lactobacillales");
    alDatabase.add("Bacillales");
    alDatabase.add("Enterobacteriales");
    alDatabase.add("Bacteroidetes");
    alDatabase.add("Spirochaetes");
    alDatabase.add("Tenericutes");
    ObservableList<String> olDatabase = FXCollections.observableArrayList(alDatabase);
    cbDatabase.setItems(olDatabase);
    
  }
  
  
  @FXML
  private void selectRead1Handler() {
    
    
    //DirectoryChooser directoryChooser = new DirectoryChooser();
    //File selectedDirectory = directoryChooser.showDialog(null);
    FileChooser fileChooser = new FileChooser();
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      tfRead1.setText(selectedFile.toString()); 
    }
    
    //gsModel.setWorkspace(tfWorkspace.getText());
    //System.out.println(gsModel.getWorkspace());
    //System.out.println(pl.getWorkspace());
  }
  
  @FXML
  private void selectRead2Handler() {
    FileChooser fileChooser = new FileChooser();
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      tfRead2.setText(selectedFile.toString()); 
    }
  }
  

}
