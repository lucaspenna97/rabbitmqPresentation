import com.rabbitmq.client.ConnectionFactory;

public class ConnectionFactoryRabbit {

    public static ConnectionFactory createConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        return  factory;
    }
}
