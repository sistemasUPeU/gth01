/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import oracle.jdbc.OracleConnection;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.Renuncia;

/**
 *
 * @author Nicole
 */
public class RenunciaDAO {

	String sql;
	PreparedStatement ps;
	CallableStatement cs;
	ResultSet rs;
	Connection cn;
	DataSource d = AppConfig.getDataSource();

	private static JdbcTemplate jt;

	public RenunciaDAO(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}

	public ArrayList<Map<String, Object>> listar() {
		sql = "SELECT * FROM RENUNCIA r,RHTM_DGP d,RHTM_TRABAJADOR t\n" + "where r.ID_DGP=d.ID_DGP\n"
				+ "and d.ID_TRABAJADOR=t.ID_TRABAJADOR;";
		ArrayList<Map<String, Object>> lista = new ArrayList<>();
		try {
			cn = d.getConnection();
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("idrenuncia", rs.getString("idrenuncia"));
				// m.put("no_trabajador", rs.getString("no_trabajador"));
				// m.put("ap_paterno", rs.getString("ap_paterno"));
				// m.put("ap_materno", rs.getString("ap_materno"));
				// m.put("id_contrato", rs.getString("id_contrato"));
				lista.add(m);

			}
		} catch (Exception e) {
			System.out.println("Error al Listar Renuncias" + e);
		} finally {
			try {
				cn.close();
			} catch (SQLException ex) {
				Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return lista;
	}

	public boolean add(Object o) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	public boolean edit(Object o) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	public boolean delete(Object o) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	public ArrayList<Map<String, Object>> listarEmpleados() { // lista a todos los empleados por departamento.
		sql = "select rt.ID_TRABAJADOR,rt.NO_TRABAJADOR,rt.AP_PATERNO,rt.AP_MATERNO, rt.NU_DOC  ,ra.NO_AREA,rs.NO_SECCION,rp.NO_PUESTO,rc.FE_DESDE,rc.FE_HASTA "
				+ "from rhtr_puesto rp, rhtd_area ra, rhtr_seccion rs, rhtm_trabajador rt, rhtm_contrato rc,rhtx_departamento rd "
				+ "where rp.ID_SECCION = rs.ID_SECCION and rs.ID_AREA = ra.ID_AREA and rc.ID_PUESTO = rp.ID_PUESTO  and rc.ID_TRABAJADOR = rt.ID_TRABAJADOR and rd.ID_DEPARTAMENTO = ra.ID_DEPARTAMENTO "
				+ "       and rd.ID_DEPARTAMENTO='DPT-0017' " + "AND RC.FE_HASTA > SYSDATE " + "ORDER BY ( RA.NO_AREA)";
		return (ArrayList<Map<String, Object>>) jt.queryForList(sql);
	}

	public ArrayList<Map<String, Object>> DetalleEmp(String idTR) { // Esto Lista el detalle del Trabajador.
		String sql = "select rc.ID_CONTRATO , rc.ID_DGP , rt.ID_TRABAJADOR,rt.NO_TRABAJADOR,rt.AP_PATERNO,rt.AP_MATERNO, rt.NU_DOC , rt.CL_TRA   ,ra.NO_AREA,rs.NO_SECCION,rp.NO_PUESTO,TO_CHAR(rc.FE_DESDE, 'Month DD, YYYY')AS FEC_INI ,TO_CHAR(rc.FE_HASTA, 'Month DD, YYYY')AS FEC_FIN \n"
				+ "from rhtr_puesto rp, rhtd_area ra, rhtr_seccion rs, rhtm_trabajador rt, rhtm_contrato rc,rhtx_departamento rd\n"
				+ "where rp.ID_SECCION = rs.ID_SECCION and rs.ID_AREA = ra.ID_AREA and rc.ID_PUESTO = rp.ID_PUESTO  and rc.ID_TRABAJADOR = rt.ID_TRABAJADOR and rd.ID_DEPARTAMENTO = ra.ID_DEPARTAMENTO\n"
				+ "       and rd.ID_DEPARTAMENTO='DPT-0017' and RT.ID_TRABAJADOR = ? ";
		return (ArrayList<Map<String, Object>>) jt.queryForList(sql, idTR.trim());
	}

	public ArrayList<Map<String, Object>> DetRen() {
		String sql = "SELECT * FROM RENUNCIA r,RHTM_DGP d,RHTM_TRABAJADOR t\n" + "where r.ID_DGP=d.ID_DGP\n"
				+ "and d.ID_TRABAJADOR=t.ID_TRABAJADOR";

		return (ArrayList<Map<String, Object>>) jt.queryForList(sql);

	}

	public void Renuncia(String idContr, String idDgp, String User_au, String DirecADj, String Nom_Adj, String Desc,
			String Size_Adj, String Type_Adj, String Opc) {
		String sql = "{CALL RHSP_INSERT_ADJUNTARRENUNCIA ( ? , ? , ? , ? , ? , ? , ? , ? , ? )}";
		jt.update(sql, idContr, idDgp, User_au, DirecADj, Nom_Adj, Desc, Size_Adj, Type_Adj, Opc);
	}

	// Esto Lista el detalle del Trabajador filtrado por DNI en la interfaz
	// "Registrar Renuncia".
	public List<Map<String, Object>> Buscar_DetalleTrabajador(String dni) {
		sql = "select ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD FROM REN_VIEW_TRABAJADOR";

		sql += " where DNI='" + dni + "' ";

		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> gg() {
		sql = "select * from REN_VIEW_RENUNCIA";
		return jt.queryForList(sql);
	}

	// Lista de motivos de renuncia
	public List<Map<String, Object>> mostrarMotivo() {
		sql = "SELECT* FROM REN_MOTIVO";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> cargarMotivo(String idtr) {
		sql = "SELECT * FROM REN_RENUNCIA r, RHTM_TRABAJADOR t WHERE r.ID_TRABAJADOR=t.ID_TRABAJADOR";

		sql += " and r.ID_RENUNCIA='" + idtr + "' ";

		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> correo(String idcontrato) {
		sql = "select * from REN_VIEW_RENUNCIA where ID_CONTRATO='" + idcontrato + "'";
		return jt.queryForList(sql);
	}

	// falta
	public int crearRenuncia(Renuncia r) {
		int x = 0;
		String sql = "INSERT INTO REN_RENUNCIA(ID_CONTRATO,TI_ARCHIVO,NO_ARCHIVO,FECHA_CARTA,ID_USUARIO) VALUES(?,?,?,?,?)";
		try {
			jt.update(sql, new Object[] { r.getId_contrato(), r.getTi_archivo(), r.getNo_archivo(), r.getFecha(),r.getId_usuario() });
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}

	// documentos
	public List<Map<String, Object>> mostrardocs(String id) {
		sql = "SELECT * FROM DOC_ADJUNTO WHERE IDDOCUMENTO='" + id + "' ";
		return jt.queryForList(sql);
	}

	// insertar motivos
	// @SuppressWarnings("deprecation")
	public int insertarMotivos(String[] array) {
		int x = 0;
		try {
			cn = d.getConnection();
			Array arreglo = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_MOTIVO", array);
			cs = cn.prepareCall("call REN_SP_INSERTAR_MOTIVOS( ? )");
			cs.setArray(1, arreglo);
			x = 1;

			cs.execute();
			cn.commit();
			cn.close();
		} catch (Exception e) {
			System.out.println("Error al insertar motivos " + e);
		}
		return x;
	}

	// public boolean enviarCorreo(String de, String clave, String[] para, String
	// mensaje, String asunto){
	public int enviarCorreo(String de, String clave, String para, String mensaje, String asunto) {
		int enviado = 0;
		try {

			String host = "smtp.gmail.com";

			Properties prop = System.getProperties();

			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.user", de);
//			prop.put("mail.smtp.password", clave);
			prop.put("mail.smtp.port", 587);
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
			message.setText(mensaje);

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

}
