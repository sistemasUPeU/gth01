package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public int enviarCorreo(String de, String clave, String para, String mensaje, String asunto,String foto) {
//		de = "pruebagth@gmail.com";
//		clave = "GTH123456";
//		para = "estefannygarcia@upeu.edu.pe";
//		mensaje = "que pasho!!!";
//		asunto = "Con todo menos miedo";
		int enviado = 0;
		try {

			BodyPart texto = new MimeBodyPart();
			texto.setContent(mensaje, "text/html");

			BodyPart adjunto = new MimeBodyPart();
//			((MimeBodyPart) adjunto).attachFile(foto);
			adjunto.setDataHandler(
			new DataHandler(new FileDataSource("C:/Users/AlphaTeam-02-NicoleG/Pictures/mem1.jpg")));
			adjunto.setFileName("mem1.jpg");

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

			enviado = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return enviado;
	}

	public int notificarAbandono(Abandono a) {
		int x = 0;
		String sql = "UPDATE ABANDONO SET ESTADO=? WHERE IDABANDONO=?";
		try {
			jt.update(sql, new Object[] { a.getEstado(), a.getIdabandono() });
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}

	public List<Map<String, Object>> listarNotificados() {
		sql = "select * from RA_VIEW_RENABAN WHERE ESTADO='Notificado'";
		return jt.queryForList(sql);
	}
}
