
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.net.URISyntaxException;


public class MqListener {
    private ActiveMQConnectionFactory connectionFactory;
    private PooledConnectionFactory pooledConnectionFactory;
    private DefaultMessageListenerContainer container;


    private String url="failover:(tcp://192.168.70.90:61616,tcp://192.168.70.86:61616)?initialReconnectDelay=1000&maxReconnectAttempts=5";

    @Before
    public void getConn() throws URISyntaxException, JMSException {

        connectionFactory = new ActiveMQConnectionFactory("username","password",url);
        connectionFactory.setUseAsyncSend(true);
        pooledConnectionFactory = new PooledConnectionFactory(connectionFactory);


        container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(pooledConnectionFactory);
        container.setDestination(new ActiveMQQueue("www.test"));
        container.setMessageListener(new ActiveListener());

        container.initialize();//未执行本行代码，启动不报错，无法收取消息

        container.start();
    }

    @Test
    public void test() throws JMSException {



        while (true){}
    }

    class ActiveListener implements MessageListener{
        private Logger logger = LoggerFactory.getLogger(ActiveListener.class);
        @Override
        public void onMessage(Message message) {
            System.out.println("接收到队列消息");
            try {
                if (message instanceof TextMessage) {
                    logger.warn("接收到队列消息",message);
                }
            } catch (Exception e) {
                logger.error("接收消息失败:" + e.getMessage());
                logger.error("消息类message：",message);
                e.printStackTrace();
            }
        }
    }

}
