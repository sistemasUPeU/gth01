/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author Cesar
 */
public class DocumentoDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";
    String UrlArchivo = "";//Check this variable

    public DocumentoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Id_Doc_Trab(String id_trabajador) {
        sql = "select * from RHVD_DOCUMENTO_TRABAJADOR WHERE id_trabajador=?";
        return jt.queryForList(sql, id_trabajador.trim());
    }

    public String List_files(String id) {
        sql = "select a.NO_FILE,a.NO_ORIGINAL  from RHTV_ARCHIVO_DOCUMENTO a ,  RHTV_DOCUMENTO_ADJUNTO d where d.ID_DOCUMENTO_ADJUNTO = a.ID_DOCUMENTO_ADJUNTO  and  a.id_documento_adjunto=? and a.ES_FILE='1'";
        List<Map<String, Object>> list = jt.queryForList(sql, id);
        String texto_html = "";
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> o = list.get(i);
            String tipo = ((String) o.get("NO_FILE")).substring(((String) o.get("NO_FILE")).length() - 3, ((String) o.get("NO_FILE")).length());
            if (tipo.equals("PDF") || tipo.equals("OCX")) {
                if (tipo.equals("OCX") || tipo.equals("DOC")) {
                    texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ORIGINAL")) + "' href=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "'><img src='Archivo/word.png' style='width:100px;height:100px;' class='borde'><br>" + ((String) o.get("NO_ORIGINAL")) + "</a>";
                } else {
                    texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ORIGINAL")) + "' href=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "\"><img src='Archivo/pdf.png' style='width:100px;height:100px;' class='borde'><br>" + ((String) o.get("NO_ORIGINAL")) + "</a>";
                }
            } else {
                texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ORIGINAL")) + "' href=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "\"><img src=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "\" style='width:100px;height:100px' class='borde' /></a>";
            }
        }
        return texto_html;
    }

    public String List_files_tra(String id) {
        sql = "select a.NO_FILE,a.NO_ORIGINAL  from RHTV_ARCHIVO_DOCUMENTO a, "
                + "RHTV_DOCUMENTO_ADJUNTO d where "
                + "d.ID_DOCUMENTO_ADJUNTO = a.ID_DOCUMENTO_ADJUNTO and "
                + "a.id_documento_adjunto=? and a.ES_FILE='1'";
        List<Map<String, Object>> list = jt.queryForList(sql, id);
        String texto_html = "";
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> o = list.get(i);
            texto_html = texto_html + "<img src=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "\" />";
        }
        return texto_html;
    }

    public String List_file_url(String id) {
        sql = "select a.NO_FILE,a.NO_ORIGINAL from RHTV_ARCHIVO_DOCUMENTO a, "
                + "RHTV_DOCUMENTO_ADJUNTO d where "
                + "d.ID_DOCUMENTO_ADJUNTO = a.ID_DOCUMENTO_ADJUNTO and "
                + "a.id_documento_adjunto=? and a.ES_FILE='1'";
        List<Map<String, Object>> list = jt.queryForList(sql, id);
        String texto_html = "";
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> o = list.get(i);
            String tipo = ((String) o.get("NO_FILE")).substring(((String) o.get("NO_FILE")).length() - 3, ((String) o.get("NO_FILE")).length());
            if (tipo.equals("PDF") || tipo.equals("OCX")) {
                if (tipo.equals("OCX") || tipo.equals("DOC")) {
                    texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ORIGINAL")) + "' href=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "'><img src='Archivo/word.png' style='width:100px;height:100px;' class='borde'><br>" + ((String) o.get("NO_ORIGINAL")) + "</a>";
                } else {
                    texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ORIGINAL")) + "' href=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "\"><img src='Archivo/pdf.png' style='width:100px;height:100px;' class='borde'><br>" + ((String) o.get("NO_ORIGINAL")) + "</a>";
                }
            } else {
                texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ORIGINAL")) + "' href=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "\"><img src=\"" + UrlArchivo + "Archivo/" + ((String) o.get("NO_FILE")) + "\" style='width:100px;height:100px' class='borde' /></a>";
            }
        }
        return texto_html;
    }

    public String List_file_url2(String id) {
        String texto_html = "";
        sql = "select a.NO_FILE,a.NO_ORIGINAL  from RHTV_ARCHIVO_DOCUMENTO a ,  RHTV_DOCUMENTO_ADJUNTO d where d.ID_DOCUMENTO_ADJUNTO = a.ID_DOCUMENTO_ADJUNTO  and  a.id_documento_adjunto=? and a.ES_FILE='1'";
        String sql2 = "select  count(*)  from RHTV_ARCHIVO_DOCUMENTO a ,  RHTV_DOCUMENTO_ADJUNTO d where d.ID_DOCUMENTO_ADJUNTO = a.ID_DOCUMENTO_ADJUNTO  and  a.id_documento_adjunto=? and a.ES_FILE='1'";
        int num = jt.queryForObject(sql2, Integer.class, id);
        int num2 = 0;
        List<Map<String, Object>> list = jt.queryForList(sql, id);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> o = list.get(i);
            num2 = num2 + 1;
            //String tipo = rs.getString("NO_FILE").substring(rs.getString("NO_FILE").length() - 3, rs.getString("NO_FILE").length());
            texto_html = texto_html + "<a class='mustang-gallery' title='" + o.get("NO_ORIGINAL") + "' href='" + UrlArchivo + "Archivo/" + o.get("NO_FILE") + "'>" + o.get("NO_ORIGINAL") + "</a>";
            if (num2 != num) {
                texto_html = texto_html + ",";
            }
        }
        return texto_html;
    }

    public String List_file_url3(String id) {
        sql = "SELECT NO_ARCHIVO,NO_ARCHIVO_ORIGINAL FROM RHTV_CONTRATO_ADJUNTO where  ID_CONTRATO=?";
        List<Map<String, Object>> list = jt.queryForList(sql, id.trim());
        String texto_html = "";
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> o = list.get(i);
            String tipo = ((String) o.get("NO_ARCHIVO")).substring(((String) o.get("NO_ARCHIVO")).length() - 3, ((String) o.get("NO_ARCHIVO")).length());
            if (tipo.trim().equals("PDF") || tipo.equals("OCX") || tipo.equals("DOC")) {
                if (tipo.equals("OCX") || tipo.equals("DOC")) {
                    texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ARCHIVO_ORIGINAL")) + "' href='" + UrlArchivo + "Contratos_Adjuntos/" + ((String) o.get("NO_ARCHIVO")) + "'><img src='" + UrlArchivo + "Contratos_Adjuntos/' style='width:100px;height:100px;' class='borde'><br>" + ((String) o.get("NO_ARCHIVO_ORIGINAL")) + "</a>";
                } else {
                    texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ARCHIVO_ORIGINAL")) + "' href='" + UrlArchivo + "Contratos_Adjuntos/" + ((String) o.get("NO_ARCHIVO")) + "'><img src='" + UrlArchivo + "Contratos_Adjuntos/' style='width:100px;height:100px;' class='borde'><br>" + ((String) o.get("NO_ARCHIVO_ORIGINAL")) + "</a>";
                }
            } else {

                texto_html = texto_html + "<a class='mustang-gallery' title='" + ((String) o.get("NO_ARCHIVO_ORIGINAL")) + "' href='" + UrlArchivo + "Contratos_Adjuntos/" + ((String) o.get("NO_ARCHIVO")) + "'><img src='" + UrlArchivo + "Contratos_Adjuntos/" + ((String) o.get("NO_ARCHIVO")) + "' style='width:100px;height:100px;' class='borde'></a>";
            }
        }
        return texto_html;
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> List_Documentos() {
        sql = "Select * from RHTR_DOCUMENTOS";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> List_Hijos(String id_trabajador) {
        sql = "select RHTD_DATOS_HIJO_TRABAJADOR.*, "
                + "rhfu_val_doc_hijo(ID_DATOS_HIJOS_TRABAJADOR) as "
                + "val_doc from RHTD_DATOS_HIJO_TRABAJADOR where "
                + "id_trabajador=? and "
                + "(sysdate-fe_nacimiento)/360<18 and "
                + "ES_DATOS_HIJO_TRABAJADOR='1'";
        return jt.queryForList(sql,id_trabajador.trim());
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> List_Conyugue(String id_trabajador) {
        sql = "SELECT * FROM rhtm_trabajador where "
                + "AP_NOMBRES_C is not null and "
                + "id_trabajador=?";
        return jt.queryForList(sql,id_trabajador.trim());
    }
    
    public int List_Adventista(String id_trabajador) {
        sql = "select count(li_religion) as  num from "
                + "RHTM_TRABAJADOR where li_religion = 1 and "
                + "id_trabajador=?";
        return jt.queryForObject(sql, Integer.class,id_trabajador);
    }
    
    public int List_Req_nacionalidad(String id_trabajador) {
        sql = "select count(id_nacionalidad) as num from "
                + "rhtm_trabajador where id_trabajador not in "
                + "(select id_trabajador from rhtm_trabajador where "
                + "id_nacionalidad = 'NAC-0193') and "
                + "id_trabajador=?";
        return jt.queryForObject(sql, Integer.class,id_trabajador.trim());
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> List_doc_req_pla(String iddgp, String idtra) {
        sql = "SELECT * FROM RHVD_LIST_DOC_TRA where "
                + "ID_TRABAJADOR=?";
        if (iddgp != null) {
       ///     sql += (!iddgp.equals("")) ? " and ID_DGP='" + iddgp + "' " : "";
        }
        sql += " ORDER BY nu_orden";
        return jt.queryForList(sql,idtra);
    }
    
    public void INSERT_DGP_DOC_ADJ(String ID_DGP_DOC_ADJ, 
            String ID_DGP, String ID_DOCUMENTO_ADJUNTO, 
            String ES_DGP_DOC_ADJ, String idtr, String idh) {
        jt.update("CALL RHSP_INSERT_DGP_DOC_ADJ( ?, ?, ?, ? ,?,?)",
                null,null,ID_DOCUMENTO_ADJUNTO.trim(),ES_DGP_DOC_ADJ,
                idtr,idh);
    }
    
    public String INSERT_DOCUMENTO_ADJUNTO(String ID_DOCUMENTO_ADJUNTO, 
            String ID_DOCUMENTOS, String ES_DOCUMENTO_ADJUNTO, 
            String US_CREACION, String FE_CREACION, String US_MODIF, 
            String FE_MODIF, String IP_USUARIO, 
            String DE_DOCUMENTO_ADJUNTO, String NO_USUARIO, 
            String ES_REC_FISICO, String ID_CONTRATO) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt)
                .withProcedureName("CALL RHSP_INSERT_DOCUMENTO_ADJUNTO"
                        + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_DOCUMENTO_ADJUNTO_SP", null);
        inParamMap.put("ID_DOCUMENTOS_SP", ID_DOCUMENTOS);
        inParamMap.put("ES_DOCUMENTO_ADJUNTO_SP", ES_DOCUMENTO_ADJUNTO);
        inParamMap.put("US_CREACION_SP", US_CREACION);
        inParamMap.put("FE_CREACION_SP", FE_CREACION);
        inParamMap.put("US_MODIF_SP", US_MODIF);
        inParamMap.put("FE_MODIF_SP", FE_MODIF);
        inParamMap.put("IP_USUARIO_SP", IP_USUARIO);
        inParamMap.put("DE_DOCUMENTO_ADJUNTO_SP", DE_DOCUMENTO_ADJUNTO);
        inParamMap.put("NO_USUARIO_SP", NO_USUARIO);
        inParamMap.put("ES_REC_FISICO_SP", ES_REC_FISICO);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("id_doca");
    }
    
    public void INSERT_ARCHIVO_DOCUMENTO(String ID_ARCHIVO_DOCUMENTO, 
            String ID_DOCUMENTO_ADJUNTO, String NO_FILE, 
            String NO_ORIGINAL, String ES_FILE) {
        jt.update("CALL RHSP_INSERT_ARCHIVO_DOCUMENTO( ?, ?, ?, ?, ? )",
                null,ID_DOCUMENTO_ADJUNTO.trim(),NO_FILE,NO_ORIGINAL,"1");
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> List_Doc_CE() {
        sql = " SELECT f.ti_documento, f.documento, f.es_obligatorio, "
                + "da.de_documento_adjunto, da.es_documento_adjunto, "
                + "f.id_document, da.id_documento_adjunto, "
                + "da.id_contrato, da.es_rec_fisico, da.id_dgp FROM "
                + "(SELECT dr.id_documentos AS id_document, "
                + "dr.id_tipo_planilla AS id_tipo_plani, "
                + "dr.id_requerimiento AS id_requerimient, "
                + "dr.documento, dr.ti_planilla, dr.planilla AS "
                + "planilla, dr.id_requerimiento, dr.TI_DOCUMENTO, "
                + "dr.es_obligatorio FROM rhvd_doc_plant_req dr, "
                + "RHTR_REQUERIMIENTO r WHERE dr.id_requerimiento = "
                + "r.id_requerimiento AND dr.ID_REQUERIMIENTO = "
                + "'REQ-0019' )f LEFT OUTER JOIN (SELECT g.*, "
                + "dd.id_dgp, dd.id_contrato FROM "
                + "rhtv_documento_adjunto g, RHTV_DGP_DOC_ADJ dd WHERE "
                + "dd.id_documento_adjunto=g.id_documento_adjunto) da "
                + "ON (f.id_document = da.id_documentos )";
        return jt.queryForList(sql);
    }
    
    public int countDocumentsByIdTrabajador(String id) {
        sql = "SELECT count(*) FROM RHVD_REQ_DGP_TRA where "
                + "ID_TRABAJADOR=? AND ID_DOCUMENTO_ADJUNTO IS NULL";
        return jt.queryForObject(sql, Integer.class, id);
    }
    
    public void Desactivar_doc(String id_doc_adj) {
        jt.update("CALL RHSP_DESAC_DOC_ADJ(?)", id_doc_adj.trim());
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> List_doc_tra(String idtra) {
        sql = "SELECT * FROM RHVD_REQ_DGP_TRA  where ID_TRABAJADOR=?";
        return jt.queryForList(sql,idtra);
    }
    
    public int count_documentos_x_tra(String id_tra) {
        sql = "SELECT count(*) FROM RHTV_DGP_DOC_ADJ where "
                + "ID_TRABAJADOR=? and ES_DGP_DOC_ADJ='1'";
        return jt.queryForObject(sql, Integer.class, id_tra.trim());
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> Documentos() {
        sql = "SELECT ID_DOCUMENTOS,NO_DOCUMENTO, ES_DOCUMENTO, "
                + "TI_DOCUMENTO,ES_OBLIGATORIO FROM RHTR_DOCUMENTOS "
                + "where ES_DOCUMENTO='1'";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> Lis_doc_trabajador(String idtr) {
        sql = "SELECT * FROM RHVD_LIST_DOC_TRA  where "
                + "ID_TRABAJADOR=? ORDER BY NU_ORDEN";
        return jt.queryForList(sql,idtr.trim());
    }
    
    public void INSERT_DGP_DOC_tra(String ID_DGP_DOC_ADJ, String ID_DGP, String ID_DOCUMENTO_ADJUNTO, String ES_DGP_DOC_ADJ, String idtr, String idh) {
        jt.update("CALL RHSP_INSERT_DGP_DOC_ADJ( ?, ?, ?, ? ,?,?)", 
                null,null,ID_DOCUMENTO_ADJUNTO.trim(),ES_DGP_DOC_ADJ,
                idtr.trim(),idh);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String,Object>> Lis_doc_trabajador_hab(String idtr) {
        sql = "SELECT * FROM RHVD_LIST_DOC_TRA WHERE "
                + "ID_DOCUMENTO_ADJUNTO IS NOT NULL AND "
                + "ID_TRABAJADOR=?";
        return jt.queryForList(sql,idtr.trim());
    }
    
    
}
