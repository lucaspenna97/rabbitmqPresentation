import com.rabbitmq.client.*;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {

    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "";

    public void receiveMessageFromQueue() {
        try {
            Connection connection = ConnectionFactoryRabbit.createConnection().newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicConsume(QUEUE_NAME,true, deliveredMessage(), canceledMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private DeliverCallback deliveredMessage() {
        return new DeliverCallback() {
            public void handle(String s, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                showMessage(message);
            }
        };
    }

    private CancelCallback canceledMessage() {
        return new CancelCallback() {
            public void handle(String s) throws IOException {
                System.out.println(s);
            }
        };
    }

}
