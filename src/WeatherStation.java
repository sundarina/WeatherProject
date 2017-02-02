public class WeatherStation {
    public static void main(String[] args) throws InterruptedException {

        WeatherData weatherDataKiev = new WeatherData("Kiev");
        WeatherData weatherDataLviv = new WeatherData("Lviv");

       // CurrentConditionsDisplay currentDisplay =
         //       new CurrentConditionsDisplay(weatherDataKiev);

       // currentDisplay.setWeatherData(weatherDataLviv);
       Observer cd = new CurrentDisplayForm(weatherDataLviv);
        new CurrentDisplayForm(weatherDataKiev);

        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherDataKiev);

        new StatisticDisplayForm(weatherDataKiev);
        new StatisticDisplayForm(weatherDataLviv);

       // ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDataKiev);
        new ForecastDisplayForm(weatherDataKiev);
        new ForecastDisplayForm(weatherDataLviv);



        while (true) {
            Thread.sleep(400);
            int r = ((int) (Math.random() * 200 ) + 1);
            int r2 = ((int)(Math.random() * 100 + 1));
            weatherDataKiev.setMeasurements( r, 65, 763.4f );
            weatherDataLviv.setMeasurements(r + r2, 70, 766.2f);
            weatherDataKiev.setMeasurements( r, 90, 764.5f);
            weatherDataLviv.setMeasurements(r + r2, 95, 766.5f);
        }
    }
}
