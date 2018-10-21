package pe.edu.upeu.gth.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;

public class SolicitudTest {

	SolicitudVacacionesDAO sd;
	Gson g;
	@Before
	public void Start() {
		g = new Gson();
		DataSource ds = AppConfig.getDataSource();
		sd = new SolicitudVacacionesDAO(ds);
		
	}
	
//	@Test
//	public void llenar_solicitud() {
//		HttpServletRequest request;
//		HttpServletResponse response;
////		ServletContext cntx = request.getServletContext();
//		assertEquals(5, 2+3);
////		assertNotEquals(null, sd.llenar_solicitud("TRF-00025", "05/08/2018", "20/08/2018", cntx, response));
//	
//	}
//	
//	@Test
//	public void getFechasVacaciones() {
//		HttpServletRequest request;
//		HttpServletResponse response;
////		ServletContext cntx = request.getServletContext();
//		assertEquals(5, 2+3);
////		System.out.println(sd.getFechasVacaciones("TRB-001420"));
//
//		
//		assertNotEquals(null, sd.getFechasVacaciones("TRB-001420"));
//
//	
//	}
//	
//	
//	@Test
//	public void validarTipoSolicitud() {
////		System.out.println(sd.validarTipoSolicitud("TRB-002953"));
//		assertEquals(1,sd.validarTipoSolicitud("TRB-002953"));
//		assertEquals(3,sd.validarTipoSolicitud("TRB-001420"));
//		
//		//TRB-002953 --- 1
//		//TRB-002553 --3
//		//TRB-001420 --- 3
//	}
//	@Test
//	public void insertarSolicitud() {
////		String[] inicio = new String[3];
////		inicio[0] = "20/05/2018";
////		inicio[1] = "22/06/2018";
////		inicio[2] = "01/08/2018";
////
////		String[] fin = new String[3]; 
////		fin[0] = "19/06/2018";
////		fin[1] = "20/07/2018";
////		fin[2] = "31/08/2018";
////		
////		String idt= "TRB-003610"; //Jair Stalyn
////		String tipo = "Programación";
////		String user = "mariana";
////				
////		//TRF-00019 
////		assertNotEquals(1, sd.insertarSolicitud(inicio, fin, idt, tipo, user));
//	}
//	
//	@Test
//	public void subirDocumento() {
//		String idvac = "VAC-000005";
//		String url = "archivo1.pdf";
//		
//		assertEquals(1,sd.subirDocumento("", "", url, idvac));
//	}
//	
//	
//	@Test
//	public void validarSolicitudSubida() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map = sd.validarSolicitudSubida("TRB-003610");
//		assertEquals(1, map.get("response"));
//	}
//	
//	
//	
//	@Test
//	public void sendEmail() {
//		
//	}
	
	@Test
	public void getfechas() {
		List<Map<String, Object>> dd = sd.getFechasVacaciones("TRB-003017");
		
		
		String[] fecha_array_inicio = new String[3];
		String[] fecha_array_fin = new String[3];
		int tamano = dd.size();
		if(tamano==1) {
			fecha_array_inicio[1] = "-";
			fecha_array_fin[1] = "-";
			fecha_array_inicio[2] = "-";
			fecha_array_fin[2] = "-";
		}else if(tamano == 2) {
			fecha_array_inicio[2] = "-";
			fecha_array_fin[2] = "-";
		}
		
		
		for (int i = 0; i < dd.size(); i++) {
			
	
			fecha_array_inicio[i] = dd.get(i).get("FECHA_INICIO").toString();
				
			fecha_array_fin[i] = dd.get(i).get("FECHA_FIN").toString();
				
		
			
			
			
		}
		
		System.out.println(dd);
		System.out.println(fecha_array_inicio[0] + " , " +fecha_array_fin[0] + " , " +fecha_array_inicio[1]+ " , " +fecha_array_fin[1]+ " , " +fecha_array_inicio[2]+ " , " + fecha_array_fin[2]);

	}


	@After
	public void end() {
		System.out.println("Prueba terminada");
	}
}