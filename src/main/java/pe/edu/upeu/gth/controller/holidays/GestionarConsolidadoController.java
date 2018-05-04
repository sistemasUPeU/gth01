package pe.edu.upeu.gth.controller.holidays;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JRException;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.GestionarConsolidadoDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.CustomerInfo;
import pe.edu.upeu.gth.dto.ProductOrder;
import pe.edu.upeu.gth.interfaz.MailService;

@Controller
@RequestMapping("/vacaciones/consolidado")
public class GestionarConsolidadoController {
	DataSource ds = AppConfig.getDataSource();
	GestionarConsolidadoDAO gc = new GestionarConsolidadoDAO(ds);
	// SolicitudVacacionesDAO vd = new SolicitudVacacionesDAO(ds);
	Gson g = new Gson();
	@Autowired
	public MailService ms;
	@Autowired
	ServletContext context;

	@RequestMapping(path = "/getSinAprobar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getSinAprobar() {
		return g.toJson(gc.listarConsolidadoSinAprobar());
	}

	@RequestMapping(path = "/getAprobado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAprobado() {
		return g.toJson(gc.listarConsolidadoAprobado());
	}

	@RequestMapping(path = "/guardarAprobarConsolidado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarAprovar(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String[] idarray = id_det.split(",");
		return g.toJson(gc.apobarVacCon(usuario, idarray));
	}

	@RequestMapping(path = "/enviarCorreoAprobarConsolidado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String enviarCorreoAprobado(HttpServletRequest request, Authentication authentication) {
		List<Map<String, Object>> lista = new ArrayList<>();
		lista = gc.getCorreoConsolidado();
		String[] arrayEmail = new String[lista.size()];
		System.out.println(g.toJson(lista));
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).get("DI_CORREO_PERSONAL"));
			arrayEmail[i] = lista.get(i).get("DI_CORREO_PERSONAL").toString();
		}
		String text = "Esta es una prueba de parte del grupo de desarrolladores GRRAC Enterprise,"
				+ " trabajando al servicio de la UPeU. Por favor omitir este email.";
		String[] tempArray = new String[1];
		tempArray[0] = "104granados@gmail.com";

		ms.sendEmail(getDummyOrder(), tempArray, text);

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

	@RequestMapping(path = "/readFechas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getFirma(HttpServletRequest request) {
		String id = request.getParameter("id");
		return g.toJson(gc.readFechas(id));
	}

	@RequestMapping(path = "/updateFirma", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String actualizarFirma(HttpServletRequest request) {
		String id = request.getParameter("id");
		int inicio = Integer.parseInt(request.getParameter("inicio"));
		int fin = Integer.parseInt(request.getParameter("fin"));
		return g.toJson(gc.updateFechas(id, inicio, fin));
	}

	@RequestMapping(value = "/mostrardoc")
	public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext cntx = request.getServletContext();
		// Get the absolute path of the image
		// String filename = cntx.getRealPath("/WEB-INF/dddd.png");
		// PrintWriter out = response.getWriter();
		String url = "";
		String traba = request.getParameter("traba");
		String id_det = request.getParameter("id_det");
		int op = Integer.parseInt(request.getParameter("op"));

		List<Map<String, Object>> result1 = gc.readFile(traba, id_det);
		System.out.println(g.toJson(result1));
		// System.out.println();
		switch (op) {
		case 1:
			url = (String) result1.get(0).get("URL_SOLICITUD");

			break;
		case 2:
			url = (String) result1.get(0).get("URL_PAPELETA");
			break;
		}
		String filename = cntx.getRealPath("/WEB-INF/" + url.trim());
		// String filename = url;
		// String filenam1e
		// ="E:\\TRABAJO\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gth\\WEB-INF\\david\\"+nom;

		System.out.println(url + "//" + "//" + filename);
		// System.out.println(nom + "//" + "//" + filenam1e);
		// out.println(filename);
		// retrieve mimeType dynamically
		String mime = cntx.getMimeType(filename);
		if (mime == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		response.setContentType(mime);
		File file = new File(filename);
		response.setContentLength((int) file.length());

		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();

		// Copy the contents of the file to the output stream
		byte[] buf = new byte[2048];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();

	}

	@RequestMapping(path = "/getUrl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getUrl(HttpServletRequest request) {
		String traba = request.getParameter("traba");
		String id_det = request.getParameter("id_det");
		return g.toJson(gc.readFile(traba, id_det));
	}

	@RequestMapping(path = "/subirArchivo", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") List<MultipartFile> file, @RequestParam("idvac") String idvac,
			@RequestParam("id_det") String id_det, @RequestParam("value") int value,
			MultipartHttpServletRequest request) throws IOException {
		List<String> archi = new ArrayList<>();
		System.out.println(file + "!!!!!!!!!!!!" + idvac + "!!!!!!!!!!!!" + id_det + "!!!!!!!!!!!!" + value);
		int res = 0;
		if (!file.isEmpty()) {
			try {
				for (MultipartFile fi : file) {
					System.out.println(file);
					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));
					System.out.println("controller: " + idvac);
					System.out.println("controller: " + id_det);
					String nombre = destFile.getName();
					System.out.println(nombre);

					res = gc.subirDocumento(fi.getOriginalFilename(), idvac, id_det, value);
				}

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(g.toJson(archi));

			System.out.println(res);

			return "redirect:/vacaciones/consolidado";
		}
		return null;// + url;
		// return gson.toJson(archi);
	}

	@RequestMapping(path = "/reporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String verReporte(Model model, HttpServletRequest request,
			@RequestParam(name = "format", defaultValue = "pdf", required = false) String format) throws JRException {
		// JasperCompileManager.compileReport("D:\\RRHH\\GTH\\gth01\\src\\main\\resources\\jasperreports\\request_report.jrxml");

		try {
			System.out.println("reporte123");
			// String idt = request.getParameter("idtr");
			// System.out.println(idt);
			List<Map<String, Object>> sd = gc.listarConsolidadoAprobado();
			System.out.println(sd);
			System.out.println(model);
			model.addAttribute("format", format);
			model.addAttribute("datasource", sd);
			model.addAttribute("AUTOR", "Tutor de programacion");
			System.out.println(format);
			System.out.println(model);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error controller, reporte: " + e);
		}

		// abrirReporte(getClass().getResource("/src/main/resources/request_report.jrxml").getPath());
		return "cartas_report";
	}

}
