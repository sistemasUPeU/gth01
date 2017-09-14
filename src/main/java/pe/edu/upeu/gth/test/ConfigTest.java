package pe.edu.upeu.gth.test;

import javax.sql.DataSource;

import pe.edu.upeu.gth.config.AppConfig;

public class ConfigTest {

	public static DataSource d = AppConfig.getDataSource();

	public static void main(String[] args) {
		conect();
	}

	public static void conect() {
		DataSource d = AppConfig.getDataSource();

		if (d != null) {
			System.out.println("Conectado");
		} else {
			System.out.println("No se pudo conectar");
		}
	}

}
