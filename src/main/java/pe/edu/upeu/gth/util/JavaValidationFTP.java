package pe.edu.upeu.gth.util;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class JavaValidationFTP extends JavaFTP {

private List<String> typesAccept=new ArrayList<>();	
	
	public int ftpUpFileValidation(MultipartFile file,String proceso,String file_name,String file_extension,String carpeta,String[] typesAccept) {
		int countBoolean=0;
		if (file != null && !file.isEmpty()) {        	
	        try{
	    		if(!validateTypeFileInList(typesAccept,file.getContentType())) {
	    			System.out.println("file no cumple");
	    			return countBoolean;			            	
	    		}else{
	    			System.out.println("file validado");
	    			file_name=file_name +"."+ file_extension;
	        	    if (upload( file_name, proceso,carpeta,file.getBytes())) {
	        	    	System.out.println("file subido->"+file_name);		            	    	
	        	    	countBoolean=countBoolean+1;
	        	    }else{ return countBoolean; }
	    		}		            		
	        }catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		}
        return countBoolean;
	}
		
    public void downloadFileSingle_to_client(String filename,String directory,HttpServletRequest request,HttpServletResponse response) throws Exception {									
		String downloadFile="";
		downloadFile = downloadFileFtp_in_server(filename,directory);
		System.out.println("downloadFileSingle_to_client-> "+downloadFile);
		if(!downloadFile.isEmpty()) {
	        String mimeType = request.getServletContext().getMimeType(downloadFile);
	        if (mimeType == null) {        
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);	         
	        String headerValue = String.format("attachment; filename=\"%s\"", filename);	        
	        response.setContentType(mimeType);
			response.setHeader("Content-Disposition", headerValue);
			
	        try(FileInputStream inStream = new FileInputStream(downloadFile);
	          		 OutputStream outStream = response.getOutputStream()){
	   			
	   	       	byte[] buffer = new byte[4096];
	   		    int bytesRead = -1;
	   		         
	   	        while ((bytesRead = inStream.read(buffer)) != -1) {
	   	            outStream.write(buffer, 0, bytesRead);
	   	        }
	           }
		}else {
			//properties_to_response.put("status", false);
		}
//	    return properties_to_response;
    }
   
	

   private boolean validateTypeFileInList(String[] extAccept,String extReal) {
   	if(typesAccept.isEmpty()) {
   		typesAccept=Arrays.asList(extAccept);
   	}
  	 return typesAccept.contains(extReal);    	    
  }

	
}
