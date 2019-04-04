package mbio.ncct.ont.view;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ReadsFilterSettingsController {
  
  @FXML
  public TextField tfReadScore;
  @FXML
  public TextField tfReadLength;
  @FXML
  public TextField tfHeadCrop;
  @FXML
  public CheckBox cReadsFilter;
  @FXML
  public Group gpReadsFilter;
  
  
  
  public ReadsFilterSettingsController() {
    
  }
  
  @FXML
  private void initialize() {
    
    cReadsFilter.setSelected(false);
    
    cReadsFilter.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cReadsFilter.isSelected()) {
        gpReadsFilter.setDisable(true);
      } else {
        gpReadsFilter.setDisable(false);
      }
    });
  }
}
