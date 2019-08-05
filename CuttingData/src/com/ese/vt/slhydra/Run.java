package com.ese.vt.slhydra;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Run {

	public static void main(String[] args) throws IOException {
		
		Properties prop = new Properties();
    	InputStream input = null;
    	
    	try {
        
    		String filename = "resource/config.properties";
    		input = Run.class.getClassLoader().getResourceAsStream(filename);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + filename);
    		    return;
    		}

    		//load a properties file from class path, inside static method
    		prop.load(input);
 
                //get the property value
    	        String xlsFile = prop.getProperty("xlsFile");
    	        String datFile = prop.getProperty("datFile");
 
    	        XLSReader csr = new XLSReader(xlsFile,datFile);
    			csr.readData();
    			
    	} catch (IOException ex) {
    		ex.printStackTrace();
        } finally{
        	if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
		
		
        
	}

}
