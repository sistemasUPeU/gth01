package pe.edu.upeu.gth.controller.processCore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.PasoDAO;

@Controller
@RequestMapping("/paso/")
public class CPaso {
	DataSource ds=AppConfig.getDataSource();
	PasoDAO p =new PasoDAO(ds);
    Map<String, Object> rpta = new HashMap<String, Object>();
    Gson gson = new Gson();
	PrintWriter out;
	@GetMapping("Listar_habilitados")
	public void Listar_habilitados(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("proceso");
        List<Map<String, Object>> lista = p.List_Paso_Habilitado(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        respuesta(response);
	}
	
	@GetMapping("Paso_Puesto")
	public void Paso_Puesto(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
        List<Map<String, Object>> lista = p.List_Paso_x_Puesto(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        respuesta(response);
	}
	
	@GetMapping("Mantenimiento")
	public void Mantenimiento(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("/Proceso/Menu_Mantenimiento");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("Eliminar")
	public void Eliminar(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("paso");
        p.DELETE_PASOS(id);
	}
	
	@GetMapping("Registrar")
	public void Registrar(HttpServletRequest request, HttpServletResponse response) {
		String ID_PROCESO = request.getParameter("proceso");
        String DE_PASOS = request.getParameter("desc");
        String NU_PASOS = request.getParameter("num");
        String CO_PASOS = request.getParameter("cod");
        p.INSERT_PASOS(null, ID_PROCESO, DE_PASOS, NU_PASOS, CO_PASOS);
	}
	
	@GetMapping("Update_nu_paso")
	public void Update_nu_paso(HttpServletRequest request, HttpServletResponse response) {
		int numero = Integer.parseInt(request.getParameter("num"));
        for (int i = 1; i <= numero-1; i++) {
            String ID_PASO = request.getParameter("id" + i);
            String NU_PASO = request.getParameter("numero" + i);
            p.UPDATE_NU_PASO(ID_PASO, NU_PASO);
        }
	}
	
	@GetMapping("Modificar")
	public void Modificar(HttpServletRequest request, HttpServletResponse response) {
		String ID_PASO = request.getParameter("id");
        String ID_PROCESO = request.getParameter("proceso");
        String DE_PASOS = request.getParameter("desc");
        String NU_PASOS = request.getParameter("num");
        String CO_PASOS = request.getParameter("cod");
        String ESTADO = request.getParameter("estado");
        p.UPDATE_PASOS(ID_PASO, ID_PROCESO, DE_PASOS, NU_PASOS, CO_PASOS, ESTADO);
         rpta.put("rpta", "1");
         respuesta(response);
	}
	
	@GetMapping("Eliminar_PP")
	public void Eliminar_PP(HttpServletRequest request, HttpServletResponse response) {
		String idpp = request.getParameter("id");
        p.DELETE_PUESTO_PASO(idpp);
	}
	
	@GetMapping("actualizar_estado")
	public void actualizar_estado(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
        String estado = request.getParameter("estado");
        p.ESTADO_DETALLE_PUESTO(id, estado);
	}
	
	public void respuesta(HttpServletResponse response) {
		try {
			out = response.getWriter();
	        out.println(gson.toJson(rpta));
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			rpta.put("rpta", "-1");
            rpta.put("mensaje", e.getMessage());
            out.print(gson.toJson(rpta));
            out.flush();
            out.close();
		}
	}

}
