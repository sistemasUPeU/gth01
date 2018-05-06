/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.io.File;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.Renuncia;

/**
 *
 * @author Nicole
 */
public class RenunciaDAO {
	//comentando..
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
	
	// LISTA A TODOS LOS EMPLEADOS POR DEPARTAMENTO
	public ArrayList<Map<String, Object>> listarEmpleados() { 
		sql = "select rt.ID_TRABAJADOR,rt.NO_TRABAJADOR,rt.AP_PATERNO,rt.AP_MATERNO, rt.NU_DOC  ,ra.NO_AREA,rs.NO_SECCION,rp.NO_PUESTO,rc.FE_DESDE,rc.FE_HASTA "
				+ "from rhtr_puesto rp, rhtd_area ra, rhtr_seccion rs, rhtm_trabajador rt, rhtm_contrato rc,rhtx_departamento rd "
				+ "where rp.ID_SECCION = rs.ID_SECCION and rs.ID_AREA = ra.ID_AREA and rc.ID_PUESTO = rp.ID_PUESTO  and rc.ID_TRABAJADOR = rt.ID_TRABAJADOR and rd.ID_DEPARTAMENTO = ra.ID_DEPARTAMENTO "
				+ "       and rd.ID_DEPARTAMENTO='DPT-0017' " + "AND RC.FE_HASTA > SYSDATE " + "ORDER BY ( RA.NO_AREA)";
		return (ArrayList<Map<String, Object>>) jt.queryForList(sql);
	}
	
