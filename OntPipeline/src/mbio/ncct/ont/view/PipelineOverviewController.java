package mbio.ncct.ont.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
  
  /** Initializes log4j2. */
  private static Logger logger = LogManager.getLogger(PipelineOverviewController.class);
  
  /** Initializes MainApp. */
  private MainApp mainApp;

  /** Initializes PipelineUtil. */
  private PipelineUtil pUtil = new PipelineUtil();
  
  /** Initializes Pipeline. */
  private Pipeline p = new Pipeline();
  
  /** Initializes check box for basecalling. */
  @FXML
  private CheckBox cBasecalling;
  
  /** Initializes check box for demultiplexing. */
  @FXML
  private CheckBox cDemultiplexing;
  
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
  
  /** Initializes text field for Nanopore reads workspace. */
  @FXML
  private TextField tfNanoporeWorkspace;
  
  /** Initializes text field for Illumina reads workspace. */
  @FXML
  private TextField tfIlluminaWorkspace;
  
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
  
  /** Initializes text field for output path. */
  @FXML
  private TextField tfOutputPath;
  
  /** Initializes text field for prefix. */
  @FXML
  private TextField tfPrefix;
  
  /** Initializes text field for sample sheet. */
  @FXML
  private TextField tfSampleSheet;
  
  /** Initializes check combo box for barcode kits. */
  @FXML
  private CheckComboBox<String> ccbBarcodeKits = new CheckComboBox<String>();
  
  
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
    
    ObservableList<String> olBarcodeKits = FXCollections.observableArrayList(pUtil.getBarcodeKits());
    ccbBarcodeKits.getItems().addAll(olBarcodeKits);
    IndexedCheckModel<String> icm = ccbBarcodeKits.getCheckModel();
    String[] strArrBarcodeKits = p.getBarcodeKits().replaceAll("\"", "").split(" ");
    for(int i=0;i<strArrBarcodeKits.length;i++) {
      icm.check(strArrBarcodeKits[i]);
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
    cDemultiplexing.setSelected(true);
    cReadsFilter.setSelected(true);
    cAssembly.setSelected(true);
    cPolishing.setSelected(true);
    
    tfThreads.setText(p.getThreads());
    tfNanoporeWorkspace.setPromptText("/path/to/your/Nanopore/reads/folder");
    tfIlluminaWorkspace.setPromptText("/path/to/your/Illumina/reads/folder");
    tfOutputPath.setPromptText("/path/to/your/output/folder");
    tfSampleSheet.setPromptText("/path/to/your/sample/sheet/file");
    tfSelectedBarcode.setPromptText("e.g.1,2,3, leave blank for all barcodes.");
    tfPrefix.setPromptText("e.g.ID");
    
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
    
    cDemultiplexing.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if (cDemultiplexing.isSelected()) {
        ccbBarcodeKits.setDisable(false);
        p.setIfDemultiplexing(true);
      } else {
        ccbBarcodeKits.setDisable(true);
        p.setIfDemultiplexing(false);
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
    
    tfNanoporeWorkspace.textProperty().addListener((observable, oldValue, newValue) -> {
      p.setOntReadsWorkspace(newValue);
    });
    
    tfThreads.textProperty().addListener((observable, oldValue, newValue) -> {
      p.setThreads(newValue);
    });

    tfSelectedBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
      p.setSelectedBarcode(newValue);
    });
    
    tfPrefix.textProperty().addListener((observable, oldValue, newValue) -> {
      p.setPrefix(newValue);
    });
  }

  /**
   * Is called by the main application to give a reference back to itself.
   * @param mainApp Main app.
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
      controller.setGuppyMode(p.getGuppyMode());
      controller.setDevice(p.getDevice());
     
      // Show the dialog and wait until the user closes it.
      dialogStage.showAndWait();
      if(controller.isOK == 1) {
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
    if (p.getOntReadsWorkspace().isEmpty()) {
      pUtil.createAlertDialog(AlertType.ERROR, "Empty Nanopore reads folder.", "Nanopore reads folder can not be empty.");
    } else if (p.getOutputPath().isEmpty()) {
      pUtil.createAlertDialog(AlertType.ERROR, "Empty output folder.", "Output folder can not be empty.");
    } else if (!p.getThreads().matches(("\\d+"))){
      pUtil.createAlertDialog(AlertType.ERROR, "Wrong threads.", "Threads should be a positive integer.");
    } else if (!p.getIfBasecalling() && !p.getIfReadsFilter() && !p.getIfAssembly() && !p.getIfPolishing()) {
      pUtil.createAlertDialog(AlertType.ERROR, "Empty module.", "Please select at least one module.");
    } else if (!p.getSelectedBarcode().matches("([123456789],{0,1})*")){
      pUtil.createAlertDialog(AlertType.ERROR, "Wrong seleted barcodes.", "The format of selected barcodes is wrong.");
    } else if (p.getIfBasecalling() && !pUtil.checkDirectoryValidity(new File(p.getOntReadsWorkspace()), "fast5")) {
      pUtil.createAlertDialog(AlertType.ERROR, "Wrong input files.", "Base calling runs only with .fast5 files");
    } else if (p.getIfAssembly() && p.getMethod().equals("Hybrid assembly") && p.getIlluminaReadsWorkspace().isEmpty()) {
      pUtil.createAlertDialog(AlertType.ERROR, "No Illumina reads found.", "Hybrid assembly requires Illumina reads.");
    } else if (p.getIfPolishing() && p.getIlluminaReadsWorkspace().isEmpty()) {
      pUtil.createAlertDialog(AlertType.ERROR, "No Illumina reads found.", "Polishing requires Illumina reads.");
    } else if (pUtil.checkDirectoryValidity(new File(p.getOntReadsWorkspace()), "fast5") && !p.getIfBasecalling()) {
      pUtil.createAlertDialog(AlertType.ERROR, "Base calling required.", "Base calling is required since you provide .fast5 files.");
    } else {
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      pUtil.createUserLog(p, timeStamp);
      pUtil.createPbsFile(p, timeStamp);
      try {
        Runtime.getRuntime().exec(new String[] {"bash","-c","qsub " + p.getOutputPath() + "/pipelineWithLoop_" + timeStamp + ".pbs" });
      } catch (Exception e) {
        logger.error("Can not run .pbs file. " + e);
      }
      pUtil.createAlertDialog(AlertType.INFORMATION, "Submitted.", "Your job has been submitted successfully.");
    }
  }
     
  /**
   * Called when select Nanopore reads workspace button is clicked.
   */
  @FXML
  private void handleSelectNanoporeWorkspace() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory != null) {
      if (pUtil.checkDirectoryValidity(selectedDirectory,"fast5") || pUtil.checkDirectoryValidity(selectedDirectory,"fastq")) {
        tfNanoporeWorkspace.setText(selectedDirectory.toString()); 
      } else {
        pUtil.createAlertDialog(AlertType.ERROR, "Wrong ONT workspace.", "No .fast5/.fastq files found in this folder.");
      }
    } 
  }
  
  /**
   * Called when select Illumina reads workspace button is clicked.
   */
  @FXML
  private void handleSelectIlluminaWorkspace() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory != null) {
      //Object checkResult[] = pUtil.checkIlluminaReads(selectedDirectory);
      //if ((Boolean)checkResult[0]) {
      if ((Boolean) pUtil.checkIlluminaReads(selectedDirectory)[0]) {
        tfIlluminaWorkspace.setText(selectedDirectory.toString()); 
        p.setIlluminaReadsWorkspace(selectedDirectory.toString());
      } else {
        pUtil.createAlertDialog(AlertType.ERROR, "Wrong Illumina workspace.", "This folder is not valid.");
      }
    }
  }
  
  /**
   * Called when select output folder button is clicked.
   */
  @FXML
  private void handleSelectOutputFolder() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory != null) {
      tfOutputPath.setText(selectedDirectory.toString());
      p.setOutputPath(selectedDirectory.toString());
    }
  }
  
  /**
   * Opens the document in a browser.
   */
  @FXML
  private void handleOpenDocument() {
    try {
      new ProcessBuilder("x-www-browser", "https://ontpipeline2.readthedocs.io/").start();
      //new ProcessBuilder("x-www-browser", "docs/build/html/index.html").start();
    } catch (Exception e) {
      logger.error("Can not open document. " + e);
    }
  }
  
  /**
   * Reads the sample sheet.
   */
  @FXML
  private void handleReadSampleSheet() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("CSV/TSV Files", "*.csv", "*.CSV", "*.tsv", "*.TSV"));
        //new ExtensionFilter("TSV Files", "*.tsv"));
    File sampleSheetFile = fileChooser.showOpenDialog(null);
    if (sampleSheetFile != null) {
      if (!pUtil.readSampleSheet(sampleSheetFile.toString(), pUtil.getFileExtension(sampleSheetFile.toString())).equals("wrong")) {
        tfSampleSheet.setText(sampleSheetFile.toString());
        p.setSampleSheetContent(pUtil.readSampleSheet(sampleSheetFile.toString(), pUtil.getFileExtension(sampleSheetFile.toString())));
      } else {
        pUtil.createAlertDialog(AlertType.ERROR, "Wrong sample sheet.", "The format of sample sheet is wrong.");
      };
    }
  }
}