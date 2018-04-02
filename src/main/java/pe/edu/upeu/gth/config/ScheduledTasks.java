package pe.edu.upeu.gth.config;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class ScheduledTasks {

	  private static final Logger LOG = Logger.getLogger(ScheduledTasks.class);
	  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	  private static int cont=0;
	  @Scheduled(fixedDelay=100000)
	    public void ejecutarCadaDiezSegs() {
		  cont = cont +1;
	        System.out.println("Probando..." +cont);
//	        LOG.info("Fixed Rate Task :: Execution Time - {}",  Thread.currentThread().getName() );
	    }  
	  
	  
	
	  
}
