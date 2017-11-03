package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class PuestoDAO {
	private JdbcTemplate jt;
	private String sql = "";

	public PuestoDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> List_Puesto() {
		sql = "select id_puesto,no_puesto,no_corto_pu,es_puesto,id_seccion,co_grupo from rhtr_puesto ORDER BY no_puesto ASC";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> List_Puesto_Dep(String id_departamento) {
		sql = "select  * from RHVD_PUESTO_DIRECCION where id_departamento='" + id_departamento.trim()
				+ "' and ES_PUESTO='1' ORDER BY no_puesto,no_seccion,no_area ASC";
		return jt.queryForList(id_departamento.trim(), sql);
	}

	public List<Map<String, Object>> List_Dep_Puesto() {
		sql = "select  * from RHVD_PUESTO_DIRECCION ";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> Lis_Id_Puesto(String id_puesto) {
		sql = "Select * from rhtr_puesto where id_puesto = '" + id_puesto + "' ";
		return jt.queryForList(id_puesto, sql);
	}

	public boolean Modif_Puesto(String id_puesto, String no_puesto, String no_corto_pu, String es_puesto,
			String id_seccion, String co_grupo) {
		boolean x = false;
		int i = 0;
		i = jt.update("CALL rhtr_puesto(?, ?; ?; ?; ?; ?)", id_puesto, no_puesto, no_corto_pu, es_puesto, id_seccion,
				co_grupo);
		if (i == 1) {
			x = true;
		}
		return x;
	}

	public List<Map<String, Object>> Listar_Puesto_Id(String id) {
		sql = "select  *  from rhtr_puesto where id_seccion='" + id.trim() + "' order by no_puesto";
		return jt.queryForList(id, sql);
	}

	public List<Map<String, Object>> Listar_Puesto_id_es(String id) {
		sql = "select  *  from rhtr_puesto where id_seccion='" + id.trim() + "' AND ES_PUESTO='1' order by no_puesto";
		return jt.queryForList(id, sql);
	}

	public List<Map<String, Object>> List_puesto() {
		sql = "select  *  from rhtr_puesto order by no_puesto";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> List_Puesto_x_iddgp(String id_dgp) {
		sql = "SELECT v.ID_DIRECCION FROM RHVD_PUESTO_DIRECCION v where v.ID_PUESTO=(SELECT d.ID_PUESTO FROM RHTM_DGP d where d.id_dgp='"
				+ id_dgp.trim() + "')";
		return jt.queryForList(id_dgp.trim(), sql);
	}

	public List<Map<String, Object>> List_Puesto_Lima() {
		sql = "SELECT NO_PUESTO FROM RHVD_PUESTO_DIRECCION where ID_DIRECCION='DIR-0002' OR ID_DIRECCION='DIR-0001' OR ID_DIRECCION='DIR-0003' OR ID_DIRECCION='DIR-0004' GROUP BY NO_PUESTO ORDER by NO_PUESTO";
		return jt.queryForList(sql);
	}

	public boolean Registrar_Puesto_Paso(String ID_DETALLE_PASOS, String ID_PASOS, String ID_PUESTO,
			String ES_DETALLE_PASOS, String CO_PUESTO) {
		boolean x = false;
		int i = 0;
		i = jt.update("CALL RHSP_INSERT_DETALLE_PASOS( ?, ?, ?, ?, ? )", ID_DETALLE_PASOS, ID_PASOS, ID_PUESTO,
				ES_DETALLE_PASOS, CO_PUESTO);
		if (i == 1) {
			x = true;
		}
		return x;
	}
}
