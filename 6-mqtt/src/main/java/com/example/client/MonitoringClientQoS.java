package com.example.client;

import org.eclipse.paho.client.mqttv3.*;

public class MonitoringClientQoS {
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "MonitoringClient";
    private static final String TEMPERATURE_WILDCARD_TOPIC = "sensors/+/temperature";
    private static final String SENSORS_HIERARCHY_TOPIC = "sensors/#";

    public static void main(String[] args) {
        try {
            // Create MQTT Client
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);

            // Set Callback for Incoming Messages
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("Received message on topic " + topic + ": " + new String(message.getPayload()) + " (QoS: " + message.getQos() + ")");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // No need to handle in subscriber
                }
            });

            // Connect to Broker
            client.connect();
            System.out.println("Connected to broker");

            // Subscribe to wildcard topics with QoS 1
            client.subscribe(TEMPERATURE_WILDCARD_TOPIC, 1);
            System.out.println("Subscribed to: " + TEMPERATURE_WILDCARD_TOPIC);

            client.subscribe(SENSORS_HIERARCHY_TOPIC, 2);
            System.out.println("Subscribed to: " + SENSORS_HIERARCHY_TOPIC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
