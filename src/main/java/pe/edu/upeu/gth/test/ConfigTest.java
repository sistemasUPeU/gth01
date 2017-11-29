package pe.edu.upeu.gth.test;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.config.UserDetailsServiceImpl;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;
import pe.edu.upeu.gth.dto.CustomUser;

public class ConfigTest {

	public static DataSource d = AppConfig.getDataSource();

	public static void main(String[] args) {
		conect();
		checkSecurityDaoAuthentication();
		//listar();
		proc();
	}

	public static void conect() {
		DataSource d = AppConfig.getDataSource();

		if (d != null) {
			System.out.println("Conectado");
		} else {
			System.out.println("No se pudo conectar");
		}
	}
	
	
	public static void checkSecurityDaoAuthentication() {
		UserDetailsServiceImpl userdetailsService=new UserDetailsServiceImpl();
		CustomUser user=userdetailsService.loadUserByUsername("ivan");
		System.out.println("nombre: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("checked!");
		
	}
	
	public static void listar() {
		
		try {
			SolicitudVacacionesDAO vd = new SolicitudVacacionesDAO(AppConfig.getDataSource());
			List<Map<String, Object>> sd = vd.llenar_solicitud("TRF-00002");
			for(Object x:sd) {
				System.out.println(x);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error - test:"+ e); 
		}
	
	
	}
	
	public static void proc() {
		
		try {
			SolicitudVacacionesDAO vd = new SolicitudVacacionesDAO(AppConfig.getDataSource());
			 int sd = vd.validarTipoSolicitud("TRB-003890");
					 System.out.println("hi:_"+ sd);
//			for(Object x:sd) {
//				System.out.println(x);
//			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error - test:"+ e); 
		}
	
	
	}

}
