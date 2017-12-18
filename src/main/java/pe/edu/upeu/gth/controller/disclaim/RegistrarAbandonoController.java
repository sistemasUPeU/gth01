package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.AbandonoDAO;
import pe.edu.upeu.gth.dto.Abandono;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class RegistrarAbandonoController {
	
//	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
//	Abandono r = new Abandono();
	AbandonoDAO rd = new AbandonoDAO(AppConfig.getDataSource());
//	Detalle_motivoDAO det = new Detalle_motivoDAO(AppConfig.getDataSource());
//	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();
	
	@Autowired
	ServletContext context;

	@RequestMapping(path = "/reg_aban", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") List<MultipartFile> file, @RequestParam("fecha") String fecha,
			@RequestParam("idcontrato") String idcon, Authentication authentication) throws IOException {

		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		
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
//					System.out.println("asdasdasdasdasdas" +idusuario);
					System.out.println(idcon);
					Abandono r = new Abandono();
					r.setFecha(fecha);
					r.setNo_archivo(destFile.getName());
					r.setTi_archivo(FilenameUtils.getExtension(path));
					r.setId_contrato(idcon);
					r.setId_usuario(idusuario);
					r.setTipo("A");
					rd.insertarAbandono(r);
				}

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));
		}
		return "redirect:" + url;
	}

}
