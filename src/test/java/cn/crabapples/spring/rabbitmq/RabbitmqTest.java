package cn.crabapples.spring.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/3/20 0:56
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class RabbitmqTest {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.20");
        factory.setUsername("crabapples");
        factory.setPassword("crabapples");
        factory.setVirtualHost("/crabapples");
        return factory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println(new String(body, StandardCharsets.UTF_8));
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);


        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", QUEUE_NAME, null, ("喜欢你" + i).getBytes());
//            System.out.println(i);
            Thread.sleep(200);
        }
        channel.close();
        connection.close();
    }
}
