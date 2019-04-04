package mbio.ncct.ont.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GeneralSettingsModel {
  
  private final StringProperty workspace = new SimpleStringProperty("");
  private final StringProperty selectedBarcode = new SimpleStringProperty("");
  private final StringProperty threads = new SimpleStringProperty("");
  
  public GeneralSettingsModel() {
    
  }
  
  public void setWorkspace(String workspace) {
    this.workspace.set(workspace);
  }
  
  public String getWorkspace() {
    return workspace.get();
  }
  
  public void setSelectedBarcode(String selectedBarcode) {
    this.selectedBarcode.set(selectedBarcode);
  }
  
  public String getSelectedBarcode() {
    return selectedBarcode.get();
  }
  
  public void setThreads(String threads) {
    this.selectedBarcode.set(threads);
  }
  
  public String getThreads() {
    return threads.get();
  }

}
