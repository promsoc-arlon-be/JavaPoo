


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyTest {
	
	
	private static Logger logger = LogManager.getLogger();

	MqttClient client;
	

	public MyTest() {
	}

	public static void main(String[] args) {
		logger.debug("This is debug level");
		new MyTest().doDemo();
	}

	public void doDemo() {
		logger.info("Hello");
		try {
			client = new MqttClient("tcp://localhost:1883", "pahomqttpublish1");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("A single message 3".getBytes());
			client.publish("pahodemo/test", message);
			client.disconnect();
			System.out.println("That's all folk!");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
