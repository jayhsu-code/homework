import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weatherdao implements IWeatherDao {
	private Connection conn;

	public void add(weatherSet m) throws SQLException {
		String sqlstr = "insert into weatheropendate(stationId,locationName,city,Town,hour6,hour12,hour24,datatime) values(?,?,?,?,?,?,?,?)";

		PreparedStatement status = conn.prepareStatement(sqlstr);
		status.setString(1, m.getStationId());
		status.setString(2, m.getLocationName());
		status.setString(3, m.getCity());
		status.setString(4, m.getTown());
		status.setFloat(5, m.getHOUR_6());
		status.setFloat(6, m.getHOUR_12());
		status.setFloat(7, m.getHOUR_24());
		status.setDate(8, m.getObsTime());

		status.executeUpdate();
		status.close();
	}

	@Override
	public void createConn() throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=opendata;user=sa;password=ab8dyayaya";
			conn = DriverManager.getConnection(urlstr);
			System.out.println("status:" + !conn.isClosed());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void closeConn() throws SQLException {
		if (conn != null)
			conn.close();
	}

	public void getopendata() throws IOException, SQLException, JSONException, ParseException {
		weatherSet se1 = new weatherSet();
		File json1 = new File("/Users/apple/Downloads/O-A0002-001.json");
		FileInputStream fli1e = new FileInputStream(json1);
		String jsonstr = "";
		InputStreamReader ipst = new InputStreamReader(fli1e, "UTF-8");
		BufferedReader bdr = new BufferedReader(ipst);

		while (bdr.ready()) {

			jsonstr += bdr.readLine();
		}
		JSONObject jsonOb = new JSONObject(jsonstr);
		JSONObject jsonarry = jsonOb.getJSONObject("cwbopendata");
		JSONArray jsonlocation = jsonarry.getJSONArray("location");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

		for (Object i : jsonlocation) {
			String stri = i.toString();
			JSONObject strtojson = new JSONObject(stri);
			// m.get(strtojson.get(locationName))
			se1.setStationId(strtojson.get("stationId").toString());
			se1.setLocationName(strtojson.get("locationName").toString());
			// 抓縣市
			se1.setCity(strtojson.getJSONArray("parameter").getJSONObject(0).get("parameterValue").toString());
			// 抓鄉鎮市
			se1.setTown(strtojson.getJSONArray("parameter").getJSONObject(2).get("parameterValue").toString());
			// 抓6小時雨量
			se1.setHOUR_6(strtojson.getJSONArray("weatherElement").getJSONObject(4).getJSONObject("elementValue")
					.getFloat("value"));
			// 抓12小時雨量
			se1.setHOUR_12(strtojson.getJSONArray("weatherElement").getJSONObject(5).getJSONObject("elementValue")
					.getFloat("value"));
			// 抓24小時雨量
			se1.setHOUR_24(strtojson.getJSONArray("weatherElement").getJSONObject(6).getJSONObject("elementValue")
					.getFloat("value"));
			String date1 = strtojson.getJSONObject("time").get("obsTime").toString().replace("T", "-").substring(0, 19);
			java.util.Date d1 = sdf.parse(date1);
			java.sql.Date d2 = new java.sql.Date(d1.getTime());
			se1.setObsTime(d2);
			add(se1);

		}

		System.out.println("data save finish");
		bdr.close();

	}

	public void writerJsondata() throws IOException, SQLException {

		String sqlstr = "select * from weatheropendate";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		ResultSet rs = state.executeQuery();
		String json = "{\"weather\":[";
		while (rs.next()) {
			String stationId = rs.getString(1);
			String locationName = rs.getString(2);
			String City = rs.getString(3);
			String town = rs.getString(4);
			String hour6 = rs.getString(5);
			String hour12 = rs.getString(6);

			String hour24 = rs.getString(7);
			Date getdate = rs.getDate(8);

			json += "{\n\"stationId\"" + stationId + "\n\"locationName\":" + "\"" + locationName + "\"" + ",\n\"City\":"
					+ "\"" + City + "\"" + ",\n\"town\":" + "\"" + town + "\"" + ",\n\"hour6\":" + "\"" + hour6 + "\""
					+ ",\n\"hour12\":" + "\"" + hour12 + "\"" + ",\n\"hour24\":" + "\"" + hour24 + "\""
					+ ",\n\"getDate\":" + "\"" + getdate + "\"" + "},";

		}
		json = json.substring(0, json.length() - 1);
		json += "]}";
		File jsondate = new File("/Users/apple/Documents/JAVA/redate.json");
		OutputStream ops = new FileOutputStream(jsondate);

		OutputStreamWriter opsw = new OutputStreamWriter(ops, "UTF-8");
		opsw.append(json);
		opsw.close();
		ops.close();

	}

	
}
