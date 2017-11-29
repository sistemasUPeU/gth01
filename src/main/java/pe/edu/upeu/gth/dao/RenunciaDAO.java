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
		sql = "SELECT DISTINCT TO_CHAR(MIN(ren.FECHA_REGISTRO),'MM') AS MES_ACTUAL,con.ID_CONTRATO,tra.NO_TRABAJADOR,tra.AP_PATERNO, tra.AP_MATERNO,TO_CHAR(MIN(tra.FE_NAC),'DD/MM/YYYY') as FECHA_NAC,tra.DI_DOM_A_D6, tra.NU_DOC, \r\n" + 
				"TO_CHAR(MIN(con.FE_DESDE),'DD/MM/YYYY') AS FECHA_CONTRATO,dep.NO_DEP, area.NO_AREA,sec.NO_SECCION,pues.NO_PUESTO,cenc.DE_CENTRO_COSTO,\r\n" + 
				"tipocon.DE_TI_CONTRATO,dgp.DE_ANTECEDENTES_POLICIALES,dgp.ES_CERTIFICADO_SALUD,con.ES_MFL, ren.NO_ARCHIVO,ren.FECHA_REGISTRO, con.ID_TRABAJADOR\r\n" + 
				"FROM REN_RENUNCIA ren\r\n" + 
				"LEFT JOIN RHTM_CONTRATO con ON ren.ID_CONTRATO=con.ID_CONTRATO\r\n" + 
				"LEFT JOIN RHTX_TIPO_CONTRATO tipocon ON con.TI_CONTRATO=tipocon.ID_TIPO_CONTRATO\r\n" + 
				"LEFT JOIN  RHTR_PUESTO pues ON con.ID_PUESTO=pues.ID_PUESTO \r\n" + 
				"LEFT JOIN RHTR_SECCION sec ON pues.ID_SECCION=sec.ID_SECCION\r\n" + 
				"LEFT JOIN RHTD_AREA area ON sec.ID_AREA=area.ID_AREA\r\n" + 
				"LEFT JOIN RHTX_DEPARTAMENTO dep ON area.ID_DEPARTAMENTO=dep.ID_DEPARTAMENTO\r\n" + 
				"LEFT JOIN RHTM_DGP dgp ON con.ID_DGP=dgp.ID_DGP \r\n" + 
				"LEFT JOIN RHTD_DETALLE_CENTRO_COSTO dcencos ON dgp.ID_DGP=dcencos.ID_DGP \r\n" + 
				"LEFT JOIN RHTR_CENTRO_COSTO cenc ON dcencos.ID_CENTRO_COSTO=cenc.ID_CENTRO_COSTO\r\n" + 
				"LEFT JOIN RHTM_TRABAJADOR tra ON  con.ID_TRABAJADOR=tra.ID_TRABAJADOR\r\n" + 
				"LEFT JOIN RHTX_REGIMEN_LABORAL reg ON con.ID_REGIMEN_LABORAL=reg.ID_REGIMEN_LABORAL\r\n" + 
				"LEFT JOIN RHTX_SUB_MODALIDAD sub ON con.ID_SUB_MODALIDAD=sub.ID_SUB_MODALIDAD\r\n" + 
				"LEFT JOIN RHTX_GRUPO_OCUPACION gr ON con.ID_GRUPO_OCUPACION=gr.ID_GRUPO_OCUPACION \r\n" + 
				"LEFT JOIN RHTR_TIPO_PLANILLA tipopla ON con.ID_TIPO_PLANILLA=tipopla.ID_TIPO_PLANILLA\r\n" + 
				"LEFT JOIN RHTD_DETALLE_HORARIO dethor ON con.ID_DETALLE_HORARIO=dethor.ID_DETALLE_HORARIO\r\n" + 
				"LEFT JOIN RHTC_PLANTILLA_CONTRACTUAL placon ON con.ID_PLANTILLA_CONTRACTUAL=placon.ID_PLANTILLA_CONTRACTUAL\r\n" + 
				"LEFT JOIN RHTR_SITUACION_ESPECIAL sitesp ON con.ID_SITUACION_ESPECIAL=sitesp.ID_SITUACION_ESPECIAL\r\n" + 
				"GROUP BY con.ID_CONTRATO,tra.NO_TRABAJADOR,tra.AP_PATERNO, tra.AP_MATERNO,TO_CHAR(tra.FE_NAC,'DD/MM/YYYY'),tra.DI_DOM_A_D6, tra.NU_DOC,dep.NO_DEP ,\r\n" + 
				"area.NO_AREA,sec.NO_SECCION,pues.NO_PUESTO,cenc.DE_CENTRO_COSTO,tipocon.DE_TI_CONTRATO\r\n" + 
				", dgp.DE_ANTECEDENTES_POLICIALES,dgp.ES_CERTIFICADO_SALUD,con.ES_MFL,ren.NO_ARCHIVO,ren.FECHA_REGISTRO, con.ID_TRABAJADOR\r\n" + 
				"ORDER BY FECHA_CONTRATO";
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

	
	//falta
	public int crearRenuncia(Renuncia r) {
		int x = 0;
		String sql = "INSERT INTO REN_RENUNCIA(ID_CONTRATO,TI_ARCHIVO,NO_ARCHIVO,FECHA_CARTA) VALUES(?,?,?,?)";
		try {
			jt.update(sql, new Object[] { r.getId_contrato(),r.getTi_archivo(),r.getNo_archivo(),r.getFecha()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	
	//documentos
	public List<Map<String, Object>> mostrardocs(String id) {
		sql = "SELECT * FROM DOC_ADJUNTO WHERE IDDOCUMENTO='" + id + "' ";
		return jt.queryForList(sql);
	}
	
	//insertar motivos
//		@SuppressWarnings("deprecation")
		public int  insertarMotivos(String[] array){
			int x =0;
			try {
				cn = d.getConnection();
				Array arreglo = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_MOTIVO", array);
				cs= cn.prepareCall("call REN_SP_INSERTAR_MOTIVOS( ? )");
				cs.setArray(1, arreglo);
					x=1;
				
				cs.execute();
				cn.commit();
				cn.close();
			} catch (Exception e) {
				System.out.println("Error al insertar motivos "+e);
			}
			return x;
		}
		
		
}
