package mbio.ncct.ont.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.controlsfx.dialog.Wizard;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import mbio.ncct.ont.model.GeneralSettingsModel;
import mbio.ncct.ont.model.Pipeline;
import javafx.scene.control.DialogPane;

public class GeneralSettingsController {

 //private MainApp mainApp;
  @FXML 
  public TextField tfWorkspace;
  @FXML 
  public TextField tfThreads;
  @FXML 
  public TextField tfBarcode;
  @FXML
  public CheckBox cAdapterTrimming;
  
  private String fileExtension;
  
  private GeneralSettingsModel gsModel = new GeneralSettingsModel();;

  
  public GeneralSettingsController() {
  
  }
  
  @FXML
  private void initialize() throws IOException {
    //System.out.println("ini");
    //ValidationSupport vsWorkspace = new ValidationSupport();
    //ValidationSupport vsThreads = new ValidationSupport();
    //vs.registerValidator(workspace, Validator.createEmptyValidator("Text is Required"));
    //vs.registerValidator(threads, Validator.createEmptyValidator("Text is Required"));
    //vsWorkspace.registerValidator(tfWorkspace, Validator.createEmptyValidator("EMPTY!"));
    //vsThreads.registerValidator(tfThreads, Validator.createEmptyValidator("EMPTY!"));
    
    /*
    vsWorkspace.validationResultProperty().addListener((o, oldValue, newValue) ->
      System.out.println(newValue.getMessages().toString())
    );
    */
    
   
    /*
    vsWorkspace.validationResultProperty().addListener((o, oldValue, newValue) -> {
      System.out.println(newValue.getMessages().toString());
      if (!newValue.getMessages().isEmpty()) {
         
      }
      
    });
    */
  
    //Wizard wiz = new Wizard();
    //wiz.invalidProperty().bind(workspace.textProperty().isEmpty());
    String threads = null;
    //String[] commands = {"cat /proc/cpuinfo ","grep processor ","wc -l "};
    //Process p = Runtime.getRuntime().exec(commands);
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "cat /proc/cpuinfo | grep processor | wc -l" });
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

    // read the output from the command
    //System.out.println("Here is the standard output of the command:\n");
    while ((threads = stdInput.readLine()) != null) {
        //System.out.println(s);
      tfThreads.setText(threads.toString());;
      gsModel.setThreads(threads);
    }
    
    // read any errors from the attempted command
    //System.out.println("Here is the standard error of the command (if any):\n");
    while ((threads = stdError.readLine()) != null) {
        System.out.println(threads);
    }
    
    //System.exit(0);
    
    
    
    
    
  }
  
  
  
  /*
  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;

    // Add observable list data to the table
    //personTable.setItems(mainApp.getPersonData());
  }
  */
  
  @FXML
  private void selectWorkspaceHandler() {
    
    
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory != null) {
      tfWorkspace.setText(selectedDirectory.toString()); 
    } 
    
    gsModel.setWorkspace(tfWorkspace.getText());
    //System.out.println(gsModel.getWorkspace());
    //System.out.println(pl.getWorkspace());
    //System.out.println(selectedDirectory.listFiles().toString());
    File[] f = selectedDirectory.listFiles();
    //System.out.println(f[f.length-1].getName().split("\\.")[1]);
  }
  
  @FXML
  private void checkBoxHandler() {
    /*
    ValidationSupport vs = new ValidationSupport();
    if (checkboxtest.isSelected() == true) {
      workspace.setDisable(true);
    }
    */
  }
  
  public String checkFileExtension(String f) {
    File file = new File(f);
    File[] listFiles = file.listFiles();
    return listFiles[0].getName().split("\\.")[1];
  }
  
  
}
