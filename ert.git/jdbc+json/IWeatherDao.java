import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.json.JSONException;

public interface IWeatherDao {

	public void add(weatherSet W) throws SQLException;

	public void getopendata() throws IOException, SQLException, JSONException, ParseException;
	public void createConn() throws SQLException;

	public void closeConn() throws SQLException;

	void writerJsondata() throws IOException, SQLException;

}
