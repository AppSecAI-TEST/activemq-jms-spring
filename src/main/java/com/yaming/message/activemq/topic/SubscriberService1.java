package com.yaming.message.activemq.topic;

import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by yaming on 17-5-9.
 */
@Service
public class SubscriberService1 {
    @Resource(name = "jmsTopicTemplate")
    private JmsTemplate jmsTemplate;
    /**
     * 接收消息
     */
    public TextMessage receive(Destination destination) {
        TextMessage tm = (TextMessage)jmsTemplate.receive(destination);
        try {
            System.out.println("从主题" + destination.toString() + "收到了消息：\t"
                    + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return tm;

    }
}
