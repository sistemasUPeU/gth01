package pe.edu.upeu.gth.controller.holidays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.TrabajadorFiltradoDAO;
import pe.edu.upeu.gth.dto.CustomerInfo;
import pe.edu.upeu.gth.dto.ProductOrder;
import pe.edu.upeu.gth.interfaz.MailService;

@Controller
@RequestMapping("/vacaciones/filtrar")
public class TrabajadorFiltradoController {
	
	Gson GSON = new Gson();
	
	@Autowired
	public MailService ms;
	
	@Autowired
	ServletContext context;
	
	public static ProductOrder getDummyOrder() {
		ProductOrder order = new ProductOrder();
		order.setOrderId("1111");
		order.setProductName("Thinkpad T510");
		order.setStatus("confirmed");

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setName("Websystique Admin");
		customerInfo.setAddress("WallStreet");
		customerInfo.setEmail("104granados@gmail.com");
		order.setCustomerInfo(customerInfo);
		
		return order;
	}
	
	@RequestMapping(path = "/readallTrabajadorFiltrado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadorFiltrado() {
		TrabajadorFiltradoDAO DAO = new TrabajadorFiltradoDAO(AppConfig.getDataSource());
		System.out.println("read all trabajador filtrado");
		return GSON.toJson(DAO.READALL());
	}
	
	@RequestMapping(path = "/confirmarListaFiltrada", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String confirmarListaFiltrada() {
		TrabajadorFiltradoDAO DAO = new TrabajadorFiltradoDAO(AppConfig.getDataSource());

		// AbstractApplicationContext context = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		//
		// MailService mailService = (MailService) context.getBean("mailService");
		// mailService.sendEmail(getDummyOrder());
		//
		// ((AbstractApplicationContext) context).close();

		// emailService.sendSimpleMessageUsingTemplate(mailObject.getTo(),
		// mailObject.getSubject(),
		// template,
		// mailObject.getText());

		List<Map<String, Object>> lista = new ArrayList<>();
		lista = DAO.GetEmail();
		String[] arrayEmail = new String[lista.size()];
		System.out.println(GSON.toJson(lista));
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).get("DI_CORREO_PERSONAL"));
			arrayEmail[i] = lista.get(i).get("DI_CORREO_PERSONAL").toString();
		}
		// ms.sendEmail(getDummyOrder(), arrayEmail);
		return GSON.toJson(DAO.CONFIRMAR());
	}
}
