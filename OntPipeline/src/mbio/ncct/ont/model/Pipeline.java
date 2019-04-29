package mbio.ncct.ont.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pipeline {

  private final StringProperty workspace = new SimpleStringProperty("");
  private final StringProperty selectedBarcode = new SimpleStringProperty("");
  private final StringProperty threads = new SimpleStringProperty("4");
  private final StringProperty flowcellId = new SimpleStringProperty("FLO-MIN106");
  private final StringProperty kitNumber = new SimpleStringProperty("SQK-LSK109");
  private final StringProperty barcodeKit = new SimpleStringProperty("");
  private final StringProperty readScore = new SimpleStringProperty("9");
  private final StringProperty readLength = new SimpleStringProperty("500");
  private final StringProperty headCrop = new SimpleStringProperty("50");
  private final StringProperty mode = new SimpleStringProperty("normal");
  private final StringProperty method = new SimpleStringProperty("Hybrid assembly");
  //private final StringProperty read1Workspace = new SimpleStringProperty("");
  //private final StringProperty read2Workspace = new SimpleStringProperty("");
  private final BooleanProperty ifAdapterTrimming = new SimpleBooleanProperty(true);
  private final BooleanProperty ifNoSplit = new SimpleBooleanProperty(false);
  private final BooleanProperty ifVcf = new SimpleBooleanProperty(false);
  private final StringProperty pTimes = new SimpleStringProperty("1");
  private final BooleanProperty ifBusco = new SimpleBooleanProperty(false);
  private final StringProperty buscoDatabase = new SimpleStringProperty("Bacteria");
  private final BooleanProperty ifBasecalling = new SimpleBooleanProperty(true);
  private final BooleanProperty ifReadsFilter = new SimpleBooleanProperty(true);
  private final BooleanProperty ifAssembly = new SimpleBooleanProperty(true);
  private final BooleanProperty ifPolishing = new SimpleBooleanProperty(true);
  
  
  public String getWorkspace() {
    return workspace.get();
  }
  
  public void setWorkspace(String workspace) {
    this.workspace.set(workspace);
  }
  
  public String getSelectedBarcode() {
    return selectedBarcode.get();
  }
  
  public void setSelectedBarcode(String selectedBarcode) {
    this.selectedBarcode.set(selectedBarcode);
  }
  
  public String getThreads() {
    return threads.get();
  }
  
  public void setThreads(String threads) {
    this.threads.set(threads);
  }
  
  public String getFlowcellId() {
    return flowcellId.get();
  }
  
  public void setFlowcellId(String flowcellId) {
    this.flowcellId.set(flowcellId);
  }
  
  public String getKitNumber() {
    return kitNumber.get();
  }
  
  public void setKitNumber(String kitNumber) {
    this.kitNumber.set(kitNumber);
  }
  
  public String getBarcodeKit() {
    return barcodeKit.get();
  }
  
  public void setBarcodeKit(String barcodeKit) {
    this.barcodeKit.set(barcodeKit);
  }
  
  public String getReadScore() {
    return readScore.get();
  }
  
  public void setReadScore(String readScore) {
    this.readScore.set(readScore);
  }
  
  public String getReadLength() {
    return readLength.get();
  }
  
  public void setReadLength(String readLength) {
    this.readLength.set(readLength);
  }
  
  public String getHeadCrop() {
    return headCrop.get();
  }
  
  public void setHeadCrop(String headCrop) {
    this.headCrop.set(headCrop);
  }
  
  public String getMode() {
    return mode.get();
  }
  
  public void setMode(String mode) {
    this.mode.set(mode);
  }
  
  public String getMethod() {
    return method.get();
  }
  
  public void setMethod(String method) {
    this.method.set(method);
  }
  
  public void setIfAdapterTrimming(Boolean ifAdapterTrimming) {
    this.ifAdapterTrimming.set(ifAdapterTrimming);
  }
  
  public Boolean getIfAdapterTrimming() {
    return ifAdapterTrimming.get();
  }
  
  public void setIfNoSplit(Boolean ifNoSplit) {
    this.ifNoSplit.set(ifNoSplit);
  }
  
  public Boolean getIfNoSplit() {
    return ifNoSplit.get();
  }
  
  public void setIfVcf(Boolean ifVcf) {
    this.ifVcf.set(ifVcf);
  }
  
  public Boolean getIfVcf() {
    return ifVcf.get();
  }
  
  public String getPtimes() {
    return pTimes.get();
  }
  
  public void setPtimes(String pTimes) {
    this.pTimes.set(pTimes);
  }
  
  public void setIfBasecalling(Boolean ifBasecalling) {
    this.ifBasecalling.set(ifBasecalling);
  }
  
  public Boolean getIfBasecalling() {
    return ifBasecalling.get();
  }
  
  public void setIfReadsFilter(Boolean ifReadsFilter) {
    this.ifReadsFilter.set(ifReadsFilter);
  }
  
  public Boolean getIfReadsFilter() {
    return ifReadsFilter.get();
  }
  
  public void setIfAssembly(Boolean ifAssembly) {
    this.ifAssembly.set(ifAssembly);
  }
  
  public Boolean getIfAssembly() {
    return ifAssembly.get();
  }
  
  public void setIfPolishing(Boolean ifPolishing) {
    this.ifPolishing.set(ifPolishing);
  }
  
  public Boolean getIfPolishing() {
    return ifPolishing.get();
  }
  
  public String getBuscoData() {
    return buscoDatabase.get();
  }
  
  public void setBuscoData(String buscoDatabase) {
    this.buscoDatabase.set(buscoDatabase);
  }
  
  public void setIfBusco(Boolean ifBusco) {
    this.ifBusco.set(ifBusco);
  }
  
  public Boolean getIfBusco() {
    return ifBusco.get();
  }
}
