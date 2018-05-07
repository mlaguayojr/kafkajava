import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import kafka.common.KafkaException;

/**
 * Preface:
 * 
 * Make sure Kafka Server and Zookeeper servers are running.
 * Make sure that both the Producer and Consumer are communicating on the same topic
 * @author Mario
 *
 */
public class DemoProducer {

	private static String topic;
	private static Producer<String, String> producer;

	public static void main(String[] args) throws InterruptedException {

		// This is the conversation / topic to write to.
		// Your Consumer should be listening to this topic!
		topic = "JavaTest";

		// Configuration for our Kafka Producer
		Properties props = new Properties();

		// If you have multiple Kafka servers, you can comma delimit 'bootstrap.servers'
		// props.put("bootstrap.servers", "localhost:9092,10.0.0.24:9092");

		props.put("bootstrap.servers", "localhost:9092");
		props.put("auto.create.topics.enable", true); // If set to true, Kafka will auto create the topic. If set to false, make sure the topic is created by using the step provided in the read me.
		props.put("acks", "all");
		props.put("retries", 1);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


		// Create our Kafka Producer based on the configuration provided above
		producer = new KafkaProducer<>( props );

//		 scripted();
		interactive();
		
		// Disconnect our Producer
		producer.close();

	}


	/**
	 * You provide messages to send
	 */
	static void interactive()
	{
		System.out.println("-- Interactive Mode --\n--Ctrl+Z to end.--\n--Type message. Hit enter to send.--\n");
		
		Scanner input = new Scanner(System.in);
		while(input.hasNextLine())
		{
			send(input.next());
		}
	}

	/**
	 * A non-interactive instance of Kafka's Producer
	 */
	static void scripted()
	{
		System.out.println("-- Scripted Mode --");
		// Messages to write
		String[] messages = new String[]{
				"-- Start of conversation --",
				"Hello There!",
				"Message 1",
				"Message 2",
				"Hello Consumer 1",
				"Test 1",
				"Test 2"
		};
		
		for ( String m: messages)
		{
			send(m);
		}

	}

	/**
	 * Send String m as Producer
	 * @param m
	 */
	static void send(String m)
	{
		try {

			System.out.println("Sending: '" + m + "' to '" + topic + "'");
			producer.send( new ProducerRecord<String, String>(topic, m) );

		} catch (KafkaException e) {

			// Errors based on Kafka
			System.out.println( e.getMessage() );
		}
	}

}
