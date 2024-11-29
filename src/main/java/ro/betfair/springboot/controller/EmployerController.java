package ro.betfair.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.betfair.springboot.entity.Employer;
import ro.betfair.springboot.repository.EmployerRepository;

import java.util.UUID;

@Controller
@RequestMapping(path = "/employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping(path = {"/", "/list"})
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("employers/list");
        Iterable<Employer> employerList = employerRepository.findAll();
        System.out.println(employerList);
        modelAndView.addObject("employerList", employerList);
        return modelAndView;
    }

    @GetMapping(path = "/add")
    public String form(){
        return "employers/form";
    }

    @PostMapping(path = "/add")
    public RedirectView add(@RequestParam(name = "employer_name") String employerName){
        Employer employer = new Employer();
        employer.setName(employerName);
        employerRepository.save(employer);
        return new RedirectView("/employers/list");
    }

    @GetMapping(path = "/delete/{employerId}")
    public RedirectView delete(@PathVariable("employerId") UUID employerId){
        employerRepository.deleteById(employerId);
        return new RedirectView("/employers/list");
    }
}
