package com.yaming.message.controller;

import com.yaming.message.activemq.topic.PubliherService;
import com.yaming.message.activemq.topic.SubscriberService1;
import com.yaming.message.activemq.topic.SubscriberService2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaming on 17-5-9.
 */
@Controller
public class TopicController {
    //主题
    @Resource(name = "topicDestination")
    private Destination topicDestination;
    //主题推送者
    @Resource(name = "publiherService")
    private PubliherService publisher;
    //主题接收者1
    @Resource(name = "subscriberService1")
    private SubscriberService1 subscriber1;
    //主题接收者2
    @Resource(name = "subscriberService2")
    private SubscriberService2 subscriber2;


     //生产消息
    @RequestMapping(value="/pub",method= RequestMethod.GET)
    public ModelAndView publih(){
        System.out.println("------------go publish");
        Date now = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format( now );
        System.out.println(time);
        ModelAndView mv = new ModelAndView();
        mv.addObject("time", time);
        mv.setViewName("publish");
        return mv;
    }


    //发送
    @RequestMapping(value="/onsend2",method=RequestMethod.POST)
    public ModelAndView publish(@RequestParam("message") String message) {
        System.out.println("------------send to topic");
        ModelAndView mv = new ModelAndView();
        publisher.send(topicDestination, message);

        mv.setViewName("topic_welcome");
        return mv;
    }

    //消费者1去接收主题
    @RequestMapping(value="/sub",method=RequestMethod.GET)
    public ModelAndView topic_receive() throws JMSException {
        System.out.println("------------ subscribe_message");
        ModelAndView mv = new ModelAndView();
        TextMessage tm = subscriber1.receive(topicDestination);
        mv.addObject("textMessage", tm.getText());

        mv.setViewName("topic_receive");
        return mv;
    }

    //消费者2去接收主题
    @RequestMapping(value="/sub2",method=RequestMethod.GET)
    public ModelAndView topic_receivetwo() throws JMSException {
        System.out.println("------------ subscribe2_message");
        ModelAndView mv = new ModelAndView();
        TextMessage tm = subscriber2.receive(topicDestination);
        mv.addObject("textMessage", tm.getText());

        mv.setViewName("topic_receive2");
        return mv;
    }

}
