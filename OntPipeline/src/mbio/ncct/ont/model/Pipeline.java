package mbio.ncct.ont.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Pipeline The model of pipeline.
 *
 * @author Yan Zhou
 * created on 2019/05/14
 */
public class Pipeline {
  /** Initializes and sets the workspace. */
  private final StringProperty workspace = new SimpleStringProperty("");
  
  /** Initializes and sets the specified barcode(s). */
  private final StringProperty selectedBarcode = new SimpleStringProperty("");
  
  /** Initializes and sets the threads. */
  private final StringProperty threads = new SimpleStringProperty("8");
  
  /** Initializes and sets the flowcell ID. */
  private final StringProperty flowcellId = new SimpleStringProperty("FLO-MIN106");
  
  /** Initializes and sets the kit number. */
  private final StringProperty kitNumber = new SimpleStringProperty("SQK-LSK109");
  
  /** Initializes and sets the barcode kit(s). */
  private final StringProperty barcodeKit = new SimpleStringProperty("");
  
  /** Initializes and sets the read score for filter. */
  private final StringProperty readScore = new SimpleStringProperty("9");
  
  /** Initializes and sets the read length for filter. */
  private final StringProperty readLength = new SimpleStringProperty("500");
  
  /** Initializes and sets the head crop for filter. */
  private final StringProperty headCrop = new SimpleStringProperty("50");
  
  /** Initializes and sets the assembly mode. */
  private final StringProperty mode = new SimpleStringProperty("normal");
  
  /** Initializes and sets the assembly method. */
  private final StringProperty method = new SimpleStringProperty("Hybrid assembly");
  
  /** Initializes and sets if adapter trimming will be used. */
  private final BooleanProperty ifAdapterTrimming = new SimpleBooleanProperty(true);
  
  /** Initializes and sets if skip splitting reads based on middle adapters. */
  private final BooleanProperty ifNoSplit = new SimpleBooleanProperty(false);
  
  /** Initializes and sets if a .vcf file will be output. */
  private final BooleanProperty ifVcf = new SimpleBooleanProperty(false);
  
  /** Initializes and sets the polishing times. */
  private final StringProperty pTimes = new SimpleStringProperty("1");
  
  /** Initializes and sets if BUSCO check will be used. */
  private final BooleanProperty ifBusco = new SimpleBooleanProperty(false);
  
  /** Initializes and sets the database of BUSCO. */
  private final StringProperty buscoDatabase = new SimpleStringProperty("Bacteria");
  
  /** Initializes and sets if basecalling will be used. */
  private final BooleanProperty ifBasecalling = new SimpleBooleanProperty(true);
  
  /** Initializes and sets if reads filter will be used. */
  private final BooleanProperty ifReadsFilter = new SimpleBooleanProperty(true);
  
  /** Initializes and sets if assembly will be used. */
  private final BooleanProperty ifAssembly = new SimpleBooleanProperty(true);
  
  /** Initializes and sets if polishing will be used. */
  private final BooleanProperty ifPolishing = new SimpleBooleanProperty(true);
  
  /** Initializes and sets Guppy mode. */
  private final StringProperty guppyMode = new SimpleStringProperty("high-accuracy");
  
  /** Initializes and sets nanopore device. */
  private final StringProperty device = new SimpleStringProperty("PromethION");
  
  /** Initializes and sets if Guppy fast mode will be used. */
  private final BooleanProperty ifGuppyFast = new SimpleBooleanProperty(false);
  
  /** Initializes and sets Guppy .cfg configuration file. */
  private final StringProperty guppyCfgFile = new SimpleStringProperty("");
  
  /**
   * Gets the workspace.
   * @return the String of workspace
   */
  public String getWorkspace() {
    return workspace.get();
  }
  
  /**
   * Sets the workspace.
   * @param workspace the String of workspace
   */
  public void setWorkspace(String workspace) {
    this.workspace.set(workspace);
  }
  
  /**
   * Gets the specified barcode(s).
   * @return the String of the specified barcode(s)
   */
  public String getSelectedBarcode() {
    return selectedBarcode.get();
  }
  
  /**
   * Sets the specified barcode(s).
   * @param selectedBarcode the String of the specified barcode(s)
   */
  public void setSelectedBarcode(String selectedBarcode) {
    this.selectedBarcode.set(selectedBarcode);
  }
  
  /**
   * Gets the threads.
   * @return the String of threads
   */
  public String getThreads() {
    return threads.get();
  }
  
