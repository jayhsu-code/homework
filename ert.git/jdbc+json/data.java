import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.json.JSONException;
public class data {
private Connection conn;
	public static void main(String[] arg) throws IOException, SQLException, JSONException, ParseException {
		IWeatherDao Dao=WeatherFactory.createWeather();
		weatherSet W=new weatherSet();
//		Dao.createConn();
//		Dao.getopendata();
//		Dao.writerJsondata();
//		Dao.closeConn();
		
		
		
			
	}
}

