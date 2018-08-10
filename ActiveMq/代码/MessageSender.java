
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.File;
import java.io.Serializable;

/**
 * @ClassName: MessageSender
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016年7月26日 下午4:36:49
 * @since JDK 1.7
 * @see
 */
public class MessageSender {

	private MessageSender(){}

	private JmsTemplate jmsTemplate;
	public MessageSender(JmsTemplate jmsTemplate){
		this.jmsTemplate=jmsTemplate;
	}
	/**
	 * 发送消息
	 * @Title: sendMessage
	 * @param destination
	 * @param message
	 * @author: WangZhao
	 * @date 2016年7月22日 上午11:28:19
	 */
	public void sendTxtMessage(Destination destination, final String message){
		if (null==destination) {
			destination=jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination, new MessageCreator() {
		      public Message createMessage(Session session) throws JMSException {
		        return session.createTextMessage(message);
		      }
		});
	}
	/**
	 * @Title: sendObjectMessage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param destination
	 * @param object TODO(这里用一句话描述这个方法的参数和返回数据)
	 * @author: WangZhao
	 * @date 2016年7月26日 下午4:55:56
	 */
	public <T extends Serializable> void sendObjectMessage(Destination destination,final T object){
		if (null==destination) {
			destination=jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination, new MessageCreator() {
		      public Message createMessage(Session session) throws JMSException {
		    	  ObjectMessage message = session.createObjectMessage(object);
		          return message;
		      }
		});
	}

	/**
	 * 发送消息
	 * @Title: sendMessage
	 * @param destination
	 * @author: WangZhao
	 * @date 2016年7月22日 上午11:28:19
	 */
	public void sendStreamMessage(Destination destination,final String data,final File file){
		if (null==destination) {
			destination=jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination, new MessageCreator() {
		      public Message createMessage(Session session) throws JMSException {
		    	  StreamMessage message = session.createStreamMessage();
                //FileAndBytesUtil 见CommonCode包
            	  byte[] buffer = FileAndBytesUtil.fileToBytes(file);
            	  message.writeBytes(buffer);
                  message.setStringProperty("data", data);
                  return message;
		      }
		});
	}
}
