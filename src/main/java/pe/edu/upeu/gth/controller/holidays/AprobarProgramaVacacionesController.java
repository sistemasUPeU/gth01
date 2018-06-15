package pe.edu.upeu.gth.controller.holidays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.AprobarProgramaVacaciones;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.CustomerInfo;
import pe.edu.upeu.gth.dto.ProductOrder;
import pe.edu.upeu.gth.interfaz.MailService;

@Controller
@RequestMapping("/vacaciones/programa_vacaciones")
public class AprobarProgramaVacacionesController {
	Gson g = new Gson();
	DataSource ds = AppConfig.getDataSource();
	AprobarProgramaVacaciones t = new AprobarProgramaVacaciones(ds);
	@Autowired
	public MailService ms;

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getSinAprobar(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		return g.toJson(t.listarSinAprobar(depa));
	}

	@RequestMapping(path = "/getAprobados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAprobados(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		Gson g = new Gson();
		return g.toJson(t.listarAprobados(depa));
	}

	@RequestMapping(path = "/getRechazados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getRechazados(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		return g.toJson(t.listarRechazados(depa));
	}

	@RequestMapping(path = "/guardarAprobar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarAprobar(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String[] idarray = id_det.split(",");
		return g.toJson(t.apobarVac(usuario, idarray));
	}

	@RequestMapping(path = "/guardarObservar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarObservar(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String text = request.getParameter("text");
		String emisor = request.getParameter("emisor");
		return g.toJson(t.observarVac(usuario, id_det, text, emisor));
	}

	@RequestMapping(path = "/enviarObservacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String enviarObservacion(HttpServletRequest request, Authentication authentication) {
		List<Map<String, Object>> lista_e = new ArrayList<>();
		List<Map<String, Object>> lista_s = new ArrayList<>();
		int c = 0;
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		String id_det = request.getParameter("id_det");
		String receptor = request.getParameter("receptor");
		lista_e = t.GetEmailEmployee(depa, id_det, receptor);
		lista_s = t.GetEmailSecre(depa);
		String fecha_ini = lista_e.get(0).get("FECHA_INICIO").toString();
		String fecha_fin = lista_e.get(0).get("FECHA_FIN").toString();
		String[] arrayEmail = new String[lista_s.size() + 1];
		System.out.println(g.toJson(lista_s));
		for (int i = 0; i < lista_s.size(); i++) {
			System.out.println(lista_s.get(i).get("DI_CORREO_PERSONAL"));
			arrayEmail[i] = lista_s.get(i).get("DI_CORREO_PERSONAL").toString();
			c = i;
		}
		arrayEmail[c + 1] = lista_e.get(0).get("DI_CORREO_PERSONAL").toString();
		String text = lista_e.get(0).get("TEXTO").toString();
		String format = "Su Jefe de departamento ha observado su programa de vacaciones:\r\n" + text + "\r\n"
				+ "El programa de vacaciones observado es del " + fecha_ini + " al " + fecha_fin;
		String[] tempArray = new String[1];
		tempArray[0] = "104granados@gmail.com";
		ms.sendEmail(getDummyOrder(), tempArray, format);
		System.out.println(g.toJson(arrayEmail));
		return g.toJson(arrayEmail);
	}

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
}