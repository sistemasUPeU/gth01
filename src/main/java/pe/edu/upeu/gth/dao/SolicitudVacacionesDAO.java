package pe.edu.upeu.gth.dao;

import java.io.File;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import oracle.jdbc.OracleConnection;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.ProductOrder;

public class SolicitudVacacionesDAO {
	private JdbcTemplate jt;

	DataSource d = AppConfig.getDataSource();
	Connection cn;
	
	
	@Autowired
	JavaMailSender mailSender;

	public SolicitudVacacionesDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

public Map<String, Object> llenar_solicitud(String idtrabajador, String fechainicio1, ServletContext cntx, HttpServletResponse response) {
		
//		String jasperFile ="C:\\Users\\COTA\\git\\gth01\\src\\main\\resources\\jasperreports\\request_report.jrxml";
		String jasperFile = cntx.getRealPath("/jasperreports/request_report.jrxml" );
		Map<String, Object> OutValues = new HashMap<>();
		 String outfilePDF ="";
		 String outFoler ="";
		 
		 
	    try {
	    	// Cargamos el driver JDBC
	    		JRDataSource datasource = new JREmptyDataSource();
	    	
//	    	cn=(Connection) jt.getDataSource();
	    	 Class.forName("oracle.jdbc.OracleDriver");
//	    	 cn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.21.9:1521:XE","gth", "123");
	    	 cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","gth", "123");
	    	 cn.setAutoCommit(false);
	    	 
	    	 String realPath = cntx.getRealPath("/resources/img/");
		       	
		    	Map<String,Object> Inparamets = new HashMap<String,Object>();
				
				Inparamets.put("txtidtrab", idtrabajador);
				Inparamets.put("txtfecha1", fechainicio1);
//				Inparamets.put("txtIdVacante", idvacante);
				Inparamets.put("realPath", realPath);
				System.out.println(Inparamets);
		      
				JasperReport report = JasperCompileManager.compileReport(jasperFile);
				JasperPrint print = JasperFillManager.fillReport(report, Inparamets, cn);
				System.out.println(report);
				System.out.println(print);
				//generar Carpeta 
				
//				    outFoler ="C:\\Users\\Cesar\\Documents\\ALPHA PROJECTS\\PPP\\new - ppp\\ppp\\ppp\\src\\main\\webapp\\Portafolios\\FolderPPP\\"+codigo;
//				   outFoler = "C:\\Users\\COTA\\git\\gth01\\src\\main\\webapp\\resources\\files\\solicitud\\" + idtrabajador;
				   outFoler = cntx.getRealPath("/resources/files/solicitud/" + idtrabajador );
			       File outDir = new File(outFoler);
			       System.out.println("existe o no "+outDir.exists());
			       if (outDir.exists() == false) { 
		    	       outDir.mkdirs();
			    	}
			       outfilePDF = cntx.getRealPath("/resources/files/solicitud/" + idtrabajador+"/SVP_"+idtrabajador+".pdf" );
//			        ="C:\\Users\\COTA\\git\\gth01\\src\\main\\webapp\\resources\\files\\solicitud\\" + idtrabajador+"\\SVP_"+idtrabajador+".pdf";
//			       outfilePDF ="C:\\Users\\Cesar\\Documents\\ALPHA PROJECTS\\PPP\\new - ppp\\ppp\\ppp\\src\\main\\webapp\\Portafolios\\FolderPPP\\"+codigo+"\\CartP-"+codigo+"vcn-"+idvacante+".pdf";
			       System.out.println("existe ?¡:"+outDir.exists());
			       
				 // Exporta el informe a PDF
				 JasperExportManager.exportReportToPdfFile(print,outfilePDF);
				 System.out.println("respesta dao 1: " + response.getOutputStream());
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        if (cn != null) {
	        	cn.rollback();
	          System.out.println("ROLLBACK EJECUTADO");
	          cn.close();
	        }
	      }catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	    OutValues.put("folder", outFoler);
		OutValues.put("pdf", outfilePDF);
		System.out.println("outvalues: " + OutValues);
		return OutValues;
		
		
		
//		List<Map<String, Object>> lista = new ArrayList<>();
//		try {
//			String sql = "SELECT a.ID_TRABAJADOR_FILTRADO, a.ID_CONSOLIDADO, b.ID_TRABAJADOR, b.AP_PATERNO, b.AP_MATERNO, b.NO_TRABAJADOR,b.NU_DOC , c.ID_VACACIONES, c.TIPO, c.ESTADO,\r\n"
//					+ "d.ID_DET_VACACIONES, d.FECHA_INICIO, d.FECHA_FIN, D.ESTADO\r\n"
//					+ "FROM RHMV_TRABAJADOR_FILTRADO A\r\n"
//					+ "JOIN RHTM_TRABAJADOR B ON A.ID_TRABAJADOR = B.ID_TRABAJADOR\r\n"
//					+ "JOIN RHMV_VACACIONES C  ON C.ID_TRABAJADOR_FILTRADO = A.ID_TRABAJADOR_FILTRADO\r\n"
//					+ "JOIN RHMV_DET_VACACIONES D ON C.ID_VACACIONES = D.ID_VACACIONES\r\n" + "AND C.ESTADO = 1\r\n"
//					+ "AND B.ID_TRABAJADOR = '" + idt + "'";
//
//			lista = jt.queryForList(sql);
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Error - dao:" + e);
//		}
//
//		return lista;
	}

