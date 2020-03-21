package cn.crabapples.demo;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * TODO rabbitmq工作队列演示
 *
 * @author Mr.He
 * 2020/3/21 2:13
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class RabbitmqWorkQueueDemo {
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqWorkQueueDemo.class);
    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws TimeoutException, IOException {
        new Thread(() -> {
            try {
                getMessage1();
            } catch (TimeoutException | IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                getMessage2();
            } catch (TimeoutException | IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                sendMessage();
            } catch (TimeoutException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void sendMessage() throws TimeoutException, IOException, InterruptedException {
        Connection connection = RabbitmqDemoConfigure.getConnection();
        Channel channel = connection.createChannel();
        int i = 0;
        while (true) {
            channel.basicPublish("", QUEUE_NAME, null, String.format("测试%s", i++).getBytes());
            Thread.sleep(500);
        }
    }

    private static void getMessage1() throws TimeoutException, IOException {
        Connection connection = RabbitmqDemoConfigure.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                logger.info("client1-->收到消息:[{}]", new String(body));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    private static void getMessage2() throws TimeoutException, IOException {
        Connection connection = RabbitmqDemoConfigure.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                logger.info("client2--->收到消息:[{}]", new String(body));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
