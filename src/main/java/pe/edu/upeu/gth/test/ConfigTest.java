package pe.edu.upeu.gth.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.config.UserDetailsServiceImpl;
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenProcesarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.CustomUser;

public class ConfigTest {

	public static DataSource d = AppConfig.getDataSource();
	public static Gson gs = new Gson();
	public static RenAutorizarDAO au = new RenAutorizarDAO(d);
	public static RenunciaDAO re = new RenunciaDAO(d);
	public static RenProcesarDAO pr = new RenProcesarDAO(d);

	public static void main(String[] args) {
//		conect();
//		checkSecurityDaoAuthentication();
		//Autorizar();
//		Procesar();
//		Procesar1();
		//Renuncia();
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
	public static void Autorizar() {
//		DataSource d = AppConfig.getDataSource();
		System.out.println(gs.toJson(au.Autorizar()));
	}
	
	public static void Renuncia() {
		DataSource d = AppConfig.getDataSource();
		System.out.println(gs.toJson(re.listar()));
	}
	
	public static void Procesar() {
		System.out.println(gs.toJson(pr.Procesar()));
	}
	


}
