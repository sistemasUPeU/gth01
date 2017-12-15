package pe.edu.upeu.gth.test;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.config.UserDetailsServiceImpl;
import pe.edu.upeu.gth.dao.ControlFirmasDAO;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.CustomerInfo;
import pe.edu.upeu.gth.dto.ProductOrder;
import pe.edu.upeu.gth.interfaz.MailService;


public class ConfigTest {

	public static DataSource d = AppConfig.getDataSource();
	public static SolicitudVacacionesDAO SD = new SolicitudVacacionesDAO(AppConfig.getDataSource());

	public static void main(String[] args) {
		conect();
		checkSecurityDaoAuthentication();
		//listar();
		//proc();
//		TestJhorman();
		//TestHarold();
//		SD.sendEmail(getDummyOrder());

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		MailService mailService = (MailService) context.getBean("mailService");
//		MailService ma = (MailService)	 ApplicationContextProvider.getApplicationContext.getBean("mailService");
		
		
//		mailService.sendEmail(getDummyOrder());

		((AbstractApplicationContext) context).close();

		
		
	}

	private static void TestJhorman() {
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		Gson GSON = new Gson();
		System.out.println(GSON.toJson(DAO.UPDATEFECHA("DEV-00003", 1, 0)));
	}
	
	private static void TestHarold() {
		
		Gson GSON = new Gson();
		
		//	String[] inicio = {"20171105","20171225"};
		String[] inicio = {"10/01/17"};
		String[] fin = {"10/02/17"};
		
		System.out.println(SD.insertarSolicitud(inicio, fin, "TRB-002735", "PROGRAMACION", "karina").trim());
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
	
	public static ProductOrder getDummyOrder(){
		ProductOrder order = new ProductOrder();
		order.setOrderId("1111");
		order.setProductName("Thinkpad T510");
		order.setStatus("confirmed");
		
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setName("Websystique Admin");
		customerInfo.setAddress("WallStreet");
		customerInfo.setEmail("104granados@gmail.com");
		order.setCustomerInfo(customerInfo);
		System.out.println(order);
		return order;
	}

}
