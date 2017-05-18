package com.yaming.message.controller;

import com.yaming.message.activemq.queue.ConsumerService;
import com.yaming.message.activemq.queue.ConsumerService2;
import com.yaming.message.activemq.queue.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaming on 17-5-8.
 */
@Controller
public class DemoController {

    //队列名yaming.queue
    @Resource(name="demoQueueDestination")
    private Destination demoQueueDestination;

    //队列消息生产者
    @Resource(name="producerService")
    private ProducerService producer;

    //队列消息消费者1
    @Resource(name="consumerService")
    private ConsumerService consumer;

    //队列消息消费者2
    @Resource(name="consumerService2")
    private ConsumerService2 consumer2;

    //去生产
    @RequestMapping(value="/producer",method=RequestMethod.GET)
    public ModelAndView producer(){
        System.out.println("------------go producer");

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format( now );
        System.out.println(time);

        ModelAndView mv = new ModelAndView();
        mv.addObject("time", time);
        mv.setViewName("jms_producer");
        return mv;
    }
     //发送
    @RequestMapping(value="/onsend",method=RequestMethod.POST)
    public ModelAndView producer(@RequestParam("message") String message) {
        System.out.println("------------send to jms");
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(demoQueueDestination, message);
        mv.setViewName("welcome");
        return mv;
    }
     //消费者1去接收
    @RequestMapping(value="/receive",method=RequestMethod.GET)
    public ModelAndView queue_receive() throws JMSException {
        System.out.println("------------receive message");
        ModelAndView mv = new ModelAndView();
        TextMessage tm=null;
        if (demoQueueDestination != null) {
            tm = consumer.receive(demoQueueDestination);
            mv.addObject("textMessage", tm.getText());

            mv.setViewName("queue_receive");
            return mv;
        }else {
            tm = consumer.receive(demoQueueDestination);
            mv.addObject("textMessage", tm.getText());

            mv.setViewName("notNull");
            return mv;
        }


    }

    //消费者2去接收
    @RequestMapping(value="/receive2",method=RequestMethod.GET)
    public ModelAndView queue_receivetwo() throws JMSException {
        System.out.println("------------receive2 message");
        ModelAndView mv = new ModelAndView();
        TextMessage tm = consumer2.receive(demoQueueDestination);
        mv.addObject("textMessage", tm.getText());
        mv.setViewName("queue_receive2");
        return mv;
    }
    /*
     * ActiveMQ Manager Test
     */
    @RequestMapping(value="/jms",method=RequestMethod.GET)
    public ModelAndView jmsManager() throws IOException {
        System.out.println("------------jms manager");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index.jsp");

        JMXServiceURL url = new JMXServiceURL("");
        JMXConnector connector = JMXConnectorFactory.connect(url);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        return mv;
    }


}
