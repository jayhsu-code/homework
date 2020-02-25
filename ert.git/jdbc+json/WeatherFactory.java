
public class WeatherFactory {
public static IWeatherDao createWeather() {
	IWeatherDao Dao=new Weatherdao();
	return Dao;
}
}
