package pe.edu.upeu.gth.util;


import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;



public class JavaFTP {

	
	  public static final String barra_inclinada_ftp = "/";
		
		private static final String SERVER = "192.168.21.30";
	    private static final String USER = "kevin";
	    private static final int PORT = 21;    
	    private static String ruta="";

	    public boolean upload(String remoteFileName,String proceso,String carpeta, byte[] file_in_bytes) {
	    	System.out.println("nombre del archivo  original: "+remoteFileName);
	    	//Empieza el metodo para quitar tildes
	    	String regularexpresions="";
	    	String reemplazo="AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
	    	String ouput=remoteFileName;
	    	for (int i = 0; i < regularexpresions.length(); i++) {
	    		ouput=ouput.replace(regularexpresions.charAt(i), reemplazo.charAt(i));
			}
	    	System.out.println("nombre del archivo  modificado sin tildes: "+ouput);

	    	//Termina el metodo para quitar tildes
	        FTPClient ftpClient = new FTPClient();
	        boolean done = false;        
	        try {
	        	ftpClient.setAutodetectUTF8(true);
	        	ftpClient.setControlEncoding("UTF-8");
	            ftpClient.connect(SERVER, PORT);
	            ftpClient.login(USER, getPassword());
	            ruta="GTH"+"/"+proceso+"/"+carpeta;
	            
	            if(!ftpClient.changeWorkingDirectory(ruta)) {
	            	System.out.println("entro al exist directory "+ruta);
	            	ftpClient.makeDirectory(ruta);
	            	ftpClient.changeWorkingDirectory(ruta);
	            }
	            
	            //ftpClient.enterLocalActiveMode();           
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	            ftpClient.enterLocalPassiveMode();            
	           
	            try (InputStream input = new ByteArrayInputStream(file_in_bytes)) {            	
	                done = 	ftpClient.storeFile(remoteFileName, input);
	                System.out.println("acaba de guardar " + done);
	                //System.out.println(ftpClient.rename(remoteFileName, "CARTA01.docx"));
	                input.close();
	            }
	            System.out.println("Cerrando conexion upload");
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
	        return done;
	    }
	    
	    public boolean delete(String remoteFileName,String proceso,String carpeta) {
	        FTPClient ftpClient = new FTPClient();
	        boolean done = false;
	        try {
	            ftpClient.connect(SERVER, PORT);
	            ftpClient.login(USER, getPassword());
	            ruta="GTH"+"/"+proceso+"/"+carpeta;
	            ftpClient.setControlEncoding("UTF-8");
	            ftpClient.changeWorkingDirectory(ruta);
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	           
	            done=ftpClient.deleteFile(remoteFileName);
	        } catch (IOException ex) {
	            System.out.println(ex.getMessage());
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
	        return done;
	    }

	    public String downloadFileFtp_in_server(String remoteFileName, String downloadDirectory) {
	    	FTPClient ftpClient = new FTPClient();    	
	    	String barra_local_windows=FileSystems.getDefault().getSeparator()+FileSystems.getDefault().getSeparator();
	    	
	    	String directory_home = System.getProperty("user.home").replace(FileSystems.getDefault().getSeparator(), barra_local_windows);
	    	String carpeta_to_dowlaod = directory_home+barra_local_windows+"Documents"+barra_local_windows+downloadDirectory;
	    	String path_file = "";
	    	
	        try {
	            ftpClient.connect(SERVER, PORT);
	            ftpClient.login(USER, getPassword());
	            ruta = "PPP"+barra_inclinada_ftp+downloadDirectory;
	            if(!ftpClient.changeWorkingDirectory(ruta)) {
	            	System.out.println("entro al exist directory "+ruta);
	            	ftpClient.makeDirectory(ruta);
	            	ftpClient.changeWorkingDirectory(ruta);
	            }
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	            
	            System.out.println("JavaFTP archivo con nombre -> "+remoteFileName);
	            System.out.println("carpeta donde descargara -> "+carpeta_to_dowlaod);   
	            
	            System.out.println("existe la ruta -> "+(new File(carpeta_to_dowlaod).mkdirs()));            
	            
	            String path_new_file = carpeta_to_dowlaod+barra_local_windows+remoteFileName;
	            //path_new_file = path_new_file.replace(barra_local_windows, "/");
	            System.out.println(path_new_file);
	            File download = new File(path_new_file);
	            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(download))) {
	            	System.out.println(ftpClient.printWorkingDirectory());
	                if(ftpClient.retrieveFile(remoteFileName, out)) {
	                	path_file = path_new_file;
	                }
	            }
	        } catch (IOException ex) {
	            System.out.println(ex.getMessage());
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	            	System.out.println(ex.getMessage());
	            }
	        }
	        return path_file;
	    }
	    
	    public String downloadFileFtp_in_tomcat(String remoteFileName, String downloadDirectory,String folderInTomcat) {
	    	FTPClient ftpClient = new FTPClient();    	
	    	//String barra_local_windows=FileSystems.getDefault().getSeparator()+FileSystems.getDefault().getSeparator();
	    	
	    	//String directory_home = System.getProperty("user.home").replace(FileSystems.getDefault().getSeparator(), barra_local_windows);
	    	//String carpeta_to_dowlaod = directory_home+barra_local_windows+"Documents"+barra_local_windows+downloadDirectory;
	    	String path_file = "";
	    	
	        try {
	            ftpClient.connect(SERVER, PORT);
	            ftpClient.login(USER, getPassword());
	            ruta = downloadDirectory;
	            System.out.println("Esta es la ruta que le mand: "+ruta);
	            if(!ftpClient.changeWorkingDirectory(ruta)) {
	            	System.out.println("entro al exist directory "+ruta);
	            	ftpClient.makeDirectory(ruta);
	            	ftpClient.changeWorkingDirectory(ruta);
	            }
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	            
	            System.out.println("JavaFTP archivo con nombre -> "+remoteFileName);
	            System.out.println("carpeta donde descargara -> "+folderInTomcat);   
	            
	            System.out.println("existe la ruta -> "+(new File(folderInTomcat).mkdirs()));            
	            
	            String path_new_file = folderInTomcat+"\\"+remoteFileName;
	            //path_new_file = path_new_file.replace(barra_local_windows, "/");
	            System.out.println(" Este es el nuevo path, a donde debera descargar"+path_new_file);
	            File download = new File(path_new_file);
	            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(download))) {
	            	System.out.println(ftpClient.printWorkingDirectory());
	                if(ftpClient.retrieveFile(remoteFileName, out)) {
	                	path_file = path_new_file;
	                }
	            }
	        } catch (IOException ex) {
	            System.out.println("Error en cargar archivo desde tomcat: "+ex.getMessage());
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	            	System.out.println(ex.getMessage());
	            }
	        }
	        return path_file;
	    }
	    public boolean download(String remoteFileName, String downloadDirectory) {
	        FTPClient ftpClient = new FTPClient();
	        boolean success=false;
	        try {
	            ftpClient.connect(SERVER, PORT);
	            ftpClient.login(USER, getPassword());
	            ftpClient.changeWorkingDirectory("PPP");
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	            System.out.println("JavaFTP archivo con nombre ->"+remoteFileName);
	            File download = new File(downloadDirectory+FileSystems.getDefault().getSeparator()+remoteFileName);
	            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(download))) {
	                success = ftpClient.retrieveFile(remoteFileName, out);          
	            }
	        } catch (IOException ex) {
	            System.out.println(ex.getMessage());
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	            	System.out.println(ex.getMessage());
	            }
	        }
	        return success;
	    }
	    
	    private String getPassword(){
	        return "12345";
	    }
	    
	    public String getruta()
	    {
	    	String path=ruta;
	    	return path+"/";
	    }

	
	
}
