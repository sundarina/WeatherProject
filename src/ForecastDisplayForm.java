import javax.swing.border.EmptyBorder;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ForecastDisplayForm extends JFrame implements Observer {

    private Subject weatherData;

    private JPanel contentPane;
    private JTextField textFieldLastPressure;
    private JTextField textFieldCurrentPressure;
    private JTextField textFieldForecast;


    private float lastPressure;
    private float currentPressure = 765.5f;


    public void setWeatherData(Subject weatherData) {
        this.weatherData.removeObserver(this);
        this.weatherData = weatherData; //ссылка меняется
        weatherData.registerObserver(this); //для перерегистрации в новом прогнозе(датчика)
    }

    /*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForecastDisplayForm frame = new CurrentDisplayForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

    public ForecastDisplayForm(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
        setTitle(((WeatherData)weatherData).getCityName() + " forecast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        textFieldLastPressure = new JTextField();
        textFieldLastPressure.setBounds(300, 36, 86, 20);
        panel.add(textFieldLastPressure);
        textFieldLastPressure.setColumns(20);

        textFieldCurrentPressure = new JTextField();
        textFieldCurrentPressure.setColumns(20);
        textFieldCurrentPressure.setBounds(300, 66, 86, 20);
        panel.add(textFieldCurrentPressure);

        textFieldForecast = new JTextField();
        textFieldForecast.setColumns(20);
        textFieldForecast.setBounds(300, 96, 240, 20);
        panel.add(textFieldForecast);

        JLabel lblTemp = new JLabel("Last pressure");
        lblTemp.setBounds(10, 39, 100, 14);
        panel.add(lblTemp);

        JLabel lblHum = new JLabel("Current pressure");
        lblHum.setBounds(10, 69, 100, 14);
        panel.add(lblHum);

        JLabel lblFor = new JLabel("Forecast");
        lblFor.setBounds(10, 100, 100, 14);
        panel.add(lblFor);
        setVisible(true);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        textFieldLastPressure.setText(Float.toString(pressure));
        textFieldCurrentPressure.setText(Float.toString(currentPressure));

        if (currentPressure > pressure) {
            textFieldForecast.setText("Improving weather on the way!");
        } else if (currentPressure == pressure) {
            textFieldForecast.setText("More of the same");
        } else if (currentPressure < pressure) {
            textFieldForecast.setText("Watch out for cooler, rainy weather");
        }
    }
}
