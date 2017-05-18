package com.yaming.message.activemq.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by yaming on 17-5-9.
 */
@Service
public class PubliherService {

    @Resource(name = "jmsTopicTemplate")
    private JmsTemplate jmsTemplate;
    public void send(Destination destination,final String message){
        System.out.println("向主题" + destination.toString() + "发送了消息------------" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    /**
     * 向默认主题发送消息
     */
    public void send(final String msg) {
        String destination =  jmsTemplate.getDefaultDestination().toString();
        System.out.println("向主题" +destination+ "发送了消息------------" + msg);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }
}
