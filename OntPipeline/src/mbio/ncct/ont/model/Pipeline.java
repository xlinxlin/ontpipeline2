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
  /** Initialize and set the workspace. */
  private final StringProperty workspace = new SimpleStringProperty("");
  
  /** Initialize and set the specified barcode(s). */
  private final StringProperty selectedBarcode = new SimpleStringProperty("");
  
  /** Initialize and set the threads. */
  private final StringProperty threads = new SimpleStringProperty("8");
  
  /** Initialize and set the flowcell ID. */
  private final StringProperty flowcellId = new SimpleStringProperty("FLO-MIN106");
  
  /** Initialize and set the kit number. */
  private final StringProperty kitNumber = new SimpleStringProperty("SQK-LSK109");
  
  /** Initialize and set the barcode kit(s). */
  private final StringProperty barcodeKit = new SimpleStringProperty("");
  
  /** Initialize and set the read score for filter. */
  private final StringProperty readScore = new SimpleStringProperty("9");
  
  /** Initialize and set the read length for filter. */
  private final StringProperty readLength = new SimpleStringProperty("500");
  
  /** Initialize and set the head crop for filter. */
  private final StringProperty headCrop = new SimpleStringProperty("50");
  
  /** Initialize and set the assembly mode. */
  private final StringProperty mode = new SimpleStringProperty("normal");
  
  /** Initialize and set the assembly method. */
  private final StringProperty method = new SimpleStringProperty("Hybrid assembly");
  
  /** Initialize and set if adapter trimming will be used. */
  private final BooleanProperty ifAdapterTrimming = new SimpleBooleanProperty(true);
  
  /** Initialize and set if skip splitting reads based on middle adapters. */
  private final BooleanProperty ifNoSplit = new SimpleBooleanProperty(false);
  
  /** Initialize and set if a .vcf file will be output. */
  private final BooleanProperty ifVcf = new SimpleBooleanProperty(false);
  
  /** Initialize and set the polishing times. */
  private final StringProperty pTimes = new SimpleStringProperty("1");
  
  /** Initialize and set if BUSCO check will be used. */
  private final BooleanProperty ifBusco = new SimpleBooleanProperty(false);
  
  /** Initialize and set the database of BUSCO. */
  private final StringProperty buscoDatabase = new SimpleStringProperty("Bacteria");
  
  /** Initialize and set if basecalling will be used. */
  private final BooleanProperty ifBasecalling = new SimpleBooleanProperty(true);
  
  /** Initialize and set if reads filter will be used. */
  private final BooleanProperty ifReadsFilter = new SimpleBooleanProperty(true);
  
  /** Initialize and set if assembly will be used. */
  private final BooleanProperty ifAssembly = new SimpleBooleanProperty(true);
  
  /** Initialize and set if polishing will be used. */
  private final BooleanProperty ifPolishing = new SimpleBooleanProperty(true);
  
  /** Initialize and set Guppy mode. */
  private final StringProperty guppyMode = new SimpleStringProperty("high-accuracy");
  
  /** Initialize and set nanopore device. */
  private final StringProperty device = new SimpleStringProperty("PromethION");
  
  /** Initialize and set if Guppy fast mode will be used. */
  private final BooleanProperty ifGuppyFast = new SimpleBooleanProperty(false);
  
  /** Initialize and set Guppy .cfg configuration file. */
  private final StringProperty guppyCfgFile = new SimpleStringProperty("");
  
  /**
   * Get the workspace.
   * @return the String of workspace
   */
  public String getWorkspace() {
    return workspace.get();
  }
  
  /**
   * Set the workspace.
   * @param workspace the String of workspace
   */
  public void setWorkspace(String workspace) {
    this.workspace.set(workspace);
  }
  
  /**
   * Get the specified barcode(s).
   * @return the String of the specified barcode(s)
   */
  public String getSelectedBarcode() {
    return selectedBarcode.get();
  }
  
  /**
   * Set the specified barcode(s).
   * @param selectedBarcode the String of the specified barcode(s)
   */
  public void setSelectedBarcode(String selectedBarcode) {
    this.selectedBarcode.set(selectedBarcode);
  }
  
  /**
   * Get the threads.
   * @return the String of threads
   */
  public String getThreads() {
    return threads.get();
  }
  
  /**
   * Set the threads.
   * @param threads the String of the threads
   */
  public void setThreads(String threads) {
    this.threads.set(threads);
  }
  
  /**
   * Get the flowcell ID.
   * @return the String of flowcell iD
   */
  public String getFlowcellId() {
    return flowcellId.get();
  }
  
  /**
   * Set the flowcell ID.
   * @param flowcellId the String of the flowcell ID
   */
  public void setFlowcellId(String flowcellId) {
    this.flowcellId.set(flowcellId);
  }
  
  /**
   * Get the kit number.
   * @return the String of kit number
   */
  public String getKitNumber() {
    return kitNumber.get();
  }
  
  /**
   * Set the kit number.
   * @param kitNumber the String of the kit number
   */
  public void setKitNumber(String kitNumber) {
    this.kitNumber.set(kitNumber);
  }
  
  /**
   * Get the barcode kit(s).
   * @return the String of the barcode kit(s)
   */
  public String getBarcodeKit() {
    return barcodeKit.get();
  }
  
  /**
   * Set the barcode kit(s).
   * @param barcodeKit the String of the barcode kit(s)
   */
  public void setBarcodeKit(String barcodeKit) {
    this.barcodeKit.set(barcodeKit);
  }
  
  /**
   * Get the read score.
   * @return the String of read score
   */
  public String getReadScore() {
    return readScore.get();
  }
  
  /**
   * Set the read score.
   * @param readScore the String of the read score
   */
  public void setReadScore(String readScore) {
    this.readScore.set(readScore);
  }
  
  /**
   * Get the read length.
   * @return the String of the read length
   */
  public String getReadLength() {
    return readLength.get();
  }
  
  /**
   * Set the read length.
   * @param readLength the String of the read length
   */
  public void setReadLength(String readLength) {
    this.readLength.set(readLength);
  }
  
  /**
   * Get the head crop.
   * @return the String of the head crop
   */
  public String getHeadCrop() {
    return headCrop.get();
  }
  
  /**
   * Set the head crop.
   * @param headCrop the String of the head crop
   */
  public void setHeadCrop(String headCrop) {
    this.headCrop.set(headCrop);
  }
  
  /**
   * Get the assembly mode.
   * @return the String of the assembly mode
   */
  public String getMode() {
    return mode.get();
  }
  
  /**
   * Set the assembly mode.
   * @param mode the String of the assembly mode
   */
  public void setMode(String mode) {
    this.mode.set(mode);
  }
  
  /**
   * Get the Guppy mode.
   * @return the String of the Guppy mode
   */
  public String getGuppyMode() {
    return guppyMode.get();
  }
  
  /**
   * Set the Guppy mode.
   * @param guppyMode the String of the Guppy mode
   */
  public void setGuppyMode(String guppyMode) {
    this.guppyMode.set(guppyMode);
  }
  
  /**
   * Get the nanopore device.
   * @return the String of the nanopore device
   */
  public String getDevice() {
    return device.get();
  }
  
  /**
   * Set the nanopore device.
   * @param device the String of the nanopore device
   */
  public void setDevice(String device) {
    this.device.set(device);
  }
  
  /**
   * Get the assembly method.
   * @return the String of the assembly method
   */
  public String getMethod() {
    return method.get();
  }
  
  /**
   * Set the assembly method.
   * @param method the String of the assembly method
   */
  public void setMethod(String method) {
    this.method.set(method);
  }
  
  /**
   * Set if adapter trimming will be used.
   * @param ifAdapterTrimming the Boolean value of if adapter trimming will be used
   */
  public void setIfAdapterTrimming(Boolean ifAdapterTrimming) {
    this.ifAdapterTrimming.set(ifAdapterTrimming);
  }
  
  /**
   * Get if adapter trimming is used.
   * @return the Boolean value of if adapter trimming is used
   */
  public Boolean getIfAdapterTrimming() {
    return ifAdapterTrimming.get();
  }
  
  /**
   * Set if split reads in Porechop.
   * @param ifNoSplit the Boolean value of if split reads in Porechop
   */
  public void setIfNoSplit(Boolean ifNoSplit) {
    this.ifNoSplit.set(ifNoSplit);
  }
  
  /**
   * Get if split the reads in Porechop.
   * @return the Boolean value of if split the reads in Porechop
   */
  public Boolean getIfNoSplit() {
    return ifNoSplit.get();
  }
  
  /**
   * Set if .vcf file will be produced.
   * @param ifVcf The Boolean value of if .vcf file will be produced.
   */
  public void setIfVcf(Boolean ifVcf) {
    this.ifVcf.set(ifVcf);
  }
  
  /**
   * Get if a .vcf file will be produced.
   * @return the Boolean value of if if a .vcf file will be produced
   */
  public Boolean getIfVcf() {
    return ifVcf.get();
  }
  
  /**
   * Get the polishing times.
   * @return the String of polishing times
   */
  public String getPtimes() {
    return pTimes.get();
  }
  
  /**
   * Set the polishing times.
   * @param pTimes The polishing times.
   */
  public void setPtimes(String pTimes) {
    this.pTimes.set(pTimes);
  }
  
  /**
   * Set if basecalling will be used.
   * @param ifBasecalling the Boolean value of if basecalling will be used
   */
  public void setIfBasecalling(Boolean ifBasecalling) {
    this.ifBasecalling.set(ifBasecalling);
  }
  
  /**
   * Get if basecalling will be used.
   * @return the String of if basecalling will be used
   */
  public Boolean getIfBasecalling() {
    return ifBasecalling.get();
  }
  
  /**
   * Set if reads filter will be used.
   * @param ifReadsFilter the Boolean value of if reads filter will be used
   */
  public void setIfReadsFilter(Boolean ifReadsFilter) {
    this.ifReadsFilter.set(ifReadsFilter);
  }
  
  /**
   * Get if reads filter will be used.
   * @return the Boolean value of if reads filter will be used
   */
  public Boolean getIfReadsFilter() {
    return ifReadsFilter.get();
  }
  
  /**
   * Set if assembly will be used.
   * @param ifAssembly the Boolean value of if assembly will be used
   */
  public void setIfAssembly(Boolean ifAssembly) {
    this.ifAssembly.set(ifAssembly);
  }
  
  /**
   * Get if assembly will be used.
   * @return the Boolean value of if assembly will be used
   */
  public Boolean getIfAssembly() {
    return ifAssembly.get();
  }
  
  /**
   * Set if polishing will be used.
   * @param ifPolishing the Boolean value of if polishing will be used
   */
  public void setIfPolishing(Boolean ifPolishing) {
    this.ifPolishing.set(ifPolishing);
  }
  
  /**
   * Get if polishing will be used.
   * @return the Boolean value of if polishing will be used
   */
  public Boolean getIfPolishing() {
    return ifPolishing.get();
  }
  
  /**
   * Get the database of BUSCO.
   * @return the String of the BUSCO database
   */
  public String getBuscoData() {
    return buscoDatabase.get();
  }
  
  /**
   * Set the BUSCO database.
   * @param buscoDatabase the String of busco database
   */
  public void setBuscoData(String buscoDatabase) {
    this.buscoDatabase.set(buscoDatabase);
  }
  
  /**
   * Set if BUSCO will be used.
   * @param ifBusco the Boolean value of if BUSCO will be used
   */
  public void setIfBusco(Boolean ifBusco) {
    this.ifBusco.set(ifBusco);
  }
  
  /**
   * Get if BUSCO will be used.
   * @return the Boolean value of if BUSCO will be used
   */
  public Boolean getIfBusco() {
    return ifBusco.get();
  }
  
  /**
   * Set if Guppy fast mode will be used.
   * @param ifGuppyFast the Boolean value of if Guppy fast mode will be used
   */
  public void setIfGuppyFast(Boolean ifGuppyFast) {
    this.ifGuppyFast.set(ifGuppyFast);
  }
  
  /**
   * Get if Guppy fast mode will be used.
   * @return the Boolean value of if Guppy fast mode will be used
   */
  public Boolean getIfGuppyFast() {
    return ifGuppyFast.get();
  }
  
  /**
   * Get the Guppy configuration file.
   * @return the String of the Guppy configuration file
   */
  public String getGuppyCfgFile() {
    return guppyCfgFile.get();
  }
  
  /**
   * Set the Guppy configuration file.
   * @param guppyCfgFile The String of the Guppy configuration file.
   */
  public void setGuppyCfgFile(String guppyCfgFile) {
    this.guppyCfgFile.set(guppyCfgFile);
  }
}