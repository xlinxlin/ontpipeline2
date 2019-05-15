package mbio.ncct.ont.view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is the controller of the advanced polishing settings.
 *
 * @author Yan Zhou
 * created on 2019/05/14
 */
public class AdvancedPolishingController {
  
  private Stage dialogStage;
  
  @FXML
  public ChoiceBox<String> cbPtimes;
  @FXML
  public ChoiceBox<String> cbBuscoData;
  @FXML
  public CheckBox cBusco;
  @FXML
  public Label lbDatabase;
  
  public int isOK = 0;
  
  /**
   * Initializes the controller of advanced polishing settings.
   */
  @FXML
  private void initialize() {
    cbBuscoData.setDisable(true);
    lbDatabase.setDisable(true);
    cBusco.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(cBusco.isSelected()) {
        cbBuscoData.setDisable(false);
        lbDatabase.setDisable(false);
      } else {
        cbBuscoData.setDisable(true);
        lbDatabase.setDisable(true);
      }
    });
  }
  
  
  /**
   * Set the advanced polishing setting dialog stage.
   * @param dialogStage advanced polishing setting dialog stage
   */
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }
  
  /**
   * If OK button is clicked, return 1, close the advanced polishing setting dialog.
   */
  @FXML
  private void OK() {
    isOK = 1;
    dialogStage.close();
  }
  
  /**
   * If Cancel button is clicked, return 0, close the advanced polishing setting dialog.
   */
  @FXML
  private void cancel() {
    dialogStage.close();
  }
  
  /**
   * Initializes and set the polishing times.
   * @param pTimes the initial value of polishing times
   */
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
  
  /**
   * Initializes and set the BUSCO database.
   * @param buscoData the initial value of BUSCO database
   */
  public void setBuscoData (String buscoData) {
    ArrayList<String> alDatabase = new ArrayList<String>();
    alDatabase.add("Bacteria");
    alDatabase.add("Proteobacteria");
    alDatabase.add("Rhizobiales");
    alDatabase.add("Beta_proteobacteria");
    alDatabase.add("Gamma_proteobacteria");
    alDatabase.add("Enterobacteriales");
    alDatabase.add("Delta_Epsilon_proteobacteria");
    alDatabase.add("Actinobacteria");
    alDatabase.add("Cyanobacteria");
    alDatabase.add("Firmicutes");
    alDatabase.add("Clostridia");
    alDatabase.add("Lactobacillales");
    alDatabase.add("Bacillales");
    alDatabase.add("Enterobacteriales");
    alDatabase.add("Bacteroidetes");
    alDatabase.add("Spirochaetes");
    alDatabase.add("Tenericutes");
    ObservableList<String> olDatabase = FXCollections.observableArrayList(alDatabase);
    cbBuscoData.setItems(olDatabase);
    cbBuscoData.setValue(buscoData);
  }
  
  /**
   * Initializes and set if BUSCO check will be used or not.
   * @param ifBusco true if BUSCO check will be used
   */
  public void setIfBusco (boolean ifBusco) {
    cBusco.setSelected(ifBusco);
  }
}
