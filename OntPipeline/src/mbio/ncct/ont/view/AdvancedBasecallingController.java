package mbio.ncct.ont.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * This is the controller of the advanced basecalling settings.
 *
 * @author Yan Zhou
 * created on 2019/05/14
 */
public class AdvancedBasecallingController {
  
  @FXML
  public CheckComboBox<String> ccbBarcodeKits = new CheckComboBox<String>() ;
  @FXML
  public Button btnAdBasecallApply;
  @FXML
  public ChoiceBox<String> cbGuppyMode = new ChoiceBox<String>();
  @FXML
  public ChoiceBox<String> cbDevice = new ChoiceBox<String>();
   
  private Stage dialogStage;
  
  public int isOK = 0;
  
  @FXML
  private void initialize() throws IOException {
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
  
  
  
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  private ArrayList<String> getBarcodeKits() throws IOException {
    String s = null;
    ArrayList<String> arBarcodeKits = new ArrayList<String>();
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_barcoder --print_kits | awk 'NR>1 {print $1}' | sort | uniq" });
    //Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_barcoder --print_kits | awk 'NR>1 {print $1}' | sort | uniq" });
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    while ((s = stdInput.readLine()) != null ) {
      if (s.isEmpty() == false) {
        arBarcodeKits.add(s);
      }
    }
    return arBarcodeKits;
  }
  
  @FXML
  private String OK() {
    String test = ccbBarcodeKits.getCheckModel().getCheckedItems().isEmpty() ? 
        "" : ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\"");
    isOK = 1;
    dialogStage.close();
    return test;
  }
  
  @FXML
  private void Cancel() {
    dialogStage.close();
  }
  
  public void setBarcodeKits(String barcodeKits) throws IOException {
    ObservableList<String> olBarcodeKits = FXCollections.observableArrayList(getBarcodeKits());
    ccbBarcodeKits.getItems().addAll(olBarcodeKits);
    IndexedCheckModel<String> icm = ccbBarcodeKits.getCheckModel();
    String[] strArrBarcodeKits = barcodeKits.replaceAll("\"", "").split(" ");
    for(int i=0;i<strArrBarcodeKits.length;i++) {
      icm.check(strArrBarcodeKits[i]);
    }
  }
  
  public void setGuppyMode(String guppyMode) {
    cbGuppyMode.setValue(guppyMode);
  }
  
  public void setDevice(String device) {
    cbDevice.setValue(device);
  }
  
}
