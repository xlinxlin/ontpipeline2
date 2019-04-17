package mbio.ncct.ont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;
import org.controlsfx.dialog.Wizard.LinearFlow;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mbio.ncct.ont.view.GeneralSettingsController;
import mbio.ncct.ont.view.PipelineOverviewController;
import mbio.ncct.ont.view.AdvancedAssemblyController;
import mbio.ncct.ont.view.AdvancedBasecallingController;
import mbio.ncct.ont.view.AdvancedPolishingController;
import mbio.ncct.ont.view.AdvancedReadsFilterController;
import mbio.ncct.ont.view.AssemblySettingsController;
import mbio.ncct.ont.view.BasecallingSettingsController;
import mbio.ncct.ont.view.ReadsFilterSettingsController;
import mbio.ncct.ont.model.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MainApp extends Application {
  
  private Stage primaryStage;
  private BorderPane rootLayout;
  public Pipeline p = new Pipeline();
  
  

 // ... AFTER THE OTHER VARIABLES ...

    /**
     * The data as an observable list of Persons.
     */

    /**
     * Constructor
     */
  public MainApp() {
        // Add some sample data
  }
  
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     * @throws IOException 
     */

  
    // ... THE REST OF THE CLASS ...
    
  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("ONT Pipeline");
    
    initRootLayout();
    showPipelineOverview();
    //showWizard();    
    
    
  }
    
    /**
     * Initializes the root layout.
     */
  public void initRootLayout() {
    try {
      // Load root layout from fxml file.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
      rootLayout = (BorderPane) loader.load();
      //rootLayout = loader.load();
            
      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public void showPipelineOverview() {
    try {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/RootLayout_.fxml"));
        AnchorPane pipelineOverview = (AnchorPane) loader.load();
        
        // Set person overview into the center of root layout.
        rootLayout.setCenter(pipelineOverview);
        PipelineOverviewController controller = loader.getController();
        controller.setMainApp(this);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

    /**
     * Shows the person overview inside the root layout.
     * @throws IOException 
     */
  public void showWizard() throws IOException {
    
 
    ValidationSupport vsTfWorkspace = new ValidationSupport();
    ValidationSupport vsTfThreads = new ValidationSupport();
    ValidationSupport vsCbFlowcellId = new ValidationSupport();
    ValidationSupport vsCbKitNumber = new ValidationSupport();
    ValidationSupport vsTfReadLength = new ValidationSupport();
    ValidationSupport vsTfReadScore = new ValidationSupport();
    ValidationSupport vsTfHeadCrop = new ValidationSupport();
    //ValidationSupport vsCcbBarcodeKit = new ValidationSupport();
    
    
    
    //FXMLLoader loaderBasecallingSettings = new FXMLLoader(getClass().getResource("view/BasecallingSettingsView.fxml"));  
    //basecallingSettingsPage.setContent(loaderBasecallingSettings.load());
    //basecallingSettingsPage.setHeaderText("Basecalling Settings");
    //BasecallingSettingsController bsCtrl = loaderBasecallingSettings.getController();
    //vsCbFlowcellId.registerValidator(bsCtrl.cbFlowcellId, Validator.createEmptyValidator("Text is Required"));
    //vsCcbBarcodeKit.registerValidator(bsCtrl.cbKitNumber, Validator.createEmptyValidator("Text is Required"));
    
    WizardPane generalSettingsPage = new WizardPane() {
      
      public void onEnteringPage(Wizard wizard) { 
        wizard.invalidProperty().unbind();
        wizard.invalidProperty().bind(Bindings.or(vsTfWorkspace.invalidProperty(),vsTfThreads.invalidProperty())); 
      }
      
      /*
      public void onEnteringPage(Wizard wizard) { 
      
        FXMLLoader loaderGeneralSettings = new FXMLLoader(getClass().getResource("view/GeneralSettingsView.fxml"));
        try {
          this.setContent(loaderGeneralSettings.load());
          this.setHeaderText("General Settings");
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        GeneralSettingsController gsCtrl = loaderGeneralSettings.getController();
        
        vsTfWorkspace.registerValidator(gsCtrl.tfWorkspace, Validator.createEmptyValidator("Text is Required"));
        vsTfThreads.registerValidator(gsCtrl.tfThreads, Validator.createEmptyValidator("Text is Required"));
        
        
        gsCtrl.tfBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("textfield changed from " + oldValue + " to " + newValue);
          if(newValue.isEmpty()) {
            //vsTfWorkspace.registerValidator(gsCtrl.tfWorkspace, Validator.createEmptyValidator("Text is Required"));
            //vsTfThreads.registerValidator(gsCtrl.tfThreads, Validator.createEmptyValidator("Text is Required"));
            ValidationSupport.setRequired(gsCtrl.tfWorkspace, true);
            ValidationSupport.setRequired(gsCtrl.tfThreads, true);
          } else {
            ValidationSupport.setRequired(gsCtrl.tfWorkspace, false);
            ValidationSupport.setRequired(gsCtrl.tfThreads, false);
          }
        });
        
        wizard.invalidProperty().bind(Bindings.or(vsTfWorkspace.invalidProperty(),vsTfThreads.invalidProperty())); 
      }
      */
      
    };
    
    WizardPane basecallingSettingsPage = new WizardPane() {
       
      public void onEnteringPage(Wizard wizard) {
        
        /*
        BasecallingSettingsController bsCtrl = new BasecallingSettingsController();
        bsCtrl.cBasecalling.selectedProperty().addListener((observable, oldValue, newValue) -> {
          if (bsCtrl.cBasecalling.isSelected()) {
           System.out.println("selected"); 
            
          } else {
            wizard.invalidProperty().unbind();
            wizard.invalidProperty().bind(Bindings.or(vsCbFlowcellId.invalidProperty(),vsCbKitNumber.invalidProperty())); 
           
          }
        
        });
        */
        
        wizard.invalidProperty().unbind();
        //wizard.invalidProperty().bind(Bindings.or(vsCbFlowcellId.invalidProperty(),vsCbKitNumber.invalidProperty())); 
        BooleanBinding booleanBinding = vsCbFlowcellId.invalidProperty().or(vsCbKitNumber.invalidProperty());
        wizard.invalidProperty().bind(booleanBinding);
      }
      
      public void onExitingPage(Wizard wizard) {
       
      }
      /*
      public void onEnteringPage(Wizard wizard) {
        FXMLLoader loaderBasecallingSettings = new FXMLLoader(getClass().getResource("view/BasecallingSettingsView.fxml"));
        try {
          this.setContent(loaderBasecallingSettings.load());
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        this.setHeaderText("Basecalling Settings");
        BasecallingSettingsController bsCtrl = loaderBasecallingSettings.getController();
        
        vsCbFlowcellId.registerValidator(bsCtrl.cbFlowcellId, Validator.createEmptyValidator("Text is Required"));
        vsCcbBarcodeKit.registerValidator(bsCtrl.cbKitNumber, Validator.createEmptyValidator("Text is Required"));
        //wizard.invalidProperty().bind(Bindings.and(vsCbFlowcellId.invalidProperty(),vsCcbBarcodeKit.invalidProperty()));
        //wizard.invalidProperty().bind(Bindings.add(vsCbFlowcellId.invalidProperty(),vsCcbBarcodeKit.invalidProperty()));
        //wizard.invalidProperty().unbind();
        
        
        bsCtrl.cBasecalling.selectedProperty().addListener((observable, oldValue, newValue) -> {
          if (bsCtrl.cBasecalling.isSelected()) {
            
            ValidationSupport.setRequired(bsCtrl.cbFlowcellId, false);
            ValidationSupport.setRequired(bsCtrl.cbKitNumber, false);
            //wizard.invalidProperty().unbind();
            //System.out.println("unbind");
            
          } else {
            
            //vsCbFlowcellId.registerValidator(bsCtrl.cbFlowcellId, Validator.createEmptyValidator("Text is Required"));
            //vsCcbBarcodeKit.registerValidator(bsCtrl.cbKitNumber, Validator.createEmptyValidator("Text is Required"));
            ValidationSupport.setRequired(bsCtrl.cbFlowcellId, true);
            ValidationSupport.setRequired(bsCtrl.cbKitNumber, true);
            //wizard.invalidProperty().bind(Bindings.or(vsCbFlowcellId.invalidProperty(),vsCcbBarcodeKit.invalidProperty())); 
            //System.out.println("bind");
            
            //wizard.invalidProperty().bind(Bindings.or(vsCbFlowcellId.invalidProperty(),vsCcbBarcodeKit.invalidProperty())); 
          }
        
        });
        
        
        
        //vsCbFlowcellId.registerValidator(bsCtrl.cbFlowcellId, Validator.createEmptyValidator("Text is Required"));
        //vsCcbBarcodeKit.registerValidator(bsCtrl.cbKitNumber, Validator.createEmptyValidator("Text is Required"));
        
        //wizard.invalidProperty().bind(Bindings.or(vsCbFlowcellId.invalidProperty(),vsCcbBarcodeKit.invalidProperty())); 
      }
     */ 
     
    }; 
    
    WizardPane readsFilterSettingsPage = new WizardPane() {  
      public void onEnteringPage(Wizard wizard) {
     
        
        wizard.invalidProperty().unbind();
        BooleanBinding booleanBinding = vsTfReadLength.invalidProperty().or(vsTfReadScore.invalidProperty().or(vsTfHeadCrop.invalidProperty()));
        wizard.invalidProperty().bind(booleanBinding);
        //wizard.invalidProperty().set(true);;
        //wizard.invalidProperty().bind(Bindings.equalIgnoreC, op2)vsTfReadLength.invalidProperty()); 
        //wizard.invalidProperty().bind(Bindings.or(vsTfReadLength.invalidProperty(),vsTfReadScore.invalidProperty()));
        
        //BooleanBinding booleanBinding = vsTfReadLength.invalidProperty().or(vsTfReadScore.invalidProperty().or(vsTfHeadCrop.invalidProperty()));
        //wizard.invalidProperty().bind(booleanBinding);
        //this.onExitingPage(wizard);
        //System.out.println("456");
      } 
      
      public void onExitingPage(Wizard wizard) {
        //System.out.println("on Existing page");
        wizard.invalidProperty().unbind();
        BooleanBinding booleanBinding = vsTfReadLength.invalidProperty().and(vsTfReadScore.invalidProperty().and(vsTfHeadCrop.invalidProperty()));
        wizard.invalidProperty().bind(booleanBinding);
        vsTfReadLength.setErrorDecorationEnabled(false);
        //System.out.println("123");
        //BooleanBinding booleanBinding = vsTfReadLength.invalidProperty().or(vsTfReadScore.invalidProperty().or(vsTfHeadCrop.invalidProperty()));
        //wizard.invalidProperty().bind(booleanBinding);
      }
     
    };
    
    WizardPane assemblySettingsPage = new WizardPane() {  
      
    };
    
    Wizard wizard = new Wizard();
    
    //wizard.setFlow(new LinearFlow(generalSettingsPage, basecallingSettingsPage,readsFilterSettingsPage, assemblySettingsPage));
    
    FXMLLoader loaderGeneralSettings = new FXMLLoader(getClass().getResource("view/GeneralSettingsView.fxml"));  
    generalSettingsPage.setContent(loaderGeneralSettings.load());
    generalSettingsPage.setHeaderText("General Settings");
    GeneralSettingsController gsCtrl = loaderGeneralSettings.getController();
    vsTfWorkspace.registerValidator(gsCtrl.tfWorkspace, Validator.createEmptyValidator("Text is Required"));
    vsTfThreads.registerValidator(gsCtrl.tfThreads, Validator.createRegexValidator("Number only!", "\\d+", Severity.ERROR));
    
    FXMLLoader loaderBasecallingSettings = new FXMLLoader(getClass().getResource("view/BasecallingSettingsView.fxml"));  
    basecallingSettingsPage.setContent(loaderBasecallingSettings.load());
    basecallingSettingsPage.setHeaderText("Basecalling Settings");
    BasecallingSettingsController bsCtrl = loaderBasecallingSettings.getController();
    vsCbFlowcellId.registerValidator(bsCtrl.cbFlowcellId, Validator.createEmptyValidator("Text is Required"));
    vsCbKitNumber.registerValidator(bsCtrl.cbKitNumber, Validator.createEmptyValidator("Text is Required"));
   
    FXMLLoader loaderReadsFilterSettings = new FXMLLoader(getClass().getResource("view/ReadsFilterSettingsView.fxml"));
    readsFilterSettingsPage.setContent(loaderReadsFilterSettings.load());
    ReadsFilterSettingsController rfCtrl = loaderReadsFilterSettings.getController();    
    readsFilterSettingsPage.setHeaderText("Reads Filter Settings");
    //vsTfReadLength.registerValidator(rfCtrl.tfReadLength, Validator.createEmptyValidator("Text is Required"));
    vsTfReadLength.registerValidator(rfCtrl.tfReadLength, Validator.createRegexValidator("Number only!", "\\d+", Severity.ERROR));
    vsTfReadLength.registerValidator(rfCtrl.tfReadScore, Validator.createRegexValidator("Number only!", "\\d+", Severity.ERROR));
    vsTfReadLength.registerValidator(rfCtrl.tfHeadCrop, Validator.createRegexValidator("Number only!", "\\d+", Severity.ERROR)); 
    
    rfCtrl.cReadsFilter.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(rfCtrl.cReadsFilter.isSelected()) { 
        readsFilterSettingsPage.onExitingPage(wizard);
      } else {
        readsFilterSettingsPage.onEnteringPage(wizard);
      }
    });
    
    
    
    
    
    FXMLLoader loaderAssemblySettings = new FXMLLoader(getClass().getResource("view/AssemblySettingsView.fxml"));
    assemblySettingsPage.setContent(loaderAssemblySettings.load());
    AssemblySettingsController asCtrl = loaderAssemblySettings.getController();    
    assemblySettingsPage.setHeaderText("Assembly Settings");
    //vsTfReadLength.registerValidator(rfCtrl.tfReadLength, Validator.createEmptyValidator("Text is Required"));
    	
    //Wizard wizard = new Wizard();
    //wizard.setFlow(new LinearFlow(generalSettingsPage, basecallingSettingsPage,readsFilterSettingsPage, assemblySettingsPage));
    
    //wizard.setFlow(new LinearFlow(generalSettingsPage,readsFilterSettingsPage, basecallingSettingsPage));
    /*
    String ex = gsCtrl.checkFileExtension(gsCtrl.tfWorkspace.getText());
    if(ex.toLowerCase().equals("fastq")) {
      wizard.setFlow(new LinearFlow(generalSettingsPage, basecallingSettingsPage,readsFilterSettingsPage, assemblySettingsPage));
    } else {
      wizard.setFlow(new LinearFlow(generalSettingsPage,readsFilterSettingsPage, assemblySettingsPage));
    }
    */
    /*
    if(gsCtrl.checkFileExtension(gsCtrl.tfWorkspace.getText()).toLowerCase().equals("fastq")) {
      System.out.println("123");
    }
    */
    
    wizard.setFlow(new LinearFlow(generalSettingsPage, basecallingSettingsPage,readsFilterSettingsPage, assemblySettingsPage));

    wizard.showAndWait().ifPresent(result -> {
      if (result == ButtonType.FINISH) {
        //System.out.println("Wizard finished, settings: " + wizard.getSettings());
        //System.out.println(bsCtrl.ccbBarcodeKit.getCheckModel().getCheckedItems());
        //System.out.println("Head crop" + asCtrl.cbTimes.getValue());
        String workspace = gsCtrl.tfWorkspace.getText();
        String threads = gsCtrl.tfThreads.getText();
        String barcodes = gsCtrl.tfBarcode.getText() == null ? "all" : gsCtrl.tfBarcode.getText();
        Boolean adapterTrimming = gsCtrl.cAdapterTrimming.isSelected();
        Boolean basecallingSettings = bsCtrl.cBasecalling.isSelected();
        String flowcellId = bsCtrl.cbFlowcellId.getValue();
        String kitNumber = bsCtrl.cbKitNumber.getValue();
        String barcodeKits = bsCtrl.ccbBarcodeKit.getCheckModel().getCheckedItems().isEmpty() ? 
            "" : bsCtrl.ccbBarcodeKit.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\"");
        Boolean readsFilterSettings = rfCtrl.cReadsFilter.isSelected();
        String readScore = rfCtrl.tfReadScore.getText();
        String readLength = rfCtrl.tfReadLength.getText();
        String headCrop = rfCtrl.tfHeadCrop.getText();
        Boolean assemblySettings = asCtrl.cAssemblySettings.isSelected();
        String mode = asCtrl.cbMode.getValue();
        String method = asCtrl.cbMethod.getValue();
        String read1 = asCtrl.tfRead1.getText();
        String read2 = asCtrl.tfRead2.getText();
        Boolean vcf = asCtrl.cVCF.isSelected();
        String polishingTimes = asCtrl.cbTimes.getValue();
        Boolean busco = asCtrl.cBusco.isSelected();
        //System.out.println(barcodeKits);
        Path path = Paths.get("/home/yan/nextflowScripts/ont/pipeline.pbs");
        Path newPath = Paths.get("/home/yan/nextflowScripts/ont/pipeline_.pbs");
        Charset charset = StandardCharsets.UTF_8;

        String content = null;
        try {
          content = new String(Files.readAllBytes(path), charset);
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        content = content.replaceAll("\\$\\{WORKSPACE_PATH\\}", workspace)
            .replaceAll("\\$NOT_BASECALLING", basecallingSettings.toString())
            .replaceAll("\\$\\{FLOWCELL_ID\\}", flowcellId)
            .replaceAll("\\$\\{KIT_NUMBER\\}", kitNumber)
            .replaceAll("\\$\\{THREADS\\}", threads)
            .replaceAll("\\$\\{BARCODEKIT\\}", barcodeKits)
            .replaceAll("\\$NOT_ADAPTERTRIMMING", adapterTrimming.toString())
            .replaceAll("\\$\\{BARCODENUMBERS\\}", barcodes)
            .replaceAll("\\$NOT_READSFILTER", readsFilterSettings.toString())
            .replaceAll("\\$\\{SCORE\\}", readScore)
            .replaceAll("\\$\\{LENGTH\\}", readLength)
            .replaceAll("\\$\\{HEADCROP\\}", headCrop)
            .replaceAll("\\$NOT_ASSEMBLY", assemblySettings.toString())
            .replaceAll("\\$\\{MODE\\}", mode)
            .replaceAll("\\$\\{METHOD\\}", method)
            .replaceAll("\\$NOT_BUSCO", busco.toString())
            .replaceAll("\\$\\{PTIMES\\}", polishingTimes);
       try {
          Files.write(newPath, content.getBytes(charset));
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        
        
        
        /*
        Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "qsub -v "
            + "NOT_ADAPERTRIMMING="+adapterTrimming+",BARCODENUMBERS="+barcodes
            + ",NOT_BASECALLING="+basecallingSettings
            + ",THREADS="+threads+",FLOWCELL_ID="+flowcellId+",KIT_NUMBER="+kitNumber+",BARCODEKIT="+barcodeKits
            + ",NOT_READSFILTER="+readsFilterSettings
            + ",SCORE="+readScore+",LENGTH="+readLength+",HEADCROP="+headCrop
            + ",IS_ASSEMBLY="+assemblySettings
            + ",MODE="+mode+",METHOD="+method+",READ1="+read1+",READ2="+read2+",IS_VCF="+vcf+",PTIMES="+polishingTimes
            + ",NOT_BUSCO="+busco
            + ",WORKSPACE_PATH="+workspace+" /home/yan/pbsScripts/ont_pipeline_java.pbs" });
            */
        /*
        Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_basecaller -h" });
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String s = null;
        while ((s = stdInput.readLine()) != null ) {
          //System.out.println(s);
          System.out.println(s);
        }
        */
        System.out.println("1");
        
        //Process p = Runtime.getRuntime().exec(new String[] {"bash","-c", "qsub -v NOT_BASECALLING=false,THREADS=4,FLOWCELL_ID=FLO-MIN106,KIT_NUMBER=SQK-RBK004,WORKSPACE_PATH=/home/yan/ncct/TestData/fast5_106_sqk-rbk004 /home/yan/nextflowScripts/ont/pipeline.pbs"});
        //Process p = Runtime.getRuntime().exec(new String[] {"bash","-c","qsub /home/yan/nextflowScripts/ont/adapterTrimming.pbs"});
        //Process p = Runtime.getRuntime().exec(new String[] {"bash","-c","qsub -v NOT_ADAPTERTRIMMING=false,NOT_BASECALLING=false,WORKSPACE_PATH=/home/yan/ncct/TestData/fast5_106_sqk-rbk004,THREADS=4 /home/yan/nextflowScripts/ont/adapterTrimming.pbs"});
        //Process p = Runtime.getRuntime().exec(new String[] {"bash","-c","echo 'sleep 20'|qsub"});
        //Process p = Runtime.getRuntime().exec(new String[] {"bash","-c","qsub /home/yan/pbsScripts/test.pbs"});
        System.out.println("2");
        
      } 
    });
  }
    
    /**
     * Returns the main stage.
     * @return
     */
  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args);
  }
  
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

      // Set the person into the controller.
      AdvancedBasecallingController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setBarcodeKits(p.getBarcodeKit());
      //controller.setPerson(person);
     
      // Show the dialog and wait until the user closes it
      dialogStage.showAndWait();
      //System.out.println(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      p.setBarcodeKit(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      //return controller.isOkClicked();
    } catch (IOException e) {
      e.printStackTrace();
      //return false;
    }
  }
  
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

      // Set the person into the controller.
      AdvancedReadsFilterController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setReadScore(p.getReadScore());
      controller.setReadLength(p.getReadLength());
      controller.setHeadCrop(p.getHeadCrop());
      controller.setIfAdapterTrimming(p.getIfAdapterTrimming());
      controller.setIfSplitting(p.getIfSplitting());
      //controller.setPerson(person);
     
      // Show the dialog and wait until the user closes it
      dialogStage.showAndWait();
      //System.out.println(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      //p.setBarcodeKit(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      //return controller.isOkClicked();
      p.setReadScore(controller.tfReadScore.getText());
      p.setReadLength(controller.tfReadLength.getText());
      p.setHeadCrop(controller.tfHeadCrop.getText());
      p.setIfAdapterTrimming(controller.cAdapterTrimming.isSelected());
      p.setIfSplitting(controller.cSplitting.isSelected());
    } catch (IOException e) {
      e.printStackTrace();
      //return false;
    }
  }
  
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

      // Set the person into the controller.
      AdvancedAssemblyController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setIfVcf(p.getIfVcf());
      //controller.setPerson(person);
     
      // Show the dialog and wait until the user closes it
      dialogStage.showAndWait();
      //System.out.println(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      //p.setBarcodeKit(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      //return controller.isOkClicked();
      p.setIfVcf(controller.cVcf.isSelected());
    } catch (IOException e) {
      e.printStackTrace();
      //return false;
    }
  }
  
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

      // Set the person into the controller.
      AdvancedPolishingController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setPtimes(p.getPtimes());
      //controller.setIfVcf(p.getIfVcf());
      //controller.setPerson(person);
     
      // Show the dialog and wait until the user closes it
      dialogStage.showAndWait();
      //System.out.println(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      //p.setBarcodeKit(controller.ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\""));
      //return controller.isOkClicked();
      //p.setIfVcf(controller.cVcf.isSelected());
      p.setPtimes(controller.cbPtimes.getValue());
    } catch (IOException e) {
      e.printStackTrace();
      //return false;
    }
  }
  
  public void handleStartPipeline() {
    System.out.println(p.getBarcodeKit());
    System.out.println("HeadCrop:"+p.getHeadCrop());
  }
  
}