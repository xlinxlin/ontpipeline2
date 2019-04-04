package mbio.ncct.ont.model;

import mbio.ncct.ont.view.GeneralSettingsController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import mbio.ncct.ont.view.BasecallingSettingsController;
import mbio.ncct.ont.view.ReadsFilterSettingsController;

public class Pipeline {

  
  private final StringProperty workspace = new SimpleStringProperty("");
  private final StringProperty selectedBarcode = new SimpleStringProperty("");
  private final StringProperty threads = new SimpleStringProperty("");
  private final StringProperty flowcellId = new SimpleStringProperty("");
  private final StringProperty kitNumber = new SimpleStringProperty("");
  private final StringProperty barcodeKit = new SimpleStringProperty("");
  private final StringProperty readScore = new SimpleStringProperty("");
  private final StringProperty readLength = new SimpleStringProperty("");
  private final StringProperty headCrop = new SimpleStringProperty("");
  private final StringProperty mode = new SimpleStringProperty("");
  private final StringProperty method = new SimpleStringProperty("");
  private final StringProperty read1Workspace = new SimpleStringProperty("");
  private final StringProperty read2Workspace = new SimpleStringProperty("");
  
  //public Pipeline() {
  //}
  
  public String getWorkspace() {
    //System.out.println(workspace+"123");
    //this.workspace.set("hello world");
    return workspace.get();
  }
  
  public void setWorkspace(String workspace) {
    this.workspace.set(workspace);
  }
  
  public String getSelectedBarcode() {
    return selectedBarcode.get();
  }
  
  
}
