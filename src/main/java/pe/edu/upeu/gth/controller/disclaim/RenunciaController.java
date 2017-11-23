package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
import java.io.IOException;
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
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class RenunciaController {

	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();

	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();

	@RequestMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_principal");
	}

	@RequestMapping(value = "/registrationR", method = RequestMethod.GET)
	public ModelAndView registrarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_registrar");
	}

	@RequestMapping(value = "/authorizationR", method = RequestMethod.GET)
	public ModelAndView autorizarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_aut");
	}

	@RequestMapping(value = "/processR", method = RequestMethod.GET)
	public ModelAndView procesarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_procesar");
	}

	@RequestMapping(value = "/deliveryR", method = RequestMethod.GET)
	public ModelAndView entregarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_entregar");
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
		// <<<<<<< HEAD
	}

	// @RequestMapping("/BuscarDNIDetalleR")
	// public ModelAndView Buscar_Trabajador(@RequestBody HttpServletRequest
	// request) {
	//
	// String dni = request.getParameter("dni");
	//
	// System.out.println(dni);
	// if (!"".equals(dni)) {
	// mp.put("ListarTrabajador", rd.Buscar_DetalleTrabajadorR(dni));
	// return new ModelAndView("/registrationR","rpta",gson.toJson(mp));
	// } else {
	// return new ModelAndView("/registrationR");
	// }
	// }

	// =======
	// }

	// @RequestMapping("/BuscarDNIDetalleR")
	// public ModelAndView Buscar_Trabajador(@RequestBody HttpServletRequest
	// request) {
	//
	// String dni = request.getParameter("dni");
	//
	// System.out.println(dni);
	// if (!"".equals(dni)) {
	// mp.put("ListarTrabajador", rd.Buscar_DetalleTrabajadorR(dni));
	// return new ModelAndView("/registrationR","rpta",gson.toJson(mp));
	// } else {
	// return new ModelAndView("/registrationR");
	// }
	// }

	// Registar Renuncia
	// >>>>>>> branch 'modulo-renuncias' of
	// https://github.com/sistemasUPeU/gth01.git
	@RequestMapping(value = "/detalleR", method = RequestMethod.GET)
	protected void metodosPedidos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.Buscar_DetalleTrabajador(dni)));
			break;

		case 2:
			out.println(gson.toJson(rd.mostrarMotivo()));
			break;
		case 3:
			System.out.println("Creando...");
			Renuncia r = new Renuncia();
			Object s = request.getParameter("file");
			MultipartFile file = (MultipartFile) s;
			r.setId_trabajador(request.getParameter("idtr"));
			// r.setNo_archivo(request.getParameter("no_arch"));
			// r.setTi_archivo(request.getParameter("ti_arch"));
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getContentType());
			// r.setTam_archivo(file.getContentType());
			// r.setFecha(request.getParameter("fecha"));
			out.println(rd.crearRenuncia(r));
			break;

		case 4:
			out.println(gson.toJson(rd.cargarMotivo(request.getParameter("idtr"))));
			break;
		}

	}

	@Autowired
	ServletContext context;

	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") List<MultipartFile> file)
			throws IOException {
		String url = "/";
		
		if (!file.isEmpty()) {

			// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
			// ?, ?)";

			try {

				for (MultipartFile fi : file) {
					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					FilenameUtils fich = new FilenameUtils();
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));

				}

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));

			// String nombre = file.getOriginalFilename();
			// String tipo = file.getContentType();
			// Long tamano = file.getSize();
			// byte[] pixel = file.getBytes();
			// System.out.println(nombre + "//" + tipo + "//" + tamano + "//" + pixel);
			// System.out.println(idtr);
			//
			// Renuncia r = new Renuncia();
			// r.setId_trabajador(idtr);
			// r.setNo_archivo(nombre);
			// r.setTi_archivo(tipo);
			// r.setTam_archivo(tamano);
			// r.setPixel(pixel);
			// System.out.println(rd.crearRenuncia(r));
			// if (rd.crearRenuncia(r) > 0) {
			// System.out.println("BIEN");
			// url = "/renuncias/registrationR";
			// } else {
			// System.out.println("MAL");
			// url = "/";
			//
			// }
			// jdbc.update(sql, nombre, tipo, tamano, pixel);
		}

		return "redirect:" + url;
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

	// <<<<<<< HEAD

	@RequestMapping(value = "/gg", method = RequestMethod.GET)
	protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			// String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.gg()));
			break;

		case 2:/*
				 * Pedido d = new Pedido();
				 * d.setIdfecha(Integer.parseInt(request.getParameter("idfecha")));
				 * d.setHora(request.getParameter("hora"));
				 * d.setIdcurso(Integer.parseInt(request.getParameter("idcurso")));
				 * d.setIdaula(Integer.parseInt(request.getParameter("idaula")));
				 * out.println((hs.create(d)));
				 */
			break;
		}

	}

	// =======

	// Autorizar Renuncia
	@RequestMapping(value = "/AutorizarR", method = RequestMethod.GET)
	protected void metodosAutorizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			out.println(gson.toJson(ra.Autorizar()));
			break;
		case 2:/*
				 * Pedido d = new Pedido();
				 * d.setIdfecha(Integer.parseInt(request.getParameter("idfecha")));
				 * d.setHora(request.getParameter("hora"));
				 * d.setIdcurso(Integer.parseInt(request.getParameter("idcurso")));
				 * d.setIdaula(Integer.parseInt(request.getParameter("idaula")));
				 * out.println((hs.create(d)));
				 */
			break;
		}

	}

	
	@RequestMapping(value ="/vacacion-finish", method = RequestMethod.POST)
    public void jarchiv(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
//            String id_trabajador=request.getParameter("").toString();
//            String direccion_doc=request.getParameter("").toString();
//            String nombre_doc=request.getParameter("").toString();
//            String descripcion_doc=request.getParameter("").toString();
//            String size_doc=request.getParameter("").toString();
//            String type_doc=request.getParameter("").toString();
//            String estado_vacacion=request.getParameter("").toString();
//            String rango_año_inicio=request.getParameter("").toString();
//            String rango_año_fin=request.getParameter("").toString();
//            int numero_detalle=Integer.parseInt(request.getParameter(""));
//            String fecha_inicio=request.getParameter("").toString();
//            String x[]=fecha_inicio.split("");
//            String fecha_fin=request.getParameter("").toString();
//            String y[]=fecha_fin.split("");
//            
            //vaO.vacacion(id_trabajador,direccion_doc,nombre_doc,descripcion_doc,size_doc,type_doc,estado_vacacion,rango_año_inicio,rango_año_fin,numero_detalle, x, y);
//            System.out.println(x);
            Gson g= new Gson();
            out.print(g.toJson(archi));
            out.flush();
        }
        
    }

}
