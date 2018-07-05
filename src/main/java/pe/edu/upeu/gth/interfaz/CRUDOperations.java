package pe.edu.upeu.gth.interfaz;
import java.util.ArrayList;
import java.util.Map;

public interface CRUDOperations {
	
	public ArrayList<Map<String, Object>> listar();

    public boolean add(Object o);

    public boolean edit(Object o);

    public boolean delete(Object o);

}
