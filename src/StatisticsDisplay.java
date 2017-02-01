public class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 300.0f;
    private float tempSum = 0.0f;
    private int num;
    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        num++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }
        display();
    }

    public void display() {
        System.out.println("City: "+((WeatherData)weatherData).getCityName()+" Avg/Max/Min temperature = " + (tempSum / num)
                + "/" + maxTemp + "/" + minTemp);
    }
}
