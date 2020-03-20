package cn.crabapples.demo;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * TODO rabbitmq简单队列演示
 *
 * @author Mr.He
 * 2020/3/20 2:02
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class RabbitmqSimpleQueueDemo {
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqSimpleQueueDemo.class);
    /**
     * 定义一个队列名
     */
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) {
        /*
         * 启动一个新的线程持续监听队列消息
         */
        new Thread(() -> {
            try {
                Connection connection = RabbitmqDemoConfigure.getConnection();
                Channel channel = connection.createChannel();
                /*
                 * ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑
                 * 打开一个到消息队列的通道
                 *
                 *
                 * rabbitmq新版本提供的api(持续监听队列)
                 * 重写handleDelivery方法即可在方法内部获取到消息内容进行相关操作
                 * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
                 */
                DefaultConsumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        super.handleDelivery(consumerTag, envelope, properties, body);
                        logger.info("队列监听->获取到新的消息:[{}]", new String(body, StandardCharsets.UTF_8));
                    }
                };

                channel.basicConsume(QUEUE_NAME, true, consumer);
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }).start();

        /*
         * 启动一个新的线程持续向队列发送消息
         */
        new Thread(() -> {
            try {
                Connection connection = RabbitmqDemoConfigure.getConnection();
                Channel channel = connection.createChannel();
                /*
                 * ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑
                 * 打开一个到消息队列的通道
                 *
                 *
                 * 创建一个名为 ${QUEUE_NAME} 的消息队列
                 * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
                 */
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                for (int i = 0; i < 50; i++) {
                    String message = String.format("第 %d 条消息:", i);
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                    logger.info("队列监听->消息发送成功:[{}]", message);
                    Thread.sleep(200);
                }
                channel.close();
                connection.close();
            } catch (IOException | InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
