package pe.edu.upeu.gth.controller.holidays;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.JDKKeyFactory.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;
import pe.edu.upeu.gth.interfaz.MailService;
import pe.edu.upeu.gth.util.JavaValidationFTP;

@Controller
@RequestMapping("/vacaciones")
public class FileFtpController {

	Gson gson = new Gson();
	private JavaValidationFTP javaValidationFTP;
	private Map<String, Object> rpta = new HashMap<String, Object>();
	private PrintWriter out;
	SolicitudVacacionesDAO vd = new SolicitudVacacionesDAO(AppConfig.getDataSource());

	@Autowired
	ServletContext context;

	@RequestMapping(path = "uploadSingleFIle", method = RequestMethod.POST)
	public String uploadSingleFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file, @RequestParam("idvac") String idvac,
			@RequestParam("idtrb") String idtrabajador, Authentication authentication) throws Exception {
		// lista completa de los MIME TYPE ->
		// https://developer.mozilla.org/es/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Lista_completa_de_tipos_MIME
		// http://filext.com/faq/office_mime_types.php
		// String dni = ((CUserDTO)authentication.getPrincipal()).getPer_dni();

		System.out.println("your idvac is ->" + idvac + " ---id trab >>>> " + idtrabajador);
		String result = "redirect:/vacaciones/";

		int success = 0;
		if (!file.isEmpty()) {

//			try {

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
				String dateAsString = simpleDateFormat.format(new Date());
				String file_name = "SOL_" + idvac + "_" + dateAsString;
				
				String extension = FilenameUtils.getExtension(((MultipartFile) file).getOriginalFilename());

				javaValidationFTP = new JavaValidationFTP();
				String path = "", newstr = "";

				path = context.getRealPath("/");

				String nuevaRuta = StringUtils.substringBeforeLast(path, "\\gth\\");
				System.out.println("ruta del archivo " + nuevaRuta + "\\documentos");
				
				// //javaValidationFTP.delete(name_file_date,dni+"/"+path);
				// System.out.println(file_name);
				String carpeta = idtrabajador;
				success = vd.subirDocumento("", "", file_name, idvac);
				
				System.out.println("carpeta > " + carpeta + "extension > " + extension + " filename > " + file_name);
				
				int countFileUp = javaValidationFTP.ftpUpFileValidation(file, "vacaciones", file_name , extension,
						carpeta,
						new String[] { "application/pdf",
								"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
								"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
				if (countFileUp > 0) {
					rpta.put("statusF", countFileUp);
					rpta.put("path_file", carpeta + "/" + file_name);
				} else {
					rpta.put("statusF", 0);
				}
								
				
//			} catch (IllegalStateException ec) {
//				ec.getMessage();
//				ec.printStackTrace();
//			}

			System.out.println("respuesta de insertar en la base de datos>> " + success);
		}

		return result;
	}



}
