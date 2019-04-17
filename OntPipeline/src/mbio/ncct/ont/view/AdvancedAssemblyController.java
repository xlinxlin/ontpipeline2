package mbio.ncct.ont.view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class AdvancedAssemblyController {
  
  private Stage dialogStage;
  @FXML
  public CheckBox cVcf;

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
    dialogStage.close();
  }
  
  @FXML
  public void cancel() {
    dialogStage.close();
  }
  
  public void setIfVcf(boolean ifVcf) {
    cVcf.setSelected(ifVcf);
  }
}
