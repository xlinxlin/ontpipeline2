package mbio.ncct.ont.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * This is the controller of the advanced basecalling settings.
 *
 * @author Yan Zhou
 * created on 2019/05/14
 */
public class AdvancedBasecallingController {
  
  /** Initializes log4j2. */
  static Logger logger = LogManager.getLogger(AdvancedBasecallingController.class);
  
  /** The check combo box for barcode kits . */
  @FXML
  public CheckComboBox<String> ccbBarcodeKits = new CheckComboBox<String>() ;
  
  /** The choice box for Guppy mode. */
  @FXML
  public ChoiceBox<String> cbGuppyMode = new ChoiceBox<String>();
  
  /** The choice box for ONT device. */
  @FXML
  public ChoiceBox<String> cbDevice = new ChoiceBox<String>();
   
  /** The advanced basecalling dialog stage. */
  private Stage dialogStage;
  
  /** Signal for if OK is clicked. */
  public int isOK = 0;
  
  /**
   * Initializes the controller of advanced basecalling settings.
   */
  @FXML
  private void initialize() {
    ArrayList<String> alGuppyMode = new ArrayList<String>();
    alGuppyMode.add("fast");
    alGuppyMode.add("high-accuracy");
    ObservableList<String> olGuppyMode = FXCollections.observableArrayList(alGuppyMode);
    cbGuppyMode.setItems(olGuppyMode);
    
    ArrayList<String> alDevice = new ArrayList<String>();
    alDevice.add("MinION/GridION/MinIT");
    alDevice.add("PromethION");
    ObservableList<String> olDevice = FXCollections.observableArrayList(alDevice);
    cbDevice.setItems(olDevice);
  }
  
  /**
   * Set the dialog stage for advanced basecalling setting.
   * @dialogStage Dialog stage.
   */
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  /**
   * Get all barcode kits.
   * @return an Array List with all barcode kits.
   */
  private ArrayList<String> getBarcodeKits() {
    String s = null;
    ArrayList<String> arBarcodeKits = new ArrayList<String>();
    Process p = null;
    try {
      p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_barcoder --print_kits | awk 'NR>1 {print $1}' | sort | uniq" });
    } catch (Exception e) {
      logger.error("Can not get barcode kits. " + e);
    }
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    try {
      while ((s = stdInput.readLine()) != null ) {
        if (s.isEmpty() == false) {
          arBarcodeKits.add(s);
        }
      }
    } catch (Exception e) {
      logger.error("Can not read barcode kits. " + e);
    }
    return arBarcodeKits;
  }
  
  /**
   * Called if OK button is clicked, set isOK to 1 and close the dialog.
   * @return String of all barcode kits.
   */
  @FXML
  private String OK() {
    String sBarcodeKits = ccbBarcodeKits.getCheckModel().getCheckedItems().isEmpty() ? 
        "" : ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\"");
    isOK = 1;
    dialogStage.close();
    return sBarcodeKits;
  }
  
  /**
   * Called if Cancel button is clicked, set isOK to 0 and close the dialog.
   */
  @FXML
  private void Cancel() {
    dialogStage.close();
  }
  
  /**
   * Set selected barcode kits into the box.
   * @param barcodeKits String of selected barcode kits
   */
  public void setBarcodeKits(String barcodeKits) {
    ObservableList<String> olBarcodeKits = FXCollections.observableArrayList(getBarcodeKits());
    ccbBarcodeKits.getItems().addAll(olBarcodeKits);
    IndexedCheckModel<String> icm = ccbBarcodeKits.getCheckModel();
    String[] strArrBarcodeKits = barcodeKits.replaceAll("\"", "").split(" ");
    for(int i=0;i<strArrBarcodeKits.length;i++) {
      icm.check(strArrBarcodeKits[i]);
    }
  }
  
  /**
   * Set Guppy mode.
   * @param guppyMode String of Guppy mode.
   */
  public void setGuppyMode(String guppyMode) {
    cbGuppyMode.setValue(guppyMode);
  }
  
  /**
  * Set ONT device.
  * @param device String of ONT device.
  */
  public void setDevice(String device) {
    cbDevice.setValue(device);
  }
}
