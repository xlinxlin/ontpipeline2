package mbio.ncct.ont.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.validation.ValidationSupport;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class BasecallingSettingsController {

  @FXML
  public ChoiceBox<String> cbFlowcellId = new ChoiceBox<String>();
  @FXML
  public ChoiceBox<String> cbKitNumber = new ChoiceBox<String>();
  @FXML
  public CheckComboBox<String> ccbBarcodeKit = new CheckComboBox<String>() ;
  @FXML
  public CheckBox cBasecalling = new CheckBox();
  @FXML
  public Group gpBasecalling;
  
  public BasecallingSettingsController() {
    
  }
 
  @FXML
  private void initialize() throws IOException {
    ObservableList<String> olFlowcellIds = FXCollections.observableArrayList(getFlowcellIds());
    cbFlowcellId.setItems(olFlowcellIds);
    cbFlowcellId.getSelectionModel().selectFirst();
    ObservableList<String> olKitNumbers = FXCollections.observableArrayList(getKitNumbers());
    cbKitNumber.setItems(olKitNumbers);
    cbKitNumber.getSelectionModel().selectFirst();
    ObservableList<String> olBarcodeKits = FXCollections.observableArrayList(getBarcodeKits());
    ccbBarcodeKit.getItems().addAll(olBarcodeKits);
    ccbBarcodeKit.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
      public void onChanged(ListChangeListener.Change<? extends String> c) {
        System.out.println(ccbBarcodeKit.getCheckModel().getCheckedItems());
      }
    });
    
    cBasecalling.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cBasecalling.isSelected()) {
        gpBasecalling.setDisable(true);
      } else {
        gpBasecalling.setDisable(false);
      }
    });
    
    //Set the tool tips.
    /*
    Tooltip ttCbFlowcellId = new Tooltip();
    ttCbFlowcellId.setText("Select one flowcell ID.");
    cbFlowcellId.setTooltip(ttCbFlowcellId);
    
    Tooltip ttCbKitNumber = new Tooltip();
    ttCbKitNumber.setText("Select one kit number.");
    cbKitNumber.setTooltip(ttCbKitNumber);
    
    Tooltip ttCcbBarcodeKit = new Tooltip();
    ttCcbBarcodeKit.setText("Select barcode kit(s) if used.");
    ccbBarcodeKit.setTooltip(ttCcbBarcodeKit); 
    */
    
  }
  
  private ArrayList<String> getFlowcellIds() throws IOException {
    String s = null;
    ArrayList<String> arFlowcellIds = new ArrayList<String>();
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_basecaller --print_workflows | awk 'NR>2 {print $1}' | sort | uniq" });
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
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_basecaller --print_workflows | awk 'NR>2 {print $2}' | sort | uniq" });
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    while ((s = stdInput.readLine()) != null ) {
      if (s.isEmpty() == false) {
        arKitNumbers.add(s);
      }
    }
    return arKitNumbers;
  }
  
  private ArrayList<String> getBarcodeKits() throws IOException {
    String s = null;
    ArrayList<String> arBarcodeKits = new ArrayList<String>();
    Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "guppy_barcoder --print_kits | awk 'NR>1 {print $1}' | sort | uniq" });
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    while ((s = stdInput.readLine()) != null ) {
      if (s.isEmpty() == false) {
        arBarcodeKits.add(s);
      }
    }
    return arBarcodeKits;
  }
  
  
}
