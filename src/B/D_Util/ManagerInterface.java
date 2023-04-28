package B.D_Util;

import java.sql.SQLException;
import java.util.List;


/**
 * Each manager must implement these methods
 */
public interface ManagerInterface <T>{
	
	/**
	 * Return all instances of T in Data Base
	 * 
	 * @return All instances of T
	 * @throws SQLException
	 * @throws NotFoundException
	 * @throws Exception
	 */
	public List<T> selectAll () throws SQLException, Exception;
	
	/**
	 * Insert a new T on Data Base
	 * 
	 * @param t
	 */
	public void insert (T t) throws SQLException, Exception;

	/**
	 * Updates a T on Data Base 
	 * 
	 * @param t
	 */
	public void update (T t) throws SQLException, Exception;
	
	/**
	 * Delete a T on Data Base 
	 * 
	 * @param t
	 */
	public void delete (T t) throws SQLException, Exception;
	
}
