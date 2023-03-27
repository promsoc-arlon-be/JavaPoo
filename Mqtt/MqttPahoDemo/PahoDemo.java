
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PahoDemo {
	MqttClient client;

	public PahoDemo() {
	}

	public static void main(String[] args) {
		new PahoDemo().doDemo();
	}

	public void doDemo() {
		try {
			client = new MqttClient("tcp://localhost:1883", "pahomqttpublish1");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("A single message".getBytes());
			client.publish("foo", message.getPayload(), 2, true);
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
