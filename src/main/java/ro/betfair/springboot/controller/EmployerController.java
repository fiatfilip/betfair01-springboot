package ro.betfair.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.betfair.springboot.entity.Employer;
import ro.betfair.springboot.repository.EmployerRepository;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "/employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping(path = {"/", "/list"})
    public ModelAndView list(@RequestParam("order") String order,
                             @RequestParam("column") String column){
        ModelAndView modelAndView = new ModelAndView("employers/list");
        Sort.Direction direction = Sort.Direction.ASC;
        if(order.equals("desc")){
            direction = Sort.Direction.DESC;
        }
        String orderByColumn = "id";
        if(column.equals("name")){
            orderByColumn = "name";
        } else if(column.equals("city")){
            orderByColumn = "city";
        }
        Iterable<Employer> employerList = employerRepository.findAll(
                Sort.by(direction, orderByColumn)
        );
        System.out.println(employerList);
        modelAndView.addObject("employerList", employerList);
        return modelAndView;
    }

    @GetMapping(path = "/add")
    public String form(){
        return "employers/add-form";
    }

    @GetMapping(path = "/edit/{employerId}")
    public ModelAndView editForm(@PathVariable("employerId") UUID employerId){
        ModelAndView modelAndView = new ModelAndView("employers/edit-form");
        Optional<Employer> employer = employerRepository.findById(employerId);
        modelAndView.addObject("employerToEdit", employer.get());
        return modelAndView;
    }

    @PostMapping(path = "/edit/{employerId}")
    public RedirectView editEmployer(
            @PathVariable("employerId") UUID employerId,
            @RequestParam(name = "employer_name") String employerName,
            @RequestParam(name = "employer_city") String employerCity){
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        Employer employer  = optionalEmployer.get();
        employer.setName(employerName);
        employer.setCity(employerCity);
        employerRepository.save(employer);
        return new RedirectView("/employers/list");
    }

    @PostMapping(path = "/add")
    public RedirectView add(@RequestParam(name = "employer_name") String employerName,
                            @RequestParam(name = "employer_city") String employerCity){
        Employer employer = new Employer();
        employer.setName(employerName);
        employer.setCity(employerCity);
        employerRepository.save(employer);
        return new RedirectView("/employers/list");
    }

    @GetMapping(path = "/delete/{employerId}")
    public RedirectView delete(@PathVariable("employerId") UUID employerId){
        employerRepository.deleteById(employerId);
        return new RedirectView("/employers/list");
    }
}
