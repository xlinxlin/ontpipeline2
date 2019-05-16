package mbio.ncct.ont.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import mbio.ncct.ont.model.Pipeline;

/**
 * This is the PipelineUtil class for utilities used in Pipeline.
 * 
 * @author Yan Zhou
 * created on 2019/05/16
 */
public class PipelineUtil {
  
  /** Initialize log4j2. */
  static Logger logger = LogManager.getLogger(PipelineUtil.class);
  
  /**
   * Get all flowcell IDs.
   * @return a String Array with all flowcell IDs 
   */
  public ArrayList<String> getFlowcellIds() {
    String s = null;
    ArrayList<String> arFlowcellIds = new ArrayList<String>();
    Process p = null;
    try {
      p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_basecaller --print_workflows | awk 'NR>2 {print $1}' | sort | uniq" });
    } catch (Exception e) {
      logger.error("Can not get flowcell IDs. " + e);
    }
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    try {
      while ((s = stdInput.readLine()) != null ) {
        if (!s.isEmpty()) {
          arFlowcellIds.add(s);
        }
      }
    } catch (Exception e) {
      logger.error("Can not read flowcell IDs. " + e);
    }
    return arFlowcellIds;
  }
  
  /**
   * Get all kit numbers.
   * @return a String Array with all kit numbers 
   */
  public ArrayList<String> getKitNumbers() {
    String s = null;
    ArrayList<String> arKitNumbers = new ArrayList<String>();
    Process p = null;
    try {
      p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_basecaller --print_workflows | awk 'NR>2 {print $2}' | sort | uniq" });
    } catch (Exception e) {
      logger.error("Can not get kit numbers. " + e);
    }
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    try {
      while ((s = stdInput.readLine()) != null ) {
        if (!s.isEmpty()) {
          arKitNumbers.add(s);
        }
      }
    } catch (Exception e) {
      logger.error("Can not read kit numbers. " + e);
    }
    return arKitNumbers;
  }
  