	public int trabajador_filtrado(String id) {
		int x;
		try {
			String sql = "select * from rhmv_trabajador_filtrado a where a.ID_TRABAJADOR= '" + id + "'";
			jt.queryForList(sql);
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error - dao - soli:" + e);
			x = 0;
		}
		return x;
	}

	public int validarTipoSolicitud(String codigo) {
		int i = 0;
		try {
			DataSource d = AppConfig.getDataSource();
			CallableStatement cst = d.getConnection().prepareCall("{call RHSP_VALIDAR_SOLICITUD1 (?,?)}");
			cst.setString(1, codigo);
			cst.registerOutParameter(2, Types.NUMERIC);
			cst.execute();
			System.out.println(cst.getInt(2));

			i = cst.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public String insertarSolicitud(String[] inicio, String[] fin, String idt, String tipo, String user) {

		String i = "";
		try {

			cn = d.getConnection();
			Array array_inicio = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_FE_INICIO", inicio);
			Array array_fin = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_FE_FINAL", fin);

			CallableStatement cst = d.getConnection().prepareCall("{call RHSP_VAC_INSERT_VACACIONES (?,?,?,?,?,?)}");
			cst.setArray(1, array_inicio);
			cst.setArray(2, array_fin);
			cst.setString(3, idt);
			cst.setString(4, tipo);
			cst.setString(5, user);
			cst.registerOutParameter(6, Types.CHAR);
			cst.execute();
			System.out.println("dao: "+cst.getString(6).trim());
			// cn.commit();
			cn.close();

			i = cst.getString(6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int subirDocumento(String nombre, String tipo, String url, String idvac) {
		int x = 0;

		String sql = "UPDATE RHMV_VACACIONES SET URL=? WHERE ID_VACACIONES =?";
		try {
			jt.update(sql, url, idvac);
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	public int subirPapeleta(String nombre, String tipo, String url, String idvac) {
		String sql = "UPDATE RHMV_DET_VACACIONES SET URL= ? WHERE ID_DET_VACACIONES = ?";
		try {
			jt.update(sql, url, idvac);
			return 1;
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return 0;
		}
	}

	public List<Map<String, Object>> reportePorDepartamento(String fecha1, String fecha2) {

		String sql = "SELECT COUNT(C.NO_DEP)AS NRO, D.NO_DEP  FROM (\r\n" + 
				"SELECT B.NO_DEP, TO_CHAR(A.FECHA_CREACION, 'DD/MM/YYYY') FROM RHMV_VACACIONES A \r\n" + 
				"JOIN RHMV_TRABAJADOR_FILTRADO B ON A.ID_TRABAJADOR_FILTRADO = B.ID_TRABAJADOR_FILTRADO\r\n" + 
				"WHERE TO_CHAR(A.FECHA_CREACION, 'DD/MM/YYYY') BETWEEN TO_DATE(?, 'DD/MM/YYYY') AND TO_DATE(?, 'DD/MM/YY')) C \r\n" + 
				"RIGHT JOIN RHTX_DEPARTAMENTO D\r\n" + 
				"ON C.NO_DEP = D.NO_DEP\r\n" + 
				"GROUP BY D.NO_DEP\r\n" + 
				"ORDER BY NRO DESC";

		return jt.queryForList(sql, fecha1, fecha2);

	}
	public List<Map<String, Object>> reportePorMeses() {

		String sql = "";

		return jt.queryForList(sql);

	}



	public void sendEmail(Object object) {

		ProductOrder order = (ProductOrder) object;

		MimeMessagePreparator preparator = getMessagePreparator(order);

		try {
			mailSender.send(preparator);
			System.out.println("Message Send...Hurrey");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator(final ProductOrder order) {

//		MimeMessagePreparator preparator = new MimeMessagePreparator()
//		{
//			public void prepare(MimeMessage mimeMessage) throws Exception {
//				mimeMessage.setFrom("harolcotac@gmail.com");
//				mimeMessage.setRecipient(Message.RecipientType.TO,
//						new InternetAddress(order.getCustomerInfo().getEmail()));
//				mimeMessage.setText("Dear " + order.getCustomerInfo().getName()
//						+ ", thank you for placing order. Your order id is " + order.getOrderId() + ".");
//				mimeMessage.setSubject("Your order on Demoapp");
//				System.out.println("mime> " + mimeMessage);
//			}
//		};

		
		MimeMessagePreparator preparator1 = new MimeMessagePreparator() {
			   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setFrom("harolcotac@gmail.com");
			     message.setTo("harolcotac@gmail.com");
			     message.setSubject("mi aplicacion2");
			     message.setText("my hiiii i am testing, please see it", true);
//			     message.addInline("myLogo", new ClassPathResource("img/mylogo.gif"));
//			     message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
			   }
			 };
		System.out.println("prepa>" +preparator1);
		
		return preparator1;
		

	}
}
