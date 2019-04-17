package mbio.ncct.ont.view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class AdvancedPolishingController {
  
  private Stage dialogStage;
  
  @FXML
  public ChoiceBox<String> cbPtimes;
  @FXML
  public ChoiceBox cbBuscoData;
  @FXML
  public CheckBox cBusco;
  
  public int isOK = 0;
  
  @FXML
  private void initialize() {
    //tfReadScore.setText("9");
    //tfReadLength.setText("500");
    //tfHeadCrop.setText("50");
    //setInitialize()
  }
  
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }
  
  @FXML
  public void OK() {
    isOK = 1;
    dialogStage.close();
  }
  
  @FXML
  public void cancel() {
    dialogStage.close();
  }
  
  public void setPtimes(String pTimes) {
    ArrayList<String> alTimes = new ArrayList<String>();
    alTimes.add("0");
    alTimes.add("1");
    alTimes.add("2");
    alTimes.add("3");
    alTimes.add("4");
    ObservableList<String> olTimes = FXCollections.observableArrayList(alTimes);
    cbPtimes.setItems(olTimes);
    cbPtimes.setValue(pTimes);
  }
  
  public void setBuscoData () {
    
  }
}
