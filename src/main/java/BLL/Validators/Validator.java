package BLL.Validators;

/**
 * Interfata pentru validarea parametrilor in vederea operatiilor cu BD
 * 
 * @author user
 *
 * @param <T>
 *            clasa obiectului pentru care se face validarea
 */
public interface Validator<T> {

	public void validate(T t);
}
