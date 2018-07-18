package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.Detalle_motivoDAO;
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Detalle_motivo;
import pe.edu.upeu.gth.dto.Renuncia;
import pe.edu.upeu.gth.util.DateFormat;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class RenabanController {
	
	private Gson gson = new Gson();
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource()); 
	@RequestMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_principal");
	}

	@RequestMapping(value = "/registrationR", method = RequestMethod.GET)
	public ModelAndView registrarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/RegistrarRenuncia");
	}
	
	@RequestMapping(value = "/registrationA", method = RequestMethod.GET)
	public ModelAndView registrarAbandono(ModelMap model) {
		return new ModelAndView("abandono/RegistrarAbandono");
	}
	
	//NUEVO
	@RequestMapping(value = "/listaRA", method = RequestMethod.GET)
	public ModelAndView reportes(ModelMap model) {
		return new ModelAndView("renuncia/ListaRenaban");
	}


	@RequestMapping(value = "/authorizationR", method = RequestMethod.GET)
	public ModelAndView autorizarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/AutorizarRenuncia");
	}

	@RequestMapping(value = "/processR", method = RequestMethod.GET)
	public ModelAndView procesarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ProcesarRenuncia");
	}

	@RequestMapping(value = "/deliveryR", method = RequestMethod.GET)
	public ModelAndView entregarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_notificar");
	}

	@RequestMapping(value = "/processDetails", method = RequestMethod.GET)
	public ModelAndView detalleP(ModelMap model) {
		return new ModelAndView("renuncia/ren_DetalleP");
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView detalles(ModelMap model) {

		return new ModelAndView("renuncia/ren_detalle");
	}

	@RequestMapping(value = "/letterR", method = RequestMethod.GET)
	public ModelAndView imprimir(ModelMap model) {
		return new ModelAndView("renuncia/ren_emitir");
	}
  
	@RequestMapping(value = "/recepcionargth", method = RequestMethod.GET)
	public ModelAndView recepcionarenuncia(ModelMap model) {
		return new ModelAndView("renuncia/RecepcionarDetalle");
	}
	
	@RequestMapping(value = "/VerificarGTH", method = RequestMethod.GET)
	public ModelAndView Verificarenuncia(ModelMap model) {
		return new ModelAndView("renuncia/VerificarDet");
	}
	
	@RequestMapping(value = "/segundoEnvio", method = RequestMethod.GET)
	public ModelAndView SegundaCarta(ModelMap model) {
		return new ModelAndView("abandono/SegundaCarta");
	}
	
	@RequestMapping(value = "/firstNLetter", method = RequestMethod.GET)
	public ModelAndView detalleCartaNotarial(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("abandono/DetallePriCarta");
		String idc = request.getParameter("idc");
		System.out.println(idc);
		model.addObject("listaDetalle",ra.Buscar_DetalleTrabajador(idc));
		System.out.println(gson.toJson(ra.Buscar_DetalleTrabajador(idc)));
		return model;
	}

	@RequestMapping(value = "/mostrardoc1")
	public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext cntx = request.getServletContext();
		System.out.println();

//		String nom = (String) result1.get(0).get("NO_ARCHIVO");
//		String tipo = (String) result1.get(0).get("TI_ARCHIVO");
		String filename = cntx.getRealPath("/WEB-INF/caucha.jpg");
//		String filename = "C:/Users/Deyvis Garcia/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/gth/WEB-INF/hola/30122019_130305407813962_2002991644_o.jpg";
		System.out.println(filename);
//		 String filenam1e ="E:\\TRABAJO\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gth\\WEB-INF\\david\\"+nom;

//		System.out.println(nom + "//" + "//" + filename);
//		System.out.println(nom + "//" + "//" + filenam1e);
//		out.println(filename);
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
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();

	}

	@RequestMapping(value = "/uploaded")
	public void getUploadedPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println(request.getParameter("idtr"));
		// String sql = "SELECT pixel, tipo FROM imagen WHERE nombre = '" + nombre +
		// "'";
		List<Map<String, Object>> result = rd.cargarMotivo(request.getParameter("idtr"));

		if (!result.isEmpty()) {
			byte[] bytes = (byte[]) result.get(0).get("PIXEL");
			String mime = (String) result.get(0).get("TI_ARCHIVO");
			System.out.println(bytes);
			System.out.println(mime);
			// System.out.println(result.get(0));

			response.setHeader("Content-Type", mime);
			response.getOutputStream().write(bytes);
		}
	}

}
