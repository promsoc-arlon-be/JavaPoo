import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.*;

public class PahoCallbackDemo implements MqttCallback {
	MqttClient client;

	public PahoCallbackDemo() {
	}

	public static void main(String[] args) {
		new PahoCallbackDemo().doDemo();
	}

	public void doDemo() {
		try {
// client = new MqttClient("tcp://localhost:1883", "Sending");
			client = new MqttClient("tcp://localhost:1883", "Sending", new MemoryPersistence());
			client.connect();
			client.setCallback(this);
			client.subscribe("foo");
			MqttMessage message = new MqttMessage();
			for (int i = 0; i < 5; i++) {
				message.setPayload(("A single message from my computer fff " + i).getBytes());
				client.publish("foo", message);
			}
			System.out.println("That's all folk!");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(message);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
// TODO Auto-generated method stub
	}
}