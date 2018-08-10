
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.net.URISyntaxException;


public class MqSender {

    private ActiveMQConnectionFactory connectionFactory;
    private JmsTemplate jmsTemplate;
    private Destination destination;


    private String url="failover:(tcp://192.168.70.90:61616,tcp://192.168.70.86:61616)?initialReconnectDelay=1000&maxReconnectAttempts=5";
    @Before
    public void getConn() throws URISyntaxException, JMSException {

        connectionFactory = new ActiveMQConnectionFactory("ikongjian","ikongjian123",url);
        connectionFactory.setUseAsyncSend(true);
        jmsTemplate = new JmsTemplate(connectionFactory);
        destination = new ActiveMQQueue("www.tests");
    }

    @Test
    public void test() throws JMSException {

        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("{\"msg\":\"CRM测试一下消息\"}");
            }
        });
    }

}