  /**
   * Sets the threads.
   * @param threads the String of the threads
   */
  public void setThreads(String threads) {
    this.threads.set(threads);
  }
  
  /**
   * Gets the flowcell ID.
   * @return the String of flowcell iD
   */
  public String getFlowcellId() {
    return flowcellId.get();
  }
  
  /**
   * Sets the flowcell ID.
   * @param flowcellId the String of the flowcell ID
   */
  public void setFlowcellId(String flowcellId) {
    this.flowcellId.set(flowcellId);
  }
  
  /**
   * Gets the kit number.
   * @return the String of kit number
   */
  public String getKitNumber() {
    return kitNumber.get();
  }
  
  /**
   * Sets the kit number.
   * @param kitNumber the String of the kit number
   */
  public void setKitNumber(String kitNumber) {
    this.kitNumber.set(kitNumber);
  }
  
  /**
   * Gets the barcode kit(s).
   * @return the String of the barcode kit(s)
   */
  public String getBarcodeKit() {
    return barcodeKit.get();
  }
  
  /**
   * Sets the barcode kit(s).
   * @param barcodeKit the String of the barcode kit(s)
   */
  public void setBarcodeKit(String barcodeKit) {
    this.barcodeKit.set(barcodeKit);
  }
  
  /**
   * Gets the read score.
   * @return the String of read score
   */
  public String getReadScore() {
    return readScore.get();
  }
  
  /**
   * Sets the read score.
   * @param readScore the String of the read score
   */
  public void setReadScore(String readScore) {
    this.readScore.set(readScore);
  }
  
  /**
   * Gets the read length.
   * @return the String of the read length
   */
  public String getReadLength() {
    return readLength.get();
  }
  
  /**
   * Sets the read length.
   * @param readLength the String of the read length
   */
  public void setReadLength(String readLength) {
    this.readLength.set(readLength);
  }
  
  /**
   * Gets the head crop.
   * @return the String of the head crop
   */
  public String getHeadCrop() {
    return headCrop.get();
  }
  
  /**
   * Sets the head crop.
   * @param headCrop the String of the head crop
   */
  public void setHeadCrop(String headCrop) {
    this.headCrop.set(headCrop);
  }
  
  /**
   * Gets the assembly mode.
   * @return the String of the assembly mode
   */
  public String getMode() {
    return mode.get();
  }
  
  /**
   * Sets the assembly mode.
   * @param mode the String of the assembly mode
   */
  public void setMode(String mode) {
    this.mode.set(mode);
  }
  
  /**
   * Gets the Guppy mode.
   * @return the String of the Guppy mode
   */
  public String getGuppyMode() {
    return guppyMode.get();
  }
  
  /**
   * Sets the Guppy mode.
   * @param guppyMode the String of the Guppy mode
   */
  public void setGuppyMode(String guppyMode) {
    this.guppyMode.set(guppyMode);
  }
  
  /**
   * Gets the nanopore device.
   * @return the String of the nanopore device
   */
  public String getDevice() {
    return device.get();
  }
  
  /**
   * Sets the nanopore device.
   * @param device the String of the nanopore device
   */
  public void setDevice(String device) {
    this.device.set(device);
  }
  
  /**
   * Gets the assembly method.
   * @return the String of the assembly method
   */
  public String getMethod() {
    return method.get();
  }
  
  /**
   * Sets the assembly method.
   * @param method the String of the assembly method
   */
  public void setMethod(String method) {
    this.method.set(method);
  }
  
  /**
   * Sets if adapter trimming will be used.
   * @param ifAdapterTrimming the Boolean value of if adapter trimming will be used
   */
  public void setIfAdapterTrimming(Boolean ifAdapterTrimming) {
    this.ifAdapterTrimming.set(ifAdapterTrimming);
  }
  
  /**
   * Gets if adapter trimming is used.
   * @return the Boolean value of if adapter trimming is used
   */
  public Boolean getIfAdapterTrimming() {
    return ifAdapterTrimming.get();
  }
  
  /**
   * Sets if split reads in Porechop.
   * @param ifNoSplit the Boolean value of if split reads in Porechop
   */
  public void setIfNoSplit(Boolean ifNoSplit) {
    this.ifNoSplit.set(ifNoSplit);
  }
  
