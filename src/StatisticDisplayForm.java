import javax.swing.border.EmptyBorder;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class StatisticDisplayForm extends JFrame implements Observer {


    private Subject weatherData;
    private float sum;
    private float num;
    private float maxTemp = 300.0f;
    private float minTemp = 0.0f;
    private JPanel contentPane;
    private JTextField textFieldMin;
    private JTextField textFieldMax;
    private JTextField textFieldAverage;

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

    public void setWeatherData(Subject weatherData) {
        this.weatherData.removeObserver(this);
        this.weatherData = weatherData; //ссылка меняется
        weatherData.registerObserver(this); //для перерегистрации в новом прогнозе(датчика)
    }

    public StatisticDisplayForm(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
        setTitle(((WeatherData) weatherData).getCityName() + " statistic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        textFieldMin = new JTextField();
        textFieldMin.setBounds(150, 36, 86, 20);
        panel.add(textFieldMin);
        textFieldMin.setColumns(10);

        textFieldMax = new JTextField();
        textFieldMax.setColumns(10);
        textFieldMax.setBounds(150, 66, 86, 20);
        panel.add(textFieldMax);

        textFieldAverage = new JTextField();
        textFieldAverage.setColumns(10);
        textFieldAverage.setBounds(150, 97, 86, 20);
        panel.add(textFieldAverage);

        JLabel lblMin = new JLabel("Min temperature");
        lblMin.setBounds(10, 39, 100, 14);
        panel.add(lblMin);

        JLabel lblMax = new JLabel("Max temperature");
        lblMax.setBounds(10, 69, 100, 14);
        panel.add(lblMax);

        JLabel lblAverage = new JLabel("Average temperature");
        lblAverage.setBounds(10, 100, 100, 14);
        panel.add(lblAverage);
        setVisible(true);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        sum += temperature;
        num++;

        if (temperature < maxTemp) {
            maxTemp = temperature;

            textFieldMax.setText(Float.toString(temperature));
        }

        if (temperature > minTemp) {
            minTemp = temperature;
            textFieldMin.setText(Float.toString(temperature));
        }

        textFieldAverage.setText(sum/num + "");

    }
}
