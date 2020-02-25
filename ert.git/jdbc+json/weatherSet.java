import java.sql.Date;

public class weatherSet {
	private String stationId;
	private String locationName;
	private String City;
	private String Town;
	private float HOUR_6;
	private float HOUR_12;
	private float HOUR_24;
	private Date obsTime;
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getTown() {
		return Town;
	}
	public void setTown(String town) {
		Town = town;
	}
	
	public float getHOUR_6() {
		return HOUR_6;
	}
	public void setHOUR_6(float f) {
		HOUR_6 = f;
	}
	public float getHOUR_12() {
		return HOUR_12;
	}
	public void setHOUR_12(float hOUR_12) {
		HOUR_12 = hOUR_12;
	}
	public float getHOUR_24() {
		return HOUR_24;
	}
	public void setHOUR_24(float hOUR_24) {
		HOUR_24 = hOUR_24;
	}
	public Date getObsTime() {
		return obsTime;
	}
	public void setObsTime(Date l) {
		this.obsTime = l;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
}
