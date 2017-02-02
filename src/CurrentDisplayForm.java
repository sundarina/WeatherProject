import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class CurrentDisplayForm extends JFrame implements Observer {

    private Subject weatherData;

    private JPanel contentPane;
    private JTextField textFieldTemp;
    private JTextField textFieldHum;
    private JTextField textFieldPles;
    private String city;


    /**
     * Launch the application.
     */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrentDisplayForm frame = new CurrentDisplayForm();
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



    /**
     * Create the frame.
     */
    public CurrentDisplayForm(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);

        setTitle("Current Weather in " + ((WeatherData)weatherData).getCityName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 304);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        textFieldTemp = new JTextField();
        textFieldTemp.setBounds(150, 36, 86, 20);
        panel.add(textFieldTemp);
        textFieldTemp.setColumns(10);

        textFieldHum = new JTextField();
        textFieldHum.setColumns(10);
        textFieldHum.setBounds(150, 66, 86, 20);
        panel.add(textFieldHum);

        textFieldPles = new JTextField();
        textFieldPles.setColumns(10);
        textFieldPles.setBounds(150, 97, 86, 20);
        panel.add(textFieldPles);

        JLabel lblTemp = new JLabel("Temperature");
        lblTemp.setBounds(10, 39, 100, 14);
        panel.add(lblTemp);

        JLabel lblHum = new JLabel("Humidity");
        lblHum.setBounds(10, 69, 100, 14);
        panel.add(lblHum);

        JLabel lblPles = new JLabel("Plessure");
        lblPles.setBounds(10, 99, 100, 14);
        panel.add(lblPles);
        setVisible(true);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        textFieldTemp.setText(Float.toString(temperature));
        textFieldHum.setText(Float.toString(humidity));
        textFieldPles.setText(Float.toString(pressure));
    }
}
