package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

/**
 * Aceasta clasa utilizeaza reflection techniques pentru a construi si a efectua
 * operatiile de SELECT *, SELECT, INSERT, DELETE si UPDATE asupra tabelelor din
 * BD
 * 
 * @author user
 *
 * @param <T>
 *            clasa ce reprezinta modelul tabelului din BD asupra caruia se
 *            efectueaza operatii
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * 
	 * @param object
	 *            un obiect
	 * @return o lista de String ce reprezinta numele campurilor obiectului
	 */
	private ArrayList<String> retrieveFields(Object object) {
		ArrayList<String> list = new ArrayList<String>();
		for (Field field : object.getClass().getDeclaredFields()) {
			if (!field.getName().equals("id")) {
				list.add(field.getName());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param object
	 *            un obiect
	 * @return un ArrayList de obiecte ce reprezinta valorile atributelor
	 *         obiectului
	 */
	private ArrayList<Object> retrieveFieldValues(Object object) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (Field field : object.getClass().getDeclaredFields()) {
			if (!field.getName().equals("id")) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(object);
					list.add(value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @return un string ce creeaza query-ul pentru find all
	 */
	private String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	/**
	 * 
	 * @param field
	 *            string care reprezinta campul dupa care se face selectia din
	 *            tabelul aflat in BD
	 * @return un string ce creeaza query-ul aferent
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * 
	 * @param field
	 *            string ce reprezinta campul dupa care se face stergerea din
	 *            tabelul aflat in BD
	 * @return string ce modeleaza query-ul
	 */
	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * 
	 * @param fields
	 *            o lista de string-uri ce reprezinta numele coloanelor din
	 *            tabelul in care se face inserearea
	 * @return un string ce reprezinta sintaxa SQL
	 */
	private String createInsertQuery(ArrayList<String> fields) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ");
		sb.append("INTO ");
		sb.append("warehousedb.");
		sb.append(type.getSimpleName());
		sb.append("(");
		for (int i = 0; i < fields.size(); i++) {
			if (i != fields.size() - 1)
				sb.append(fields.get(i) + ", ");
			else
				sb.append(fields.get(i));
		}
		sb.append(")");
		sb.append(" VALUES ");
		sb.append("(");
		for (int i = 0; i < fields.size(); i++) {
			if (i != fields.size() - 1) {
				sb.append("?,");
			} else {
				sb.append("?");
			}
		}
		sb.append(")");
		return sb.toString();

	}

	/**
	 * 
	 * @param fields
	 *            o lista de String-uri ce reprezinta coloanele tabelului in
	 *            care se face update
	 * @return un string ce reprezinta sintaxa SQL pt operatia UPDATE
	 */
	private String createUpdateQuery(ArrayList<String> fields) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for (int i = 0; i < fields.size(); i++) {
			if (i != fields.size() - 1)
				sb.append(fields.get(i) + " = ?, ");
			else
				sb.append(fields.get(i) + " = ? ");
		}
		sb.append(" WHERE id =?");
		return sb.toString();
	}

	/**
	 * 
	 * @return lista cu toate inregistrarile tabelului
	 */
	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 *            id-ul inregistrarii din tabel
	 * @return obiectul cu atributele din tabel daca exista si null daca nu
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 *            id-ul inregistrarii care se doreste sa fie stearsa
	 * @return true daca s-a sters si false altfel
	 */
	public boolean deleteById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean executed = true;
		String query = createDeleteQuery("id");

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
			executed = false;
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return executed;
	}

	/**
	 * 
	 * @param resultSet
	 *            parametru de tip resultSet, care este setul de rezultate dupa
	 *            instructiunea SQL executata
	 * @return o lista de obiecte care coincide cu rezultatul instructiunii SQL
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @param t
	 *            un obiect care se doreste adaugat in tabel
	 * @return obiectul in sine daca s-a adaugat, null altfel
	 */
	public T insert(T t) {
		boolean executed = true;
		ArrayList<String> list = retrieveFields(t);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertQuery(list);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			ArrayList<Object> values = retrieveFieldValues(t);
			int k = 1;
			for (Object obj : values) {
				if (obj.getClass().getSimpleName().equals("Integer")) {
					statement.setInt(k, ((Integer) obj).intValue());
				}
				if (obj.getClass().getSimpleName().equals("Float")) {
					statement.setFloat(k, ((Float) obj).floatValue());
				}
				if (obj.getClass().getSimpleName().equals("String")) {
					statement.setString(k, (String) obj);
				}
				if (obj.getClass().getSimpleName().equals("Date")) {
					System.out.println(obj.toString());
					statement.setDate(k, (Date) obj);
					/*
					 * String TimeString = obj.toString(); try { java.util.Date
					 * utilDate = (Date) new
					 * SimpleDateFormat("YYYY-MM-DD").parse(TimeString);
					 * statement.setDate(k, new
					 * java.sql.Date(utilDate.getTime())); } catch (Exception e)
					 * { System.out.println(e.getMessage()); }
					 */
				}
				k++;
			}
			System.out.println(statement.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
			executed = false;
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		if (executed)
			return t;
		else
			return null;
	}

	/**
	 * 
	 * @param id
	 *            id-ul inregistrarii pentru care se face update
	 * @param updateObject
	 *            obiectul cu atributele care vor fi acutalizate in tabel
	 * @return obiectul daca s-a facut update, null altfel
	 */
	public T updateById(int id, T updateObject) {
		boolean executed = true;
		T foundById = findById(id);
		if (foundById == null)
			return null;
		ArrayList<String> list = retrieveFields(foundById);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createUpdateQuery(list);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			ArrayList<Object> values = retrieveFieldValues(updateObject);
			int k = 1;
			for (Object obj : values) {
				if (obj.getClass().getSimpleName().equals("Integer")) {
					statement.setInt(k, ((Integer) obj).intValue());
				}
				if (obj.getClass().getSimpleName().equals("Float")) {
					statement.setFloat(k, ((Float) obj).floatValue());
				}
				if (obj.getClass().getSimpleName().equals("String")) {
					statement.setString(k, (String) obj);
				}
				if (obj.getClass().getSimpleName().equals("Date")) {
					String TimeString = obj.toString();
					try {
						Date utilDate = (Date) new SimpleDateFormat("YYYY-MM-DD").parse(TimeString);
						statement.setDate(k, new java.sql.Date(utilDate.getTime()));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				k++;
			}
			statement.setInt(k, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
			executed = false;
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		if (executed)
			return foundById;
		else
			return null;

	}
}
