import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLseverconn {
	private Connection conn;

	public Connection createconn() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=lettery;user=sa;password=ab8dyayaya";
		conn = DriverManager.getConnection(urlstr);
		return conn;

	}

	public void closeconn() throws SQLException {
		if (conn != null)
			conn.close();
	}
	
}
