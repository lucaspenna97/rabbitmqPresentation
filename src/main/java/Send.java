import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "";

    private AMQP.BasicProperties.Builder createProperties() {
        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.deliveryMode(2);
        properties.expiration("60000");
        properties.priority(9);
        properties.contentEncoding("UTF-8");
        properties.contentType("text/plain");
        properties.timestamp(new Timestamp(System.currentTimeMillis()));
        return  properties;
    }

    public void sendMessageToQueue(String message) {
        try {
            Connection connection = ConnectionFactoryRabbit.createConnection().newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicPublish(EXCHANGE_NAME, QUEUE_NAME, createProperties().build(), message.getBytes());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
