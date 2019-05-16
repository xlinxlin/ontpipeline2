package mbio.ncct.ont.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import mbio.ncct.ont.model.Pipeline;
import mbio.ncct.ont.util.PipelineUtil;

/**
 * This is the controller of the pipeline overview settings.
 *
 * @author Yan Zhou
 * created on 2019/05/14
 */
public class PipelineOverviewController {
  
  /** Initialize log4j2. */
  static Logger logger = LogManager.getLogger(PipelineOverviewController.class);
  
  /** Initialize MainApp. */
  private MainApp mainApp;

  /** Initialize PipelineUtil. */
  private PipelineUtil pUtil = new PipelineUtil();
  
  /** Initialize Pipeline. */
  private Pipeline p = new Pipeline();
  
  /** Initializes check box for basecalling. */
  @FXML
  private CheckBox cBasecalling;
  
  /** Initializes check box for reads filter. */
  @FXML
  private CheckBox cReadsFilter;
 
  /** Initializes check box for assembly. */
  @FXML
  private CheckBox cAssembly;
  
  /** Initializes check box for polishing. */
  @FXML
  private CheckBox cPolishing;
  
  /** Initializes choice box for flowcell ID. */
  @FXML
  private ChoiceBox<String> cbFlowcellId = new ChoiceBox<String>() ;
  
  /** Initializes check box for kit number. */
  @FXML
  private ChoiceBox<String> cbKitNumber = new ChoiceBox<String>() ;
  
  /** Initializes check box for assembly mode. */
  @FXML
  private ChoiceBox<String> cbMode = new ChoiceBox<String>() ;
  
  /** Initializes check box for assembly method. */
  @FXML
  private ChoiceBox<String> cbMethod = new ChoiceBox<String>() ;
  
  /** Initializes text field for workspace. */
  @FXML
  private TextField tfWorkspace;
  
  /** Initializes text field for threads. */
  @FXML
  private TextField tfThreads;
  
  /** Initializes button for basecalling. */
  @FXML
  private Button btnBasecalling;
  
  /** Initializes button for reads filter. */
  @FXML
  private Button btnReadsFilter;
  
  /** Initializes button for assembly. */
  @FXML
  private Button btnAssembly;
  
  /** Initializes button for polishing. */
  @FXML
  private Button btnPolishing;
  
  /** Initializes text field for selected barcodes. */
  @FXML
  private TextField tfSelectedBarcode;
  