	// LISTA DETALLE DEL TRABAJADOR
	public ArrayList<Map<String, Object>> DetalleEmp(String idTR) { 
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

	// ESTO LISTA EL DETALLE DEL TRABAJADOR FILTRADO POR DNI EN LA INTERFAZ "REGISTRAR RENUNCIA"
	public List<Map<String, Object>> Buscar_DetalleTrabajador(String dni, String depa) {
		sql = "select ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD FROM REN_VIEW_TRABAJADOR";

		sql += " where DNI='" + dni + "' and NOM_DEPA='"+depa+"'";

		return jt.queryForList(sql);
	}
	
    // LISTAR TODOS LOS TRABAJADORES CON ESTADO PROCESADO
	public List<Map<String, Object>> gg(String depa)  {
	     sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='0' AND rap.ID_PASOS='PAS-000438' OR rap.ID_PASOS='PAS-000439'";	
	sql +="and NOM_DEPA='"+depa+"' AND ESTADO='0' ORDER BY ra.FECHA_RENABAN DESC";
	return jt.queryForList(sql);
	}
	
    // LISTAR TODOS LOS TRABAJADORES CON ESTADO NOTIFICADO
	public List<Map<String, Object>> listarNotificados() {
		sql = "select * from RA_VIEW_RENABAN WHERE ESTADO='Notificado'";
		return jt.queryForList(sql);
	}

	// LISTAR LOS MOTIVOS DE LA RENUNCIA
	public List<Map<String, Object>> mostrarMotivo() {
		sql = "SELECT* FROM RA_MOTIVO";
		return jt.queryForList(sql);
	}
	
	//LISTAR RENABAN - CRUD 
	public List<Map<String, Object>> Renaban(String depa) {
		sql = "select ra.ID_CONTRATO as ID_CONTRATO,ra.ID_RENABAN as ID_RENABAN,ra.ID_TRABAJADOR as ID_TRABAJADOR, ra.MES as MES,";
	    sql +="ra.NOMBRES as NOMBRES,ra.PATERNO as PATERNO, ra.MATERNO as MATERNO, ra.FECHA_NAC as FECHA_NAC, ra.DOMICILIO as DOMICILIO,";
	    sql += "ra.DNI as DNI, ra.FECHA_CONTRATO as FECHA_CONTRATO, ra.NOM_DEPA as NOM_DEPA, ra.NOM_AREA as NOM_AREA,ra.NOM_SECCION as NOM_SECCION,";
	    sql += "ra.NOM_PUESTO as NOM_PUESTO, ra.CENTRO_COSTO as CENTRO_COSTO,ra.TIPO_CONTRATO as TIPO_CONTRATO,ra.DESCRIPCION as DESCRIPCION,";
	    sql += "ra.ANTECEDENTES as ANTECEDENTES,ra.CERTI_SALUD as CERTI_SALUD,ra.MFL as MFL, ra.ARCHIVO as ARCHIVO,ra.FECHA_RENABAN as FECHA_RENABAN,";
	    sql += "ra.CORREO as CORREO, ra.TIPO as TIPO,jus.OBSERVACIONES as JUSTIFICACION, re.OBSERVACIONES as RECHAZO";
		sql += " from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN  ";
		sql += " LEFT JOIN RA_JUSTIFICACION jus ON ra.ID_RENABAN=jus.ID_RENABAN ";
		sql += " LEFT JOIN RA_RECHAZO re ON ra.ID_RENABAN=re.ID_RENABAN ";
		sql += " WHERE rap.ID_PASOS='PAS-000428' OR rap.ID_PASOS='PAS-000429' ";
		sql +=" and NOM_DEPA='"+depa+"' ORDER BY ra.FECHA_RENABAN DESC";
		return jt.queryForList(sql);
	}
	
	//ELIMINAR RENABAN - CRUD
	public int eliminarRenaban(String id) {
		int x = 0;
		String sql = "DELETE FROM RA_RENABAN WHERE ID_RENABAN=?";
		try {
			jt.update(sql, new Object[] { id});
			x= 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
   //  CARGAR MOTIVOS DE RENUNCIA
	public List<Map<String, Object>> cargarMotivo(String idtr) {
		sql = "SELECT * FROM RA_RENABAN r, RHTM_CONTRATO t WHERE r.ID_CONTRATO=t.ID_CONTRATO";

		sql += " and r.ID_CONTRATO='" + idtr + "' ";

		return jt.queryForList(sql);
	}
   //
	public List<Map<String, Object>> correo(String idcontrato) {
		sql = "select * from RA_VIEW_RENABAN where ID_CONTRATO='" + idcontrato + "'";
		return jt.queryForList(sql);
	}

	// INSERTAR RENUNCIA
	public int crearRenuncia(Renuncia r) {
		int x = 0;
		String sql = "INSERT INTO RA_RENABAN(ID_CONTRATO,TI_ARCHIVO,NO_ARCHIVO,FECHA_CARTA,ID_USUARIO,TIPO) VALUES(?,?,?,?,?,?)";
		try {
			jt.update(sql, new Object[] { r.getId_contrato(), r.getTi_archivo(), r.getNo_archivo(), r.getFecha(),r.getId_usuario(),r.getTipo() });
			
			x= 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	//BUSCAR RENABAN
	public List<Map<String, Object>> buscarRenaban(String idrenaban) {
		sql = "select * from RA_VIEW_RENABAN where ID_RENABAN='" + idrenaban + "'";
		return jt.queryForList(sql);
	}
	
	//ACTUALIZAR RENUNCIA
	public int actualizarRenuncia(Renuncia r) {
		int x = 0;
		String sql = "UPDATE RA_RENABAN SET TI_ARCHIVO=?,NO_ARCHIVO=?,FECHA_CARTA=?,USU_MOD=?,TIPO =? WHERE ID_RENABAN =?";
		try {
			jt.update(sql, new Object[] { r.getTi_archivo(), r.getNo_archivo(), r.getFecha(),r.getUsu_mod(),r.getTipo() });
			
			x= 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}

	// DOCUMENTOS
	public List<Map<String, Object>> mostrardocs(String id) {
		sql = "SELECT * FROM DOC_ADJUNTO WHERE IDDOCUMENTO='" + id + "' ";
		return jt.queryForList(sql);
	}

	// INSERTAR MOTIVOS
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
			int port = 587;

			Properties prop = System.getProperties();
			String[] parts = de.split("@");
			String part1 = parts[0]; // 123
			String tipo = parts[1]; // 654321
			
			if (tipo.equals("live.com")|| tipo.equals("hotmail.com")||tipo.equals("outlook.com")) {
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
	
	public int notificarRenuncia(Renuncia r, String idusuario,String tipo1) {
		int x = 0;
//		String sql = "INSERT INTO RA_RENABAN_PASOS(ID_RENABAN,ID_PASOS,ID_USUARIO,FECHA_MOD) VALUES(?,?,?,?)";
		String sql2 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1 WHERE ID_PASOS='PAS-000438' AND ID_RENABAN=?";
		String sql3 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1 WHERE ID_PASOS='PAS-000439' AND ID_RENABAN=?";
//		Date date = new Date();
//		
//		//obtenerhora y fecha y salida por pantalla con formato:
//		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		Date fechon = new java.sql.Date(System.currentTimeMillis());
		System.out.println(fechon);
		try {
			if(tipo1.equals("R")) {
				jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000432",idusuario,fechon});
				jt.update(sql2,new Object[] { r.getId_renuncia()});
			}else {
				jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000433",idusuario,fechon});
				jt.update(sql3,new Object[] { r.getId_renuncia()});
			}
			
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	public List<Map<String, Object>> graficoMotivos(String fecha1,String fecha2) {
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			cn = d.getConnection();
			
			cs = cn.prepareCall("call REN_SPGRAFICO_MOTIVOS(?,?)");
			cs.setString(1,fecha1);
			cs.setString(2,fecha2);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeUpdate();
			rs=(ResultSet) cs.getObject(3);
			while(rs.next()) {
				map= new HashMap<String, Object>();
				map.put("MOTIVO", rs.getString(1));
				map.put("NUMERO", rs.getString(2));
				lista.add(map);
			}
			
			cn.commit();
			cn.close();
		} catch (Exception e) {
			System.out.println("Error al insertar motivos " + e);
		}
		return lista;
	}
	
	public List<Map<String, Object>> graficoMotivos2(String fecha1,String fecha2) {
		String sql = "SELECT DISTINCT m.NO_MOTIVO as motivo,count(dm.ID_MOTIVO) as numero\r\n" + 
				"FROM REN_MOTIVO m \r\n" + 
				"LEFT JOIN REN_DETALLE_MOTIVO dm ON dm.ID_MOTIVO=m.ID_MOTIVO \r\n" + 
				"LEFT JOIN REN_RENUNCIA r ON r.ID_RENUNCIA=dm.ID_RENUNCIA\r\n" + 
				"WHERE r.FECHA_REGISTRO BETWEEN TO_DATE('"+fecha1+"','DD/MM/YYYY') AND TO_DATE('"+fecha2+"','DD/MM/YYYY') \r\n" + 
				"GROUP BY  m.NO_MOTIVO";
		return jt.queryForList(sql);
	}
	
	

}
