package pe.edu.upeu.gth.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.security.access.method.P;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.config.UserDetailsServiceImpl;
import pe.edu.upeu.gth.dao.PriCartaNotarialDAO;
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenProcesarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.Justificacion;


public class ConfigTest {

	public static DataSource d = AppConfig.getDataSource();
	public static Gson gs = new Gson();
	public static RenAutorizarDAO au = new RenAutorizarDAO(d);
	public static RenunciaDAO re = new RenunciaDAO(d);
	public static RenProcesarDAO pr = new RenProcesarDAO(d);
	public static PriCartaNotarialDAO pc = new PriCartaNotarialDAO(d);

	public static void main(String[] args) {
//		conect();
//		checkSecurityDaoAuthentication();
//		Autorizar();
//		Procesar();
//		Procesar1();
		//Renuncia();
		justificacion();
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
	
//	  public static void update(){
//		   Justificacion j = new Justificacion();
//	       j.set
//	       p.setNombre("aceite2");
//	       p.setCorreo("hgfhsdf");
//	       if(per.edit(p)==1){
//	           System.out.println("si");
//	       }else{
//	           System.out.println("no");
//	       }
//	   }
	
	 public static void justificacion(){
	        Justificacion a = new Justificacion();
	        a.setObservaciones("saliooooooo");
	        a.setId_renaban("MRA-000025");
	        if(pc.JustificarAbandono(a)==1){
	            System.out.println("si");
	        }else{
	            System.out.println("no");
	        }
	    }


}
