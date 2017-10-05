package pe.edu.upeu.gth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import pe.edu.upeu.gth.dao.AreaDAO;
import pe.edu.upeu.gth.dao.AutorizacionDAO;
import pe.edu.upeu.gth.dao.CUniversitarioDAO;
import pe.edu.upeu.gth.dao.Carga_AcademicaDAO;
import pe.edu.upeu.gth.dao.Carrera_UniversidadDAO;
import pe.edu.upeu.gth.dao.CentroCostoDAO;
import pe.edu.upeu.gth.dao.Comentario_DGPDAO;
import pe.edu.upeu.gth.dao.ContratoDAO;
//import pe.edu.upeu.gth.dao.DepartamentoDAO;
import pe.edu.upeu.gth.dao.DetalleReqProcesoDAO;
import pe.edu.upeu.gth.dao.Detalle_Centro_Costo_DAO;
import pe.edu.upeu.gth.dao.Detalle_PrivilegioDAO;
import pe.edu.upeu.gth.dao.DgpDAO;
import pe.edu.upeu.gth.dao.DireccionDAO;
import pe.edu.upeu.gth.dao.DocumentoDAO;
import pe.edu.upeu.gth.dao.EmpleadoDAO;
import pe.edu.upeu.gth.dao.Formato_HorarioDAO;
import pe.edu.upeu.gth.dao.Fotos_TrabajadorDAO;
import pe.edu.upeu.gth.dao.FuncionDAO;
import pe.edu.upeu.gth.dao.GrupoOcupacionesDAO;
import pe.edu.upeu.gth.dao.Hist_Estado_CivilDAO;
import pe.edu.upeu.gth.dao.HorarioDAO;
import pe.edu.upeu.gth.dao.ImagenDAO;
import pe.edu.upeu.gth.dao.ModuloDAO;
import pe.edu.upeu.gth.dao.NacionalidadDAO;
import pe.edu.upeu.gth.dao.NotificationDAO;
import pe.edu.upeu.gth.dao.Padre_Madre_ConyugeDAO;
import pe.edu.upeu.gth.dao.PagoDocenteDAO;
import pe.edu.upeu.gth.dao.PasoDAO;
import pe.edu.upeu.gth.dao.Periodo_PagoDAO;
import pe.edu.upeu.gth.dao.PlantillaContractualDAO;
import pe.edu.upeu.gth.dao.PlantillaDAO;
import pe.edu.upeu.gth.dao.Plazo_DgpDAO;
import pe.edu.upeu.gth.dao.PresupuestoDAO;
import pe.edu.upeu.gth.dao.PrivilegioDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dao.SeccionDAO;
import pe.edu.upeu.gth.dao.UsuarioDAO;
import pe.edu.upeu.gth.properties.globalProperties;

import java.io.IOException;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "pe.edu.upeu.gth")
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		super.addResourceHandlers(registry);
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@" + globalProperties.HOSTNAME + ":" + globalProperties.PORT + ":" + globalProperties.SID);
        dataSource.setUsername(globalProperties.USER);
        dataSource.setPassword(globalProperties.USER_PWD);
        return dataSource;
    }
    
//    CONFIGURACION DE MULTIPARTRESOLVER PARA TRABAJAR CONN ARCHUIVOS--
    /*@Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // no limit
        resolver.setMaxUploadSize(500000);
        
        return resolver;
    }*/
	
	
	
}
