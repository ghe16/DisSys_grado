package com.example.client;
import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;

public class SensorClientQoS {
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "SensorClient";
    private static final String ROOM = "room1";
    private static final String TEMPERATURE_TOPIC = "sensors/" + ROOM + "/temperature";
    private static final String STATUS_TOPIC = "sensors/" + ROOM + "/status";
    private static final String ALERT_TOPIC = "alerts/" + ROOM;

    public static void main(String[] args) {
        try {
            // Create MQTT Client
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);

            // Set Last Will with QoS 1
            MqttConnectOptions options = new MqttConnectOptions();
            options.setWill(STATUS_TOPIC, "Sensor offline".getBytes(), 1, true);
            options.setKeepAliveInterval(10);

            // Connect to Broker
            client.connect(options);
            System.out.println("Connected to broker");

            // Publish initial status with QoS 1
            client.publish(STATUS_TOPIC, new MqttMessage("Sensor online".getBytes()) {{
                setQos(1);
                setRetained(true);
            }});

            // Simulate temperature readings
            Random random = new Random();
            while (true) {
                double temperature = 20 + random.nextDouble() * 20; // Random temperature between 20 and 40
                String temperatureMessage = String.format("%.2f", temperature);

                // Publish temperature with QoS 2 and retain flag
                client.publish(TEMPERATURE_TOPIC, new MqttMessage(temperatureMessage.getBytes()) {{
                    setQos(2);
                    setRetained(true);
                }});
                System.out.println("Published temperature: " + temperatureMessage);

                // Publish alert if temperature is above threshold with QoS 0
                if (temperature > 30) {
                    String alertMessage = "Overheating! " + temperatureMessage + "°C";
                    client.publish(ALERT_TOPIC, new MqttMessage(alertMessage.getBytes()) {{
                        setQos(0);
                        setRetained(false);
                    }});
                    System.out.println("Published alert: " + alertMessage);
                }

                Thread.sleep(5000); // Wait 5 seconds
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
