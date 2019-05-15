package mbio.ncct.ont;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mbio.ncct.ont.view.PipelineOverviewController;
import mbio.ncct.ont.view.AdvancedAssemblyController;
import mbio.ncct.ont.view.AdvancedBasecallingController;
import mbio.ncct.ont.view.AdvancedPolishingController;
import mbio.ncct.ont.view.AdvancedReadsFilterController;
import mbio.ncct.ont.model.*;

/**
 * This is the MainApp class of the ONT pipeline GUI.
 * 
 * @author Yan Zhou
 * created on 2019/05/14
 */
public class MainApp extends Application {
  
  /** Initializes log4j2. */
  static Logger logger = LogManager.getLogger(MainApp.class);
  
  /** Initializes primary stage. */
  private Stage primaryStage;
  
  /** Initializes root layout. */
  private BorderPane rootLayout;
  
  /** Initializes pipeline model. */
  public Pipeline p = new Pipeline();
    
  /**
   * Initializes the GUI.
   * @param primaryStage the primary stage of the GUI.
   */
  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("ONT Pipeline");
    initRootLayout();
    showPipelineOverview();
  }
    
  /**
   * Initializes the root layout.
   */
  private void initRootLayout() {
    try {
      // Load root layout from fxml file.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
      rootLayout = (BorderPane) loader.load();
      
      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      logger.error("Can not load root layout. " + e);
    }
  }
  
  /**
   * Initializes the overview layout.
   */
  private void showPipelineOverview() {
    try {
      // Load pipeline overview.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/RootLayout_.fxml"));
      AnchorPane pipelineOverview = (AnchorPane) loader.load();
        
      // Set overview into the center of root layout.
      rootLayout.setCenter(pipelineOverview);
      PipelineOverviewController controller = loader.getController();
      controller.setMainApp(this);
    } catch (Exception e) {
      logger.error("Can not load pipeline overview layout. " + e);
    }
  }

  
    /**
     * Returns the main stage.
     * @return
     */
  /*
  public Stage getPrimaryStage() {
    return primaryStage;
  }
  */
  
  /*
  public static void main(String[] args) {
    launch(args);
  }
  */
  /**
   * Deal with the advanced basecalling settings.
   */
  public void handleAdvancedBasecalling() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedBasecallingView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Basecalling Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
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
   * Deal with the advanced reads-filter settings.
   */
  public void handleAdvancedReadsFilter() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedReadsFilterView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Reads Filter Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
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
   * Deal with the advanced assembly settings.
   */
  public void handleAdvancedAssembly() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedAssemblyView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Assembly Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
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
   * Deal with the advanced polishing settings.
   */
  public void handleAdvancedPolishing() {
    try {
      // Load the fxml file and create a new stage for the popup dialog.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/AdvancedPolishingView.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      // Create the dialog Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Advanced Polishing Setting");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
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
   * Deal with the start pipeline event.
   */
  public void handleStartPipeline()  {
    // Format and set the selected barcodes
    if (!p.getSelectedBarcode().isEmpty()) {
      String[] strArr = p.getSelectedBarcode().split(",");
      String formattedSelectedBarcode = "";
      for(int i=0;i<strArr.length;i++) {
        String formatted = String.format("%02d", Integer.valueOf(strArr[i])) + ",";
        formattedSelectedBarcode = formattedSelectedBarcode + formatted;
      }
      p.setSelectedBarcode("barcode{" + formattedSelectedBarcode.substring(0, formattedSelectedBarcode.length()-1) + "}/");
    }
    
    if ( !p.getFlowcellId().equals("FLO-MIN107") && p.getGuppyMode().equals("fast")) {
      Map<String, String> combinationFlowcellKit = null;
      String cfg = null;
      try {
        combinationFlowcellKit = findCombinationFlowcellKit();
        cfg = combinationFlowcellKit.get(p.getFlowcellId().concat(p.getKitNumber())).toString();
      } catch (Exception e) {
        logger.error("Can not get the combinations of flowcell ID and kit number. " + e);
      }
      int cfg_bps = cfg.indexOf("bps");
      String cfgFile = ( cfg.substring(0, cfg_bps + 3) + "_fast" ) + ( p.getDevice().equals("PromethION") ? "_prom" : "" ) + ".cfg";
      p.setIfGuppyFast(true);
      p.setGuppyCfgFile("/opt/ont-guppy-cpu_3.0.3/data/" + cfgFile);
    }
    
    //Path path = Paths.get("/home/yan/git/repository/OntPipeline/pbs/pipelineWithLoop.pbs");
    Path path = Paths.get("/opt/ontpipeline/pbs/pipelineWithLoop.pbs");
    Path newPath = Paths.get(p.getWorkspace() + "/pipelineWithLoop_.pbs");
    Charset charset = StandardCharsets.UTF_8;

    String content = null;
    try {
      content = new String(Files.readAllBytes(path), charset);
    } catch (Exception e) {
      logger.error("Can not read the .pbs template. " + e);
    }
    
    content = content.replaceAll("\\$WORKSPACE_PATH", p.getWorkspace())
        .replaceAll("\\$IF_BASECALLING", p.getIfBasecalling().toString())
        .replaceAll("\\$FLOWCELL_ID", p.getFlowcellId())
        .replaceAll("\\$KIT_NUMBER", p.getKitNumber())
        .replaceAll("\\$THREADS", p.getThreads())
        .replaceAll("\\$BARCODEKIT", p.getBarcodeKit())
        .replaceAll("\\$IF_ADAPTERTRIMMING", p.getIfAdapterTrimming().toString())
        .replaceAll("\\$BARCODENUMBERS", p.getSelectedBarcode())
        .replaceAll("\\$IF_READSFILTER", p.getIfReadsFilter().toString())
        .replaceAll("\\$SCORE", p.getReadScore())
        .replaceAll("\\$LENGTH", p.getReadLength())
        .replaceAll("\\$HEADCROP", p.getHeadCrop())
        .replaceAll("\\$IF_ASSEMBLY", p.getIfAssembly().toString())
        .replaceAll("\\$IF_VCF", p.getIfVcf().toString())
        .replaceAll("\\$MODE", p.getMode())
        .replaceAll("\\$METHOD", p.getMethod())
        .replaceAll("\\$IF_POLISHING", p.getIfPolishing().toString())
        .replaceAll("\\$IF_BUSCO", p.getIfBusco().toString())
        .replaceAll("\\$LINEAGE", p.getBuscoData())
        .replaceAll("\\$PTIMES", p.getPtimes())
        .replaceAll("\\$IF_GUPPYFAST", p.getIfGuppyFast().toString())
        .replaceAll("\\$CFG_FILE", p.getGuppyCfgFile());
   try {
      Files.write(newPath, content.getBytes(charset));
    } catch (Exception e) {
      logger.error("Can not write .pbs file. " + e);
    }
    //Process process = Runtime.getRuntime().exec(new String[] {"bash","-c","qsub /home/yan/nextflowScripts/ont/adapterTrimming.pbs"});
    try {
      Runtime.getRuntime().exec(new String[] {"bash","-c","qsub " + p.getWorkspace() + "/pipelineWithLoop_.pbs"});
    } catch (Exception e) {
      logger.error("Can not run .pbs file. " + e);
    }
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Submitted");
    alert.setContentText("Your job has been submitted successfully.");
    alert.showAndWait();
  }
  
  /**
   * Set if basecalling will be used or not.
   * @param ifBasecalling if basecalling selected
   */
  public void setIfBasecalling(boolean ifBasecalling) {
    p.setIfBasecalling(ifBasecalling);
  }
  
  /**
   * Set if reads filter will be used or not.
   * @param ifReadsFilter if reads filter selected
   */
  public void setIfReadsFilter(boolean ifReadsFilter) {
    p.setIfReadsFilter(ifReadsFilter);
  }
  
  /**
   * Set if assembly will be used or not.
   * @param ifAssembly if assembly selected
   */
  public void setIfAssembly(boolean ifAssembly) {
    p.setIfAssembly(ifAssembly);
  }
  
  /**
   * Set if polishing will be used or not.
   * @param ifPolishing if polishing selected
   */
  public void setIfPolishing(boolean ifPolishing) {
    p.setIfPolishing(ifPolishing);
  }
  
  /**
   * Set Flowcell ID.
   * @param flowcellID Flowcell ID
   */
  public void setFlowcellId(String flowcellId) {
    p.setFlowcellId(flowcellId);
  }
  
  /**
   * Set kit number.
   * @param kitNumber kit number
   */
  public void setKitNumber(String kitNumber) {
    p.setKitNumber(kitNumber);
  }
  
  /**
   * Set assembly mode.
   * @param mode assembly mode
   */
  public void setMode(String mode) {
    p.setMode(mode);
  }
  
  /**
   * Set assembly method.
   * @param method assembly method
   */
  public void setMethod(String method) {
    p.setMethod(method);
  }
  
  /**
   * Set user workspace.
   * @param workspace user workspace
   */
  public void setWorkspace(String workspace) {
    p.setWorkspace(workspace);
  }
  
  /**
   * Set threads.
   * @param threads thread numbers
   */
  public void setThreads(String threads) {
    p.setThreads(threads);
  }
  
  /**
   * Set the specified barcodes.
   * @param selectedBarcode the specified barcodes 
   */
  public void setSelectedBarcode(String selectedBarcode) {
    p.setSelectedBarcode(selectedBarcode);
  }
  
  /**
   * Find all the combinations of flowcell ID and kit number.
   * @return return a map with all the combinations of flowcell ID and kit number
   */
  public Map<String, String> findCombinationFlowcellKit() {
    String s = null;
    Map<String, String> m = new HashMap<String, String>();
    Process p = null;
    try {
      p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_basecaller --print_workflows | awk 'NR>2 {print $1,$2,$3,$4}' " });
    } catch (Exception e) {
      logger.error("Can not run command: guppy_basecallerr --print_workflows . " + e);
    }
    //BufferedReader stdError = null;
    try {
      BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
      //stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
      while ((s = stdInput.readLine()) != null ) {
        if (s.length() > 3) {
          String[] arr = s.replaceAll("included ", "").split(" ");
          m.put(arr[0].concat(arr[1]), arr[2]);
        }
      }
    } catch (Exception e) {
      logger.error("Can not read result from guppy_basecallerr --print_workflows . " + e);
    }
    return m;
  }
  
 private String writeSummary() {
   String str = null;
   return str;
 }
  
}