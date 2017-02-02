public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private Subject weatherData;

    public void setWeatherData(Subject weatherData) {
        this.weatherData.removeObserver(this);
        this.weatherData = weatherData; //ссылка меняется
        weatherData.registerObserver(this); //для перерегистрации в новом прогнозе(датчика)
    }

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);

    }

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("City: "+((WeatherData)weatherData).getCityName()+" Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }
}
