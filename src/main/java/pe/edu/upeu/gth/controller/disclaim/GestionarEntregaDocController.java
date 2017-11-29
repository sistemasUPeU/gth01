package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.RenunciaDAO;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class GestionarEntregaDocController {		
	
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();
	
	@RequestMapping(value = "/listarxd", method = RequestMethod.GET)
	protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			// String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.gg()));
			break;

		case 2:
			out.println(gson.toJson(rd.correo(request.getParameter("idc"))));
			break;
			
		case 3:
			String de = request.getParameter("de");
            String clave = request.getParameter("clave");
            String para = request.getParameter("para");
            String mensaje = request.getParameter("mensaje");
            String asunto = request.getParameter("asunto");
//            boolean resultado = email.enviarCorreo(de, clave, para, mensaje, asunto);
			out.println(rd.enviarCorreo(de, clave, para, mensaje, asunto));
			break;
			
			
		}

	}

}
