package pe.edu.upeu.gth.test;

import javax.sql.DataSource;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.config.UserDetailsServiceImpl;
import pe.edu.upeu.gth.dao.ControlFirmasDAO;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;
import pe.edu.upeu.gth.dto.CustomUser;

public class ConfigTest {

	public static DataSource d = AppConfig.getDataSource();

	public static void main(String[] args) {
		conect();
		checkSecurityDaoAuthentication();
		//listar();
		//proc();
		TestJhorman();
	}

	private static void TestJhorman() {
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		Gson GSON = new Gson();
		String id = "VAC-000002";
		System.out.println(GSON.toJson(DAO.READFECHA(id)));
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

}
