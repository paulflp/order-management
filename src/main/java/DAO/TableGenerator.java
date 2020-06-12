package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JTable;

/**
 * Clasa pentru generarea unui JTable in functie de o lista de obiecte
 * 
 * @author user
 *
 * @param <T>
 *            Clasa obiectelor ce vor popula tabelul
 */
public class TableGenerator<T> {
	protected static final Logger LOGGER = Logger.getLogger(TableGenerator.class.getName());

	public TableGenerator() {

	}

	/**
	 * 
	 * @param t
	 *            un obiect
	 * @return o lista ce contine numele atributelor obiecutului
	 */
	private ArrayList<String> retrieveFields(T t) {
		ArrayList<String> list = new ArrayList<String>();
		for (Field field : t.getClass().getDeclaredFields()) {
			list.add(field.getName());
		}
		return list;
	}

	/**
	 * 
	 * @param t
	 *            un obiect
	 * @return o lista de obiecte ce reprezinta valorile atributelor obiectului
	 */
	private ArrayList<Object> retrieveFieldValues(T t) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(t);
				list.add(value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 
	 * @param objects
	 *            o lista de obiecte
	 * @param nrColumns
	 *            numarul de atribute al obiecutlui
	 * @return o matrice de date cu numarul de obiecte linii si numarul de
	 *         atribute al obiectului coloane
	 */
	public String[][] buildDataMatrix(List<T> objects, int nrColumns) {
		if (objects.size() > 0 && nrColumns > 0) {
			String[][] data = new String[objects.size()][nrColumns];
			int line = 0;
			int column = 0;
			for (T iterator : objects) {
				ArrayList<Object> fieldValues = retrieveFieldValues(iterator);
				for (column = 0; column < fieldValues.size(); column++) {
					data[line][column] = fieldValues.get(column).toString();
				}
				line++;
			}
			return data;
		}
		return null;
	}

	/**
	 * 
	 * @param objects
	 * @return JTable populat cu datele obiectelor transmise printr-o lista
	 */
	public JTable createTable(List<T> objects) {
		if (objects.size() > 0) {
			ArrayList<String> fields = retrieveFields(objects.get(0));
			int nrColumns = fields.size();
			String[] columns = new String[nrColumns];
			for (int i = 0; i < nrColumns; i++) {
				columns[i] = fields.get(i);
			}

			String[][] data = buildDataMatrix(objects, nrColumns);
			JTable table = new JTable(data, columns);
			return table;
		}
		return null;
	}
}
