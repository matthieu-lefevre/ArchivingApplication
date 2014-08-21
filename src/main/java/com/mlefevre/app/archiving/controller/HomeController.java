package com.mlefevre.app.archiving.controller;

import com.mlefevre.app.archiving.entity.EntityClass;
import com.mlefevre.app.archiving.exception.ThreadingException;
import com.mlefevre.app.archiving.repository.EntityArchiveRepository;
import com.mlefevre.app.archiving.repository.EntityMainRepository;
import com.mlefevre.app.archiving.service.ThreadingService;
import com.mlefevre.app.archiving.threading.ArchivingThread;
import com.mlefevre.app.archiving.threading.NotifyingThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private EntityArchiveRepository entityArchiveRepository;

    @Autowired
    private EntityMainRepository entityMainRepository;


    @Autowired
    private ThreadingService threadingService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {

        model.addAttribute("title", "Archiving Application");

        List<EntityClass> mainEntities = this.entityMainRepository.findAll();
        model.addAttribute("main", mainEntities);

        List<EntityClass> archiveEntities = this.entityArchiveRepository.findAll();
        model.addAttribute("archive", archiveEntities);


        NotifyingThread thread1 = new ArchivingThread("Thread1", Arrays.asList("1", "2", "3"));
        NotifyingThread thread2 = new ArchivingThread("Thread2", Arrays.asList("4", "5", "6"));

        try {
            this.threadingService.execute(Arrays.asList(thread1, thread2));

        } catch (ThreadingException e) {
            e.printStackTrace();
        }

        return "home";
    }

}