  /**
   * Gets if split the reads in Porechop.
   * @return the Boolean value of if split the reads in Porechop
   */
  public Boolean getIfNoSplit() {
    return ifNoSplit.get();
  }
  
  /**
   * Sets if .vcf file will be produced.
   * @param ifVcf The Boolean value of if .vcf file will be produced.
   */
  public void setIfVcf(Boolean ifVcf) {
    this.ifVcf.set(ifVcf);
  }
  
  /**
   * Gets if a .vcf file will be produced.
   * @return the Boolean value of if if a .vcf file will be produced
   */
  public Boolean getIfVcf() {
    return ifVcf.get();
  }
  
  /**
   * Gets the polishing times.
   * @return the String of polishing times
   */
  public String getPtimes() {
    return pTimes.get();
  }
  
  /**
   * Sets the polishing times.
   * @param pTimes The polishing times.
   */
  public void setPtimes(String pTimes) {
    this.pTimes.set(pTimes);
  }
  
  /**
   * Sets if basecalling will be used.
   * @param ifBasecalling the Boolean value of if basecalling will be used
   */
  public void setIfBasecalling(Boolean ifBasecalling) {
    this.ifBasecalling.set(ifBasecalling);
  }
  
  /**
   * Gets if basecalling will be used.
   * @return the String of if basecalling will be used
   */
  public Boolean getIfBasecalling() {
    return ifBasecalling.get();
  }
  
  /**
   * Sets if reads filter will be used.
   * @param ifReadsFilter the Boolean value of if reads filter will be used
   */
  public void setIfReadsFilter(Boolean ifReadsFilter) {
    this.ifReadsFilter.set(ifReadsFilter);
  }
  
  /**
   * Gets if reads filter will be used.
   * @return the Boolean value of if reads filter will be used
   */
  public Boolean getIfReadsFilter() {
    return ifReadsFilter.get();
  }
  
  /**
   * Sets if assembly will be used.
   * @param ifAssembly the Boolean value of if assembly will be used
   */
  public void setIfAssembly(Boolean ifAssembly) {
    this.ifAssembly.set(ifAssembly);
  }
  
  /**
   * Gets if assembly will be used.
   * @return the Boolean value of if assembly will be used
   */
  public Boolean getIfAssembly() {
    return ifAssembly.get();
  }
  
  /**
   * Sets if polishing will be used.
   * @param ifPolishing the Boolean value of if polishing will be used
   */
  public void setIfPolishing(Boolean ifPolishing) {
    this.ifPolishing.set(ifPolishing);
  }
  
  /**
   * Gets if polishing will be used.
   * @return the Boolean value of if polishing will be used
   */
  public Boolean getIfPolishing() {
    return ifPolishing.get();
  }
  
  /**
   * Gets the database of BUSCO.
   * @return the String of the BUSCO database
   */
  public String getBuscoData() {
    return buscoDatabase.get();
  }
  
  /**
   * Sets the BUSCO database.
   * @param buscoDatabase the String of busco database
   */
  public void setBuscoData(String buscoDatabase) {
    this.buscoDatabase.set(buscoDatabase);
  }
  
  /**
   * Sets if BUSCO will be used.
   * @param ifBusco the Boolean value of if BUSCO will be used
   */
  public void setIfBusco(Boolean ifBusco) {
    this.ifBusco.set(ifBusco);
  }
  
  /**
   * Gets if BUSCO will be used.
   * @return the Boolean value of if BUSCO will be used
   */
  public Boolean getIfBusco() {
    return ifBusco.get();
  }
  
  /**
   * Sets if Guppy fast mode will be used.
   * @param ifGuppyFast the Boolean value of if Guppy fast mode will be used
   */
  public void setIfGuppyFast(Boolean ifGuppyFast) {
    this.ifGuppyFast.set(ifGuppyFast);
  }
  
  /**
   * Gets if Guppy fast mode will be used.
   * @return the Boolean value of if Guppy fast mode will be used
   */
  public Boolean getIfGuppyFast() {
    return ifGuppyFast.get();
  }
  
  /**
   * Gets the Guppy configuration file.
   * @return the String of the Guppy configuration file
   */
  public String getGuppyCfgFile() {
    return guppyCfgFile.get();
  }
  
  /**
   * Sets the Guppy configuration file.
   * @param guppyCfgFile The String of the Guppy configuration file.
   */
  public void setGuppyCfgFile(String guppyCfgFile) {
    this.guppyCfgFile.set(guppyCfgFile);
  }
}