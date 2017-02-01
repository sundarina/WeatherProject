import java.util.*;


public class WeatherStation {
    public static void main(String[] args) throws InterruptedException {

        WeatherData weatherDataKiev = new WeatherData("Kiev");
        WeatherData weatherDataLviv = new WeatherData("Lviv");

        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherDataKiev);

        //currentDisplay.setWeatherData(weatherDataLviv);
        //Observer cd =
        new CurrentDisplayForm(weatherDataLviv);

        new CurrentDisplayForm(weatherDataKiev);

        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherDataKiev);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDataKiev);

        while (true) {
            Thread.sleep(200);
            int r = ((int) Math.random() * 100);
            weatherDataKiev.setMeasurements(80 + r, 65 + r, 30.4f + r);
            weatherDataLviv.setMeasurements(82 + r, 70 + r, 29.2f + r);
            weatherDataKiev.setMeasurements(78 + r, 90 + r, 29.2f + r);
        }
    }
}
