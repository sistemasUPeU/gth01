package pe.edu.upeu.gth.test;

import javax.sql.DataSource;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.config.UserDetailsServiceImpl;
import pe.edu.upeu.gth.dto.CustomUser;

public class ConfigTest {

	public static DataSource d = AppConfig.getDataSource();

	public static void main(String[] args) {
		conect();
		checkSecurityDaoAuthentication();
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
