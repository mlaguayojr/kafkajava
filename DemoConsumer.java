import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class DemoConsumer {

	public static void main(String[] args)
	{

		// Create our Consumer Properties
		Properties props = new Properties();
		
		// Connect to a Kafka service
		props.put("bootstrap.servers", "localhost:9092");
		
//		Multiple Kafka's can be queried like so
//		props.put("bootstrap.servers", "localhost:9092,10.0.0.13:9092");
		
		// You can further specify our consumer by specifying 'group.id'
		props.put("group.id", "test");
		
		props.put("enable.auto.commit", "true");
		
		props.put("auto.commit.interval.ms", "5000");
		
//		We need to know how to actually 'read' our Producer's messages
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// Create our Consumer based on the properties above
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		
		// Tell our Consumer which topic to listen to
		consumer.subscribe( Arrays.asList("JavaTest") );
		
		while (true)
		{

			// Time (milliseconds) to wait for messages
			ConsumerRecords<String, String> records = consumer.poll(5000);

			for (ConsumerRecord<String, String> record : records)
			{
				
				System.out.println("REC: " + record.value() );
				
//				This line below shows how the Consumer read/received this message from Kafka
//				System.out.printf("offset = %d, key = %s \n", record.offset(), record.key() );
			}
		}

	}

}