  /**
   * Initializes the controller of pipeline overview.
   */
  @FXML
  private void initialize()  {   
    ObservableList<String> olFlowcellIds = null;
    olFlowcellIds = FXCollections.observableArrayList(pUtil.getFlowcellIds());
    cbFlowcellId.setItems(olFlowcellIds);
    cbFlowcellId.getSelectionModel().selectFirst();
    
    ObservableList<String> olKitNumbers = null;
    olKitNumbers = FXCollections.observableArrayList(pUtil.getKitNumbers());
    cbKitNumber.setItems(olKitNumbers);
    if(olKitNumbers.contains("SQK-LSK109")) {
      cbKitNumber.getSelectionModel().select("SQK-LSK109");
    } else {
      cbKitNumber.getSelectionModel().selectFirst();
      p.setKitNumber(cbKitNumber.getSelectionModel().getSelectedItem());
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
    
    tfThreads.setText(p.getThreads());
    
    cBasecalling.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cBasecalling.isSelected()) {
        cbFlowcellId.setDisable(false);
        cbKitNumber.setDisable(false);
        btnBasecalling.setDisable(false);
        p.setIfBasecalling(true);
      } else {
        cbFlowcellId.setDisable(true);
        cbKitNumber.setDisable(true);
        btnBasecalling.setDisable(true);
        p.setIfBasecalling(false);
      }
    });
    
    cReadsFilter.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cReadsFilter.isSelected()) {
        btnReadsFilter.setDisable(false);
        p.setIfReadsFilter(true);
      } else {
        btnReadsFilter.setDisable(true);
        p.setIfReadsFilter(false);
      }
    });
    
    cAssembly.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cAssembly.isSelected()) {
        cbMode.setDisable(false);
        cbMethod.setDisable(false);
        btnAssembly.setDisable(false);
        p.setIfAssembly(true);
      } else {
        cbMode.setDisable(true);
        cbMethod.setDisable(true);
        btnAssembly.setDisable(true);
        p.setIfAssembly(false);
      }
    });
    
    cPolishing.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cPolishing.isSelected()) {
        btnPolishing.setDisable(false);
        p.setIfPolishing(true);
      } else {
        btnPolishing.setDisable(true);
        p.setIfPolishing(false);
      }
    });
    
    cbFlowcellId.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      p.setFlowcellId(newValue);
    });
    
    cbKitNumber.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      p.setKitNumber(newValue);
    });
    
    cbMode.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      p.setMode(newValue);
    });
    
    cbMethod.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
      p.setMethod(newValue);
    });
    
    tfWorkspace.textProperty().addListener((observable, oldValue, newValue) -> {
      p.setWorkspace(newValue);
    });
    
    tfThreads.textProperty().addListener((observable, oldValue, newValue) -> {
      p.setThreads(newValue);
    });

    tfSelectedBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
      p.setSelectedBarcode(newValue);
    });
  }

  /**
   * Is called by the main application to give a reference back to itself.
   * @param mainApp
   */
  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
  }
  
  
  //public void setDialogStage(Stage dialogStage) {
  //  this.dialogStage = dialogStage;
  //}
  
  
  //public boolean isOkClicked() {
  //  return okClicked;
  //}
  
  /**
   * Called when advanced basecalling button is clicked.
   */
  @FXML
  private void handleAdvancedBasecalling() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedBasecallingView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Basecalling Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(mainApp.primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      // Set the controller.
      AdvancedBasecallingController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setBarcodeKits(p.getBarcodeKit());
      controller.setGuppyMode(p.getGuppyMode());
      controller.setDevice(p.getDevice());
     
      // Show the dialog and wait until the user closes it.
      dialogStage.showAndWait();
      if(controller.isOK == 1) {
        p.setBarcodeKit(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
        p.setGuppyMode(controller.cbGuppyMode.getValue());
        p.setDevice(controller.cbDevice.getValue());
      }
    } catch (Exception e) {
      logger.error("Can not load advanced basecalling view. " + e);
    }
  }
  
  /**
   * Called when advanced reads filter button is clicked.
   */
  @FXML
  private void handleAdvancedReadsFilter() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedReadsFilterView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Reads Filter Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(mainApp.primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      // Set the controller.
      AdvancedReadsFilterController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setReadScore(p.getReadScore());
      controller.setReadLength(p.getReadLength());
      controller.setHeadCrop(p.getHeadCrop());
      controller.setIfAdapterTrimming(p.getIfAdapterTrimming());
      controller.setIfSplitting(p.getIfNoSplit());
   
      // Show the dialog and wait until the user closes it.
      dialogStage.showAndWait();
      if (controller.isOK == 1) {
        p.setReadScore(controller.tfReadScore.getText());
        p.setReadLength(controller.tfReadLength.getText());
        p.setHeadCrop(controller.tfHeadCrop.getText());
        p.setIfAdapterTrimming(controller.cAdapterTrimming.isSelected());
        p.setIfNoSplit(controller.cSplitting.isSelected()); 
      }
    } catch (Exception e) {
      logger.error("Can not load advanced reads filter view. " + e);
    }
  }
  
  /**
   * Called when advanced assembly button is clicked.
   */
  @FXML
  private void handleAdvancedAssembly() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedAssemblyView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Assembly Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(mainApp.primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      // Set the controller.
      AdvancedAssemblyController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setIfVcf(p.getIfVcf());
     
      // Show the dialog and wait until the user closes it.
      dialogStage.showAndWait();
      if (controller.isOK == 1) {
        p.setIfVcf(controller.cVcf.isSelected()); 
      }
    } catch (Exception e) {
      logger.error("Can not load advanced assembly view. " + e);
    }
  }
  
  /**
   * Called when advanced polishing button is clicked.
   */
  @FXML
  private void handleAdvancedPolishung() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedPolishingView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Polishing Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(mainApp.primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      // Set the controller.
      AdvancedPolishingController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setPtimes(p.getPtimes());
      controller.setBuscoData(p.getBuscoData());
      controller.setIfBusco(p.getIfBusco());
     
      // Show the dialog and wait until the user closes it
      dialogStage.showAndWait();
      if (controller.isOK == 1) {
        p.setPtimes(controller.cbPtimes.getValue()); 
        p.setBuscoData(controller.cbBuscoData.getValue());
        p.setIfBusco(controller.cBusco.isSelected());
      }
    } catch (Exception e) {
      logger.error("Can not load advanced polishing view. " + e);
    }
  }
  
  /**
   * Called when start pipeline button is clicked.
   */
  @FXML
  private void handleStartPipeline()  {
    if (p.getWorkspace().isEmpty()) {
      Alert alertEmptyDir = new Alert(AlertType.ERROR);
      alertEmptyDir.setTitle("Empty workspace.");
      alertEmptyDir.setContentText("Workspace can not be empty.");
      alertEmptyDir.showAndWait();
    } else if (!p.getThreads().matches(("\\d+"))){
      Alert alertWrongThreads = new Alert(AlertType.ERROR);
      alertWrongThreads.setTitle("Wrong threads.");
      alertWrongThreads.setContentText("Threads should be an integer.");
      alertWrongThreads.showAndWait();
    } else {
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      pUtil.createUserLog(p, timeStamp);
      pUtil.createPbsFile(p, timeStamp);
      try {
        //Runtime.getRuntime().exec(new String[] {"bash","-c","qsub " + p.getWorkspace() + "/pipelineWithLoop_" + timeStamp + ".pbs" });
      } catch (Exception e) {
        logger.error("Can not run .pbs file. " + e);
      }
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Submitted");
      alert.setContentText("Your job has been submitted successfully.");
      alert.showAndWait();
    }
  }
    
  
  /**
   * Called when select workspace button is clicked.
   */
  @FXML
  private void handleSelectWorkspace() {
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
  }
}