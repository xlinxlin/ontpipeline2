package mbio.ncct.ont.view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class AdvancedAssemblyController {
  
  private Stage dialogStage;
  @FXML
  public CheckBox cVcf;
  
  public int isOK = 0;

  @FXML
  private void initialize() {
    
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
  
  public void setIfVcf(boolean ifVcf) {
    cVcf.setSelected(ifVcf);
  }
}
