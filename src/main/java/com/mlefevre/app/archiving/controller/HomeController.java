package com.mlefevre.app.archiving.controller;

import com.mlefevre.app.archiving.domain.entity.EntityClass;
import com.mlefevre.app.archiving.repository.EntityMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private EntityMainRepository entityMainRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("title", "Archiving Application");

        List<EntityClass> mainEntities = this.entityMainRepository.findAll();
        model.addAttribute("main", mainEntities);

        return "home";
    }

}
