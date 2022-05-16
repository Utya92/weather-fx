package project.weatherfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static project.weatherfx.constants.URLConstants.*;


public class WeatherController {


    @FXML
    private TextField city;

    @FXML
    private Text currentTemp;

    @FXML
    private Button getData;

    @FXML
    private Text maxTemp;

    @FXML
    private Text minTemp;

    @FXML
    private Text pressure;

    @FXML
    private Text real;

    @FXML
    void initialize() {
        getData.setOnAction(e -> {
            String enteredCity = city.getText().trim();
            if (!enteredCity.equals("")) {
                String output
                        = getUrlData(APIUrl + enteredCity + KEY);
                System.out.println(output);

                if (!output.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(output);
                    currentTemp.setText("Current temperature: " + jsonObject.getJSONObject(APIJsonKey)
                            .getDouble("temp") + " ℃");
                    real.setText("Real feel: " + jsonObject.getJSONObject(APIJsonKey)
                            .getDouble("feels_like") + " ℃");
                    maxTemp.setText("Max temperature: " + jsonObject.getJSONObject(APIJsonKey)
                            .getDouble("temp_max") + " ℃");
                    minTemp.setText("Min temperature: " + jsonObject.getJSONObject(APIJsonKey)
                            .getDouble("temp_min") + " ℃");
                    pressure.setText("Pressure: " + jsonObject.getJSONObject(APIJsonKey)
                            .getDouble("pressure") + " hPa");
                }
            }
        });
    }

    private String getUrlData(String urlRequest) {
        StringBuffer data = new StringBuffer();
        try {
            URL url = new URL(urlRequest);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader
                    = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String tempLine;

            while ((tempLine = bufferedReader.readLine()) != null) {
                data.append(tempLine + "\n");
            }
            bufferedReader.close();

        } catch (Exception e) {
            city.setText("city " + "\"" + city.getText() + "\"" + " wasn't found");
        }
        return data.toString();
    }
}

