package com.yaming.message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yaming on 17-5-8.
 * 欢迎页面
 */
@Controller
public class WelcomeController {
    @RequestMapping(value="/welcome",method= RequestMethod.GET)
    public ModelAndView welcome(){
        System.out.println("------------welcome");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }
    @RequestMapping(value = "/welcome2",method = RequestMethod.GET)
    public ModelAndView welcome2(){
        System.out.println("------------welcometopic");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("topic_welcome");
        return mv;
    }
}
