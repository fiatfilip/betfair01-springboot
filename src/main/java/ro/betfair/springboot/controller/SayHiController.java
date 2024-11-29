package ro.betfair.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/hello")
public class SayHiController {
    //
    @GetMapping
    public ModelAndView hello(@RequestParam("nameX") String name){
        ModelAndView modelAndView = new ModelAndView("sayHi/hello");
        modelAndView.addObject("nameY", name);
        return modelAndView;
    }
}
