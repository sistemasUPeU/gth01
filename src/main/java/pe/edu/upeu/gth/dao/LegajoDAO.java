package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.dto.Detalle_legajo;
import pe.edu.upeu.gth.dto.Legajo;
import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.dto.Renuncia;

public class LegajoDAO {
	String sql;
	private static JdbcTemplate jt;

	public LegajoDAO(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}
	
	public int insertarLegajo(Legajo r) {
		int x = 0;
		String sql = "INSERT INTO RA_LEGAJO(ID_CONTRATO,ID_TRABAJADOR) VALUES(?,?)";
		try {
			jt.update(sql, new Object[] { r.getIdcontrato(),r.getIdtrabajador()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	public int actualizarPasoFinal(String idr,String tipo) {
		System.out.println("LLEGANDO AL DAO"+tipo);
		int x = 0;
		String sql="";
		if(tipo.equals("R")) {
			System.out.println("Es renuncia");
			sql = "UPDATE RA_RENABAN_PASOS SET ESTADO = '1' WHERE ID_RENABAN=? AND ID_PASOS='PAS-000440' ";
		}else {
			System.out.println("Es abandono");
			sql= "UPDATE RA_RENABAN_PASOS SET ESTADO = '1' WHERE ID_RENABAN=? AND ID_PASOS='PAS-000441' ";
		}
		try {
			jt.update(sql,idr);
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	public int InsertarDocBenfSoc(Detalle_legajo ob) {
		int x = 0;
		String sql = "call RA_SP_INSERTAR_DOCUMENTOS(? , ?, ?, ?)";
//		String sql = "UPDATE REN_RENUNCIA SET ESTADO ='Rechazado', OBSERVACIONES=?, FECHA_RECHAZO=SYSDATE WHERE ID_RENUNCIA =? ";
		try {
		 jt.update(sql,new Object[] {ob.getDescripcion(),ob.getNo_archivo(),ob.getTi_archivo(),ob.getFecha_registro()});
		 x=1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e);
		}
		return x;
		
	}
	//NOTIFICAR DOCUMENTOS 
	public int NotificarDocumentos(String de, String clave, String para, String mensaje, String asunto,String foto) {
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
	
	
	public int EntregarRenuncia(Renuncia r) {
		int x = 0;
		String sql = "UPDATE REN_RENUNCIA SET ESTADO='Entregado' WHERE ID_RENUNCIA=?";
		try {
			jt.update(sql, new Object[] { r.getId_renuncia()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	//LEGAJO DE TODO LOS TRABAJADORES
	public List<Map<String, Object>> legajos(String depa){
		System.out.println("Esto es nom depa: "+depa);
		sql="select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO=1 AND rap.ID_PASOS IN ('PAS-000440','PAS-000441') ORDER BY ra.FECHA_RENABAN DESC";
		return jt.queryForList(sql);
	}
	//OBSERVAR LEGAJOS
	public List<Map<String, Object>> Buscar_Documentos(String idl) {
		sql = "SELECT * FROM RA_DETALLE_LEGAJO";

		sql += " where ID_LEGAJO='" + idl + "' ";

		return jt.queryForList(sql);
	}
	
	public List<Map<String, Object>> doc(String idcontrato) {
		sql = "select * from RA_VIEW_RENABAN where ID_CONTRATO=?";
		return jt.queryForList(sql,idcontrato);
	}

}
