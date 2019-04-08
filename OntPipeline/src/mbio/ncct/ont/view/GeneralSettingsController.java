package mbio.ncct.ont.view;

import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import mbio.ncct.ont.model.GeneralSettingsModel;


public class GeneralSettingsController {

  @FXML 
  public Label lWorkspace;
  @FXML 
  public TextField tfWorkspace;
  @FXML 
  public Label lThreads;
  @FXML 
  public TextField tfThreads;
  @FXML 
  public Label lBarcode;
  @FXML 
  public TextField tfBarcode;
  @FXML
  public CheckBox cAdapterTrimming;
  
  private GeneralSettingsModel gsModel = new GeneralSettingsModel();;

  
  public GeneralSettingsController() {
  
  }
  
  @FXML
  private void initialize() throws IOException {
    //Set the default threads value to the max number of threads on this computer.
    String threads = null;
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "cat /proc/cpuinfo | grep processor | wc -l" });
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    while ((threads = stdInput.readLine()) != null) {
      tfThreads.setText(threads.toString());;
      gsModel.setThreads(threads);
    }
    while ((threads = stdError.readLine()) != null) {
        System.out.println(threads);
    }
    
    //Set the tool tips.
    /*
    Tooltip ttTfWorkspace = new Tooltip();
    ttTfWorkspace.setText("Select your .fast5/.fastq file folder.");
    tfWorkspace.setTooltip(ttTfWorkspace);
    
    Tooltip ttTfThreads = new Tooltip();
    ttTfThreads.setText("Set the threads you want to use during the run.");
    tfThreads.setTooltip(ttTfThreads);
    
    Tooltip ttTfBarcode = new Tooltip();
    ttTfBarcode.setText("Set which barcode(s) you want to analysis, e.g.: 1,2,3,4, or left it blank for all barcodes.");
    tfBarcode.setTooltip(ttTfBarcode); 
    */
  }
  
  @FXML
  private void selectWorkspaceHandler() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory != null) {
      tfWorkspace.setText(selectedDirectory.toString()); 
    } 
    
    //gsModel.setWorkspace(tfWorkspace.getText());
    File[] f = selectedDirectory.listFiles();
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
  
  public String checkFileExtension(String f) {
    File file = new File(f);
    File[] listFiles = file.listFiles();
    return listFiles[0].getName().split("\\.")[1];
  }
}