  /**
   * Find all the combinations of flowcell ID and kit number.
   * @return Return a map with all the combinations of flowcell ID and kit number.
   */
  public Map<String, String> findCombinationFlowcellKit() {
    String s = null;
    Map<String, String> m = new HashMap<String, String>();
    Process p = null;
    try {
      p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "/opt/ont-guppy-cpu_3.0.3/bin/guppy_basecaller --print_workflows | awk 'NR>2 {print $1,$2,$3,$4}' " });
    } catch (Exception e) {
      logger.error("Can not run command: guppy_basecallerr --print_workflows . " + e);
    }
    //BufferedReader stdError = null;
    try {
      BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
      //stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
      while ((s = stdInput.readLine()) != null ) {
        if (s.length() > 3) {
          String[] arr = s.replaceAll("included ", "").split(" ");
          m.put(arr[0].concat(arr[1]), arr[2]);
        }
      }
    } catch (Exception e) {
      logger.error("Can not read result from guppy_basecallerr --print_workflows . " + e);
    }
    return m;
  }
  
  /**
   * Create a .pbs file filled with the input parameters.
   * @param p A Pipeline object.
   * @param timeStamp The current date and time yyyyMMdd_HHmmss.
   */
  public void createPbsFile(Pipeline p, String timeStamp) {
    if (!p.getSelectedBarcode().isEmpty()) {
      String[] strArr = p.getSelectedBarcode().split(",");
      String formattedSelectedBarcode = "";
      for(int i=0;i<strArr.length;i++) {
        String formatted = String.format("%02d", Integer.valueOf(strArr[i])) + ",";
        formattedSelectedBarcode = formattedSelectedBarcode + formatted;
      }
      p.setSelectedBarcode("barcode{" + formattedSelectedBarcode.substring(0, formattedSelectedBarcode.length()-1) + "}/");
    }
    
    if ( !p.getFlowcellId().equals("FLO-MIN107") && p.getGuppyMode().equals("fast")) {
      Map<String, String> combinationFlowcellKit = null;
      String cfg = null;
      try {
        combinationFlowcellKit = findCombinationFlowcellKit();
        cfg = combinationFlowcellKit.get(p.getFlowcellId().concat(p.getKitNumber())).toString();
      } catch (Exception e) {
        logger.error("Can not get the combinations of flowcell ID and kit number. " + e);
      }
      int cfg_bps = cfg.indexOf("bps");
      String cfgFile = ( cfg.substring(0, cfg_bps + 3) + "_fast" ) + ( p.getDevice().equals("PromethION") ? "_prom" : "" ) + ".cfg";
      p.setIfGuppyFast(true);
      p.setGuppyCfgFile("/opt/ont-guppy-cpu_3.0.3/data/" + cfgFile);
    }
    
    Path path = Paths.get("/opt/ontpipeline/pbs/pipelineWithLoop.pbs");
    Path newPath = Paths.get(p.getWorkspace() + "/pipelineWithLoop_" + timeStamp + ".pbs");
    Charset charset = StandardCharsets.UTF_8;

    String content = null;
    try {
      content = new String(Files.readAllBytes(path), charset);
    } catch (Exception e) {
      logger.error("Can not read the .pbs template. " + e);
    }
    
    content = content.replaceAll("\\$WORKSPACE_PATH", p.getWorkspace())
        .replaceAll("\\$IF_BASECALLING", p.getIfBasecalling().toString())
        .replaceAll("\\$FLOWCELL_ID", p.getFlowcellId())
        .replaceAll("\\$KIT_NUMBER", p.getKitNumber())
        .replaceAll("\\$THREADS", p.getThreads())
        .replaceAll("\\$BARCODEKIT", p.getBarcodeKit())
        .replaceAll("\\$IF_ADAPTERTRIMMING", p.getIfAdapterTrimming().toString())
        .replaceAll("\\$BARCODENUMBERS", p.getSelectedBarcode())
        .replaceAll("\\$IF_READSFILTER", p.getIfReadsFilter().toString())
        .replaceAll("\\$SCORE", p.getReadScore())
        .replaceAll("\\$LENGTH", p.getReadLength())
        .replaceAll("\\$HEADCROP", p.getHeadCrop())
        .replaceAll("\\$IF_ASSEMBLY", p.getIfAssembly().toString())
        .replaceAll("\\$IF_VCF", p.getIfVcf().toString())
        .replaceAll("\\$MODE", p.getMode())
        .replaceAll("\\$METHOD", p.getMethod())
        .replaceAll("\\$IF_POLISHING", p.getIfPolishing().toString())
        .replaceAll("\\$IF_BUSCO", p.getIfBusco().toString())
        .replaceAll("\\$LINEAGE", p.getBuscoData())
        .replaceAll("\\$PTIMES", p.getPtimes())
        .replaceAll("\\$IF_GUPPYFAST", p.getIfGuppyFast().toString())
        .replaceAll("\\$CFG_FILE", p.getGuppyCfgFile());
   try {
      Files.write(newPath, content.getBytes(charset));
    } catch (Exception e) {
      logger.error("Can not write .pbs file. " + e);
    }
  }
  
  /**
   * Create an user log file with all the input parameters.
   * @param p A Pipeline object.
   * @param timeStamp The current date and time yyyyMMdd_HHmmss.
   */
  public void createUserLog(Pipeline p, String timeStamp) {
    String path = p.getWorkspace() + "/userlog_" + timeStamp + ".log";
    File f = new File(path);
    f.getParentFile().mkdirs(); 
    try {
     f.createNewFile();
    } catch (Exception e) {
      logger.error("Can not create user log file. " + e);
    }
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
      writer.append("====General Settings====\n");
      writer.append("Workspace: " + p.getWorkspace() + "\n");
      writer.append("Threads: " + p.getThreads() + "\n");
      writer.append("Barcodes: " + ( p.getSelectedBarcode().isEmpty() ? "all" :p.getSelectedBarcode() ) + "\n\n");
      if (p.getIfBasecalling()) {
        writer.append("====Basecalling Settings====\n");
        writer.append("Flowcell ID: " + p.getFlowcellId() + " \n");
        writer.append("Kit number: " + p.getKitNumber() + " \n");
        writer.append("Guppy mode: " + p.getGuppyMode() + " \n");
        writer.append("Device: " + p.getDevice() + " \n");
        writer.append("Barcode kits: " + p.getBarcodeKit() + " \n\n"); 
      } else {
        writer.append("====No Basecalling====\n\n");
      }
      if (p.getIfReadsFilter()) {
        writer.append("====Reads Filter Settings====\n");
        writer.append("Read score: " + p.getReadScore() + " \n");
        writer.append("Read length: " + p.getReadLength() + " \n");
        writer.append("Head crop: " + p.getHeadCrop() + " \n");
        writer.append("If adapter trimming: " + p.getIfAdapterTrimming() + " \n");
        writer.append("If split reads: " + ( p.getIfAdapterTrimming() ? p.getIfNoSplit() : "") + " \n\n");
      } else {
        writer.append("====No Reads Filter====\n\n");
      }
      if (p.getIfAssembly()) {
        writer.append("====Assembly Settings====\n");
        writer.append("Assembly mode: " + p.getMode() + " \n");
        writer.append("Assembly method: " + p.getMethod() + " \n");
        writer.append("VCF: " + p.getIfVcf() + " \n\n");
      } else {
        writer.append("====No Assembly====\n\n");
      }
      if (p.getIfPolishing()) {
        writer.append("====Polishing Settings====\n");
        writer.append("Polishing times: " + p.getPtimes() + " \n");
        writer.append("BUSCO: " + p.getIfBusco() + " \n");
        writer.append("BUSCO database: " + ( p.getIfBusco() ? p.getBuscoData() : "" ) + " \n");
      } else {
        writer.append("====No Polishing====\n");
      }
      writer.close();
    } catch (Exception e) {
      logger.error("Can not create user log file. " +  e);
    }
  }
}
