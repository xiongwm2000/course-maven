package com.xd.cloud.common.util;

import java.io.File;  
import java.util.HashMap;  
import java.util.Map;  
  
import org.apache.log4j.Logger;  
  
/** 
 * 功能描述 
 * 加密常用类 
 */  
public class FlashPaperUtil {  
   
	private static final String UNINSTALL_BAT = "uninstall.bat";  
    private static final String INSTALL_BAT = "install.bat";  
    private static Logger log = Logger.getLogger(FlashPaperUtil.class);  
    private static final String CMD_C_START = "cmd /c start ";  
    private static final String FLASH_PRINTER_EXE = "FlashPrinter.exe";  
    private static final String FLASH_PAPER2_2 = "FlashPaper2.2";  
    private static final String SPACE = " ";  
    private static String flashPaperDir;  
    private static String flashPrinterPath;  
    private static String installPath;  
    private static String unInstallPath;  
    private static Runtime runtime = Runtime.getRuntime();  
      
    private static Map<String, String> fileMap = new HashMap<String, String>();  
    private static FlashPaperUtil instance;  
      
      
    private FlashPaperUtil(){  
//        flashPaperDir =   
//            FlashPaperUtil.class.getClassLoader().getResource(  
//                    FLASH_PAPER2_2).getFile();  
//        for (File file : new File(flashPaperDir).listFiles()) {  
//            fileMap.put(file.getName(), file.getAbsolutePath());  
//        }  
//        flashPrinterPath = fileMap.get(FLASH_PRINTER_EXE);  
//        installPath = fileMap.get(INSTALL_BAT);  
//        unInstallPath = fileMap.get(UNINSTALL_BAT);  
    }  
      
    public static FlashPaperUtil getInstance(){  
        if(null == instance){  
            synchronized (FlashPaperUtil.class) {  
                instance = new FlashPaperUtil();  
            }  
        }  
        return instance;  
    }  
  
    public void docToSwf(String docPath, String outPath)  
            throws Exception {  
        String command = "D:\\搜狗高速下载\\Print2Flash3\\p2fServer.exe" + SPACE + docPath  
                + SPACE + outPath;  
        Process process = runtime.exec(command);  
        process.waitFor();  
        process.destroy();  
        File outFile = new File(outPath);  
        if(outFile.exists()){  
            log.info("docToSwf success.........");  
        }  
    }  
  
    public void install() throws Exception {  
        String cmd = CMD_C_START + installPath;  
        Process process = runtime.exec(cmd,null,new File(flashPaperDir));  
        process.waitFor();  
        process.destroy();  
        log.info("install success..........");  
    }  
  
    public void uninstall() throws Exception {  
        String cmd = CMD_C_START + unInstallPath;  
        Process process = runtime.exec(cmd,null,new File(flashPaperDir));  
        process.waitFor();  
        process.destroy();  
        log.info("uninstall success..........");  
    }  
  
    public static void main(String[] args) throws Exception {  
    	
    	
    	
    	
        FlashPaperUtil.getInstance().docToSwf("D:\\a.doc","D:\\a.swf");  
    }  
	
}  