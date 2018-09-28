package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.Abandono;
import pe.edu.upeu.gth.dto.Justificacion;
import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.dto.Renuncia;
import pe.edu.upeu.gth.interfaz.CRUDOperations;

public class PriCartaNotarialDAO implements CRUDOperations {
	String sql;
	PreparedStatement ps;
	CallableStatement cs;
	ResultSet rs;
	Connection cn;
	DataSource d = AppConfig.getDataSource();

	private static JdbcTemplate jt;

	public PriCartaNotarialDAO(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}

	@Override
	public ArrayList<Map<String, Object>> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> readAll() {
		List<Map<String, Object>> readAll = new ArrayList<>();
		sql = "SELECT * FROM RHTC_PASOS";

		return readAll;
	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Map<String, Object>> Pendiente(String depa) {
		sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='0' AND rap.ID_PASOS='PAS-000435' ORDER BY ra.FECHA_RENABAN DESC";
		
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> Enviar() {
		sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,DNI FROM REN_VIEW_TRABAJADOR";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> Buscar_DetalleTrabajador(String ida) {
		sql = "select ID_RENABAN,ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO,CORREO FROM RA_VIEW_RENABAN";

		sql += " where ID_CONTRATO='" + ida + "' ";

		return jt.queryForList(sql);
	}

	// LISTA TODOS LOS TRABAJADORES CON ESTADO AUTORIZADO
	public List<Map<String, Object>> Autorizado() {
		sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='0' AND rap.ID_PASOS='PAS-000442' ORDER BY ra.FECHA_RENABAN DESC";
		return jt.queryForList(sql);
	}
	
	// LISTA TODOS LOS TRABAJADORES CON LA SEGUNDA CARTA NOTARIAL ENVIADA
		public List<Map<String, Object>> DerivadoSegundaCarta() {
			sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='1' AND rap.ID_PASOS='PAS-000442' ORDER BY ra.FECHA_RENABAN DESC";
			return jt.queryForList(sql);
		}

	public int AutorizarRenuncia(Abandono r) {
		int x = 0;
		String sql = "UPDATE RA_RENABAN SET ESTADO='Autorizado' WHERE ID_RENABAN=? ";
		try {
			jt.update(sql, new Object[] { r.getIdabandono() });
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	public int JustificarAbandono(String idrenaban,String observacion) {
		int x = 0;
		String sql = "INSERT INTO RA_JUSTIFICACION (ID_RENABAN,FECHA_JUSTIFICACION,OBSERVACION) VALUES(?,?,?) ";
		Date fechaActual = new java.sql.Date(System.currentTimeMillis());
		try {
			jt.update(sql, new Object[] { idrenaban,fechaActual, observacion});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}

//	public int JustificarAbandono(Justificacion ob) {
//		int x = 0;
//		String sql = "call REN_UPDATE_ABANDONO( ? , ?)";
//		// String sql = "UPDATE REN_RENUNCIA SET ESTADO ='Rechazado', OBSERVACIONES=?,
//		// FECHA_RECHAZO=SYSDATE WHERE ID_RENUNCIA =? ";
//		try {
//			jt.update(sql, new Object[] { ob.getId_renaban(), ob.getObservaciones() });
//			x = 1;
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Error:" + e);
//		}
//		return x;
//
//	}

	// Enviar Primera Carta Notarial
	public int enviarCorreo(String correo, String mensaje, String asunto,String ruta,String nombreArchivo,String idusuario,String idrenaban) {
		String de = "pruebagth@gmail.com";
		String clave = "GTH123456";
		String para = "jonathanromero@upeu.edu.pe";
//		mensaje = "que pasho!!!";
//		asunto = "Con todo menos miedo";
		int enviado = 0;
		try {
			String new_mensaje = "<p style='text-align:justify'>"+mensaje+"</p ></br> Atentamente Recursos Humanos - Universidad Peruana Unión, <br> Gracias </br>";

			BodyPart texto = new MimeBodyPart();
			texto.setContent(new_mensaje, "text/html");

			BodyPart adjunto = new MimeBodyPart();
//			((MimeBodyPart) adjunto).attachFile(foto);
			adjunto.setDataHandler(
			new DataHandler(new FileDataSource(ruta)));
			adjunto.setFileName(nombreArchivo);

			MimeMultipart miltiparte = new MimeMultipart();
			miltiparte.addBodyPart(texto);
			miltiparte.addBodyPart(adjunto);
			String host = "smtp.gmail.com";
			int port = 587;

			Properties prop = System.getProperties();
			String[] parts = de.split("@");
			String part1 = parts[0]; // 123
			String tipo = parts[1]; // 654321

			if (tipo.equals("live.com") || tipo.equals("hotmail.com") || tipo.equals("outlook.com")) {
				host = "smtp.live.com";
				port = 27;
			}

			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.user", de);
			prop.put("mail.smtp.password", clave);
			prop.put("mail.smtp.port", port);
			prop.put("mail.smtp.auth", "true");

			Session sesion = Session.getDefaultInstance(prop, null);

			MimeMessage message = new MimeMessage(sesion);

			message.setFrom(new InternetAddress(de));

			/*
			 * 
			 * NOTA: para enviar correo electronico masivo
			 * 
			 * InternetAddress[] direcciones = new InternetAddress[para.length]; for(int
			 * i=0;i<para.length;i++){ direcciones[i] = new InternetAddress(para[i]); }
			 * 
			 * for(int i=0;i<direcciones.length;i++){
			 * message.addRecipient(Message.RecipientType.TO, direcciones[i]); }
			 * 
			 */

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));

			message.setSubject(asunto);
			message.setContent(miltiparte);

			Transport transport = sesion.getTransport("smtp");

			transport.connect(host, de, clave);

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();
			
			//PAS-000435

			enviado = 1;
			PrimeraCarta(idrenaban, idusuario);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return enviado;
	}
	
	// Enviar Segunda Carta Notarial
		public int enviarCorreo2(String correo, String mensaje, String asunto,String ruta,String nombreArchivo,String idusuario,String idrenaban) {
			String de = "pruebagth@gmail.com";
			String clave = "GTH123456";
			String para = "jonathanromero@upeu.edu.pe";
//			mensaje = "que pasho!!!";
//			asunto = "Con todo menos miedo";
			int enviado = 0;
			try {
				String new_mensaje = "<p style='text-align:justify'>"+mensaje+"</p ></br> Atentamente Recursos Humanos - Universidad Peruana Unión, <br> Gracias </br>";

				BodyPart texto = new MimeBodyPart();
				texto.setContent(new_mensaje, "text/html");

				BodyPart adjunto = new MimeBodyPart();
//				((MimeBodyPart) adjunto).attachFile(foto);
				adjunto.setDataHandler(
				new DataHandler(new FileDataSource(ruta)));
				adjunto.setFileName(nombreArchivo);

				MimeMultipart miltiparte = new MimeMultipart();
				miltiparte.addBodyPart(texto);
				miltiparte.addBodyPart(adjunto);
				String host = "smtp.gmail.com";
				int port = 587;

				Properties prop = System.getProperties();
				String[] parts = de.split("@");
				String part1 = parts[0]; // 123
				String tipo = parts[1]; // 654321

				if (tipo.equals("live.com") || tipo.equals("hotmail.com") || tipo.equals("outlook.com")) {
					host = "smtp.live.com";
					port = 27;
				}

				prop.put("mail.smtp.starttls.enable", "true");
				prop.put("mail.smtp.host", host);
				prop.put("mail.smtp.user", de);
				prop.put("mail.smtp.password", clave);
				prop.put("mail.smtp.port", port);
				prop.put("mail.smtp.auth", "true");

				Session sesion = Session.getDefaultInstance(prop, null);

				MimeMessage message = new MimeMessage(sesion);

				message.setFrom(new InternetAddress(de));

				/*
				 * 
				 * NOTA: para enviar correo electronico masivo
				 * 
				 * InternetAddress[] direcciones = new InternetAddress[para.length]; for(int
				 * i=0;i<para.length;i++){ direcciones[i] = new InternetAddress(para[i]); }
				 * 
				 * for(int i=0;i<direcciones.length;i++){
				 * message.addRecipient(Message.RecipientType.TO, direcciones[i]); }
				 * 
				 */

				message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));

				message.setSubject(asunto);
				message.setContent(miltiparte);

				Transport transport = sesion.getTransport("smtp");

				transport.connect(host, de, clave);

				transport.sendMessage(message, message.getAllRecipients());

				transport.close();
				
				//PAS-000435

				enviado = 1;
				SegundaCarta(idrenaban, idusuario);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return enviado;
		}
//PRIMERA CARTA NOTARIAL
	public int PrimeraCarta(String idrenaban, String idusuario) {
		int x = 0;
		String sql = "INSERT INTO RA_RENABAN_PASOS(ID_RENABAN,ID_PASOS,ID_USUARIO,FECHA_MOD) VALUES(?,?,?,?)";
		String sql2 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1,FECHA_MOD=? WHERE ID_PASOS='PAS-000435' AND ID_RENABAN=?";
		
		Date fechon = new java.sql.Date(System.currentTimeMillis());
		System.out.println(fechon);
		try {
			jt.update(sql, new Object[] { idrenaban,"PAS-000442",idusuario,fechon});
			jt.update(sql2,new Object[] {fechon, idrenaban});
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}


//SEGUNDA CARTA NOTARIAL
	public int SegundaCarta(String idrenaban, String idusuario) {
		int x = 0;
		String sql = "INSERT INTO RA_RENABAN_PASOS(ID_RENABAN,ID_PASOS,ID_USUARIO,FECHA_MOD) VALUES(?,?,?,?)";
		String sql2 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1,FECHA_MOD=? WHERE ID_PASOS='PAS-000442' AND ID_RENABAN=?";
		
		Date fechon = new java.sql.Date(System.currentTimeMillis());
		System.out.println(fechon);
		try {
			jt.update(sql, new Object[] { idrenaban,"PAS-000437",idusuario,fechon});
			jt.update(sql, new Object[] { idrenaban,"PAS-000439",idusuario,fechon});
			jt.update(sql2,new Object[] {fechon,idrenaban});
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	//PREGUNTAR SI PASARON LOS 6 DÍAS(144 horas) DESDE LA PRIMERA CARTA
		public double PreguntarPrimera(String idrenaban) {
			double x =0;
			sql = "SELECT* FROM RA_RENABAN_PASOS WHERE ID_RENABAN='" + idrenaban + "' AND ID_PASOS='PAS-000435'";
			try {
				 Map<String, Object> o = jt.queryForMap(sql);
				 String fecha =o.get("FECHA_MOD").toString();
				 Date fechaActual = new Date();
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			     Date fechaCartaNotarial = sdf.parse(fecha);
//			    x = fechaActual.compareTo(fechaCartaNotarial);
			    int difference= (int)(fechaActual.getTime()-fechaCartaNotarial.getTime());
			    x=difference/3600000;
			    System.out.println("Valor de x después de la operación: "+x);
//			    System.out.println("Esta es la fecha del sistema: "+fechaActual);
//			    System.out.println("Y esta otra es la de la primera carta: "+fechaCartaNotarial);
//			    System.out.println("Esta es la diferencia: "+(int)(fechaActual.getTime()-fechaCartaNotarial.getTime()));
			} catch (Exception e) {
				System.out.println("Error en preguntarPrimera: "+e);
			}
			return x;
		}
		
		//PREGUNTAR SI PASARON LOS 6 DÍAS(144 horas) DESDE LA PRIMERA CARTA
				public double PreguntarSegunda(String idrenaban) {
					double x =0;
					sql = "SELECT* FROM RA_RENABAN_PASOS WHERE ID_RENABAN='" + idrenaban + "' AND ID_PASOS='PAS-000435'";
					try {
						 Map<String, Object> o = jt.queryForMap(sql);
						 String fecha =o.get("FECHA_MOD").toString();
						 Date fechaActual = new Date();
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					     Date fechaCartaNotarial = sdf.parse(fecha);
//					    x = fechaActual.compareTo(fechaCartaNotarial);
					    int difference= (int)(fechaActual.getTime()-fechaCartaNotarial.getTime());
					    x=difference/3600000;
					    System.out.println("Valor de x después de la operación: "+x);
//					    System.out.println("Esta es la fecha del sistema: "+fechaActual);
//					    System.out.println("Y esta otra es la de la primera carta: "+fechaCartaNotarial);
//					    System.out.println("Esta es la diferencia: "+(int)(fechaActual.getTime()-fechaCartaNotarial.getTime()));
					} catch (Exception e) {
						System.out.println("Error en preguntarPrimera: "+e);
					}
					return x;
				}
	
	//SEGUNDA CARTA NOTARIAL
	public List<Map<String, Object>> Buscar_Detalle(String ids) {
		sql = "select ID_RENABAN,ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO,CORREO FROM RA_VIEW_RENABAN";

		sql += " where ID_CONTRATO='" + ids + "' ";

		return jt.queryForList(sql);
	}
	
	
	//JUSTIFICAR PRIMERA CARTA
	public int JustificarPrimeraCarta(String idrenaban, String observacion) {
		int x = 0;
		String sql = "call RA_JUST_PRI_CARTA( ? , ?, ?)";
		Date fechaActual = new java.sql.Date(System.currentTimeMillis());
		try {
			jt.update(sql, new Object[] { idrenaban,fechaActual,observacion});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e);
		}
		return x;

	}
	
	//JUSTIFICAR SEGUNDA CARTA
		public int JustificarSegundaCarta(String idrenaban, String observacion) {
			int x = 0;
			String sql = "call RA_JUST_SEG_CARTA( ? , ?, ?)";
			Date fechaActual = new java.sql.Date(System.currentTimeMillis());
			try {
				jt.update(sql, new Object[] { idrenaban,fechaActual,observacion});
				x = 1;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error:" + e);
			}
			return x;

		}

//	public List<Map<String, Object>> listarNotificados() {
//		sql = "select * from RA_VIEW_RENABAN WHERE ESTADO='Notificado'";
//		return jt.queryForList(sql);
//	}
}
