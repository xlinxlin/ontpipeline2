package mbio.ncct.ont.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import mbio.ncct.ont.model.Pipeline;

public class AdvancedBasecallingController {
  
  @FXML
  public CheckComboBox<String> ccbBarcodeKits = new CheckComboBox<String>() ;
  @FXML
  public Button btnAdBasecallApply;
  
  private Stage dialogStage;
  
  public int isOK = 0;
  
  @FXML
  private void initialize() throws IOException {
    //ObservableList<String> olBarcodeKits = FXCollections.observableArrayList(getBarcodeKits());
    //ccbBarcodeKits.getItems().addAll(olBarcodeKits);
    //IndexedCheckModel<String> v = ccbBarcodeKits.getCheckModel();
    //ccbBarcodeKits.checkModelProperty().setValue(v);
    //v.check(2);
    //ccbBarcodeKits.setCheckModel("123");
    /*
    ccbBarcodeKits.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
      public void onChanged(ListChangeListener.Change<? extends String> c) {
        System.out.println(ccbBarcodeKits.getCheckModel().getCheckedItems());
      }
    }); 
    */
  }
  
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
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
  
  @FXML
  private String OK() {
    String test = ccbBarcodeKits.getCheckModel().getCheckedItems().isEmpty() ? 
        "" : ccbBarcodeKits.getCheckModel().getCheckedItems().toString().replace(",", "").replaceAll("\\[|\\]", "\"");
    //p.setWorkspace(test);
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
    //System.out.println("String:"+barcodeKits);
    String[] strArrBarcodeKits = barcodeKits.replaceAll("\"", "").split(" ");
    for(int i=0;i<strArrBarcodeKits.length;i++) {
      icm.check(strArrBarcodeKits[i]);
    }
  }
  
}
