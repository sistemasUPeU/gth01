package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import javax.sql.DataSource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.GestionarPrograVacacDAO;
import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@RequestMapping("/vacaciones/gestionar_programa")
public class GestionarProgramaController {
	DataSource ds = AppConfig.getDataSource();
	GestionarPrograVacacDAO ge =  new GestionarPrograVacacDAO(ds);
	Gson gs = new Gson();
	@RequestMapping(path = "/mostrardetalle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String insertarNuevoPlazo(HttpServletRequest request) {
		String idtrab = request.getParameter("idtrab");

	System.out.println("Gson RESPUESTA json detalle del trab  "+gs.toJson(ge.listardetalle(idtrab)));
		

		return gs.toJson(ge.listardetalle(idtrab));
	}
	
	
	@RequestMapping(path = "/insertarAprobacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String insertarAprobacion(@RequestBody String ini , HttpServletRequest request, Authentication authentication) {
		//@RequestBody String ini
//		@RequestParam(value="ini[]") String[] ini
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		System.out.println("es ini >> " + ini);
		String[] array = ini.trim().split(",");
		System.out.println("array original " + array);
		if(array[0].toString().equals("")) {
			System.out.println("primera posicion es cero");
			array = (String[]) ArrayUtils.remove(array, 0); 
		}
		int respuesta = 0;
		int pos = 0;
		int last = array.length;
		for(int i = 0 ; i<array.length; i++) {
			System.out.println(array[i]);
			if(array[i].equals("&")) {
				System.out.println("posicion del i& " + i);
				pos = i;
				
				break;
			}
		}
		int last_pos = last-1;
		System.out.println("last pos >> "+pos+ " , "+last_pos);
		int tipo = 0 ;
		if( pos == last_pos) {
			//es solo aprobados tipo 1, 
			String[] det_apr = (String[]) ArrayUtils.remove(array, pos); 
			System.out.println("string array solo aprobados > " + det_apr );
			
//			for(int m = 0 ; m<det_apr.length ; m++) {
//				System.out.println(det_apr[m] );
//			}
			tipo = 1;
			respuesta = ge.registrarCambiosAprobacion(usuario, det_apr, new String[] {""}, tipo);
		}else if(pos == 0) {
			//es solo rechazados tipo 2
			String[] det_re = (String[]) ArrayUtils.remove(array, pos); 
			System.out.println("string array solo rechazados > " + det_re );
//			for(int m = 0 ; m<det_re.length ; m++) {
//				System.out.println(det_re[m] );
//			}
			tipo = 2;
			respuesta = ge.registrarCambiosAprobacion(usuario, new String[] {""}, det_re, tipo);
		}else {
			//existe ambos tipo 3
			

			String[] det_apr = new String[pos];
			int rest = last_pos - pos;
			String[] det_re = new String[rest];
			
			for(int j = 0 ; j<pos; j++) {
				det_apr[j] = array[j];
			}
			int posi = pos + 1;
			int cont = 0;
			for(int j = posi ; j<=last_pos; j++) {
				det_re[cont] = array[j];
				cont++;
			}

			System.out.println("string array  aprobados > " + det_apr + " , string array rechazados >> " +  det_re );
			tipo = 3;
			respuesta = ge.registrarCambiosAprobacion(usuario, det_apr, det_re, tipo);
		}

		return gs.toJson(respuesta);
	}
	
	@RequestMapping(path = "/readallProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllProgramaVacaciones(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println(depa);
		return gs.toJson(DAO.READALL(depa));

	}

	@RequestMapping(path = "/TrabajadoresConSoliProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadoresConSoli(Authentication authentication) {
		
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println("gson trabajadores con solicitud  >>>>> " + gs.toJson(DAO.TrabajadoresConSoli(depa)));
		return gs.toJson(DAO.TrabajadoresConSoli(depa));

	}
	
	@RequestMapping(path = "/TrabajadoresAprobados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadoresAprobados(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println(depa);
		return gs.toJson(DAO.TrabajadoresAprobados(depa));

	}

//	@RequestMapping(path = "GestionarProgramaVacaciones/insertProgramaVacaciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody String insertProgramaVacaciones(HttpServletRequest request, Authentication authentication) {
//		DataSource ds = AppConfig.getDataSource();
//		GestionarPrograVacacDAO t = new GestionarPrograVacacDAO(ds);
//		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
//		String id_det = request.getParameter("id_det");
//		String[] asdf = id_det.split(",");
//		return gs.toJson(t.apobarVac(usuario, asdf));
//	}
	
	@RequestMapping(path = "/detalleaprobados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String listardetalleaprobados(HttpServletRequest request) {
		String idtrab = request.getParameter("idtrab");
		System.out.println("Gson RESPUESTA json detalle del trab  "+gs.toJson(ge.listardetalleaprobados(idtrab)));
		return gs.toJson(ge.listardetalleaprobados(idtrab));
	}
	
	
	
	
	
	
	
}
