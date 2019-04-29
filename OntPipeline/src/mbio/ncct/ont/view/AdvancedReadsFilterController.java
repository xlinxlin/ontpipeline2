package mbio.ncct.ont.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mbio.ncct.ont.model.Pipeline;

public class AdvancedReadsFilterController {
  
  private Stage dialogStage;
  
  @FXML
  public TextField tfReadScore; 
  @FXML
  public TextField tfReadLength;
  @FXML
  public TextField tfHeadCrop; 
  @FXML
  public CheckBox cAdapterTrimming; 
  @FXML
  public CheckBox cSplitting; 
  @FXML
  public Label lbSplit;
  
  public int isOK = 0;

  @FXML
  private void initialize() {
    cAdapterTrimming.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cAdapterTrimming.isSelected()) {
        cSplitting.setDisable(false);
        lbSplit.setDisable(false);
      } else {
        cSplitting.setDisable(true);
        lbSplit.setDisable(true);
      }
    });
  }
  
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }
  
  @FXML
  private void OK() {
    if(tfReadScore.getText().matches("\\d+") && tfReadLength.getText().matches("\\d+") && tfHeadCrop.getText().matches("\\d+")) {
      isOK = 1;
      dialogStage.close();
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Only integer value is accepted. \nReset defaults.");
      alert.showAndWait();
      tfReadScore.setText("9");
      tfReadLength.setText("500");
      tfHeadCrop.setText("50");
    }
  }
  
  @FXML
  private void Cancel() {
    dialogStage.close();
  }
  
  public void setReadScore(String readScore) {
    tfReadScore.setText(readScore);
  }
  
  public void setReadLength(String readLength) {
    tfReadLength.setText(readLength);
  }
  
  public void setHeadCrop(String headCrop) {
    tfHeadCrop.setText(headCrop);
  }
  
  public void setIfAdapterTrimming(boolean ifApaterTrimming) {
    cAdapterTrimming.setSelected(ifApaterTrimming);
  }
  
  public void setIfSplitting(boolean ifSplitting) {
    cSplitting.setSelected(ifSplitting);
  }
}
