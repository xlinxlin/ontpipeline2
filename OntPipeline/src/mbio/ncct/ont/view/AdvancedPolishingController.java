package mbio.ncct.ont.view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
  
  @FXML
  private void initialize() {
    //tfReadScore.setText("9");
    //tfReadLength.setText("500");
    //tfHeadCrop.setText("50");
    //setInitialize()
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
  
  public void setIfBusco (boolean ifBusco) {
    cBusco.setSelected(ifBusco);
  }
}
