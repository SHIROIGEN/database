package DouBan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DaoBase implements Dao {
	protected String url;
	public DaoBase(String url){
		this.url = url;
	}
	public DaoBase() {
		this.url= "jdbc:sqlserver://127.0.0.1:1433;databaseName=DoubanDB;user=student;password=student";
	}
	public Connection getConnection() {
		Connection con=null;
		try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con =  DriverManager.getConnection(this.url);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {}
		return con;
	}
}