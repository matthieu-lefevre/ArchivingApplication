package com.mlefevre.app.archiving.controller;

import com.mlefevre.app.archiving.exception.ArchiveException;
import com.mlefevre.app.archiving.service.ArchiveService;
import com.mlefevre.app.archiving.threading.ThreadCompleteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/archive")
public class ArchiveController implements ThreadCompleteListener {

    private static Logger LOG = LoggerFactory.getLogger(ArchiveController.class);

    @Autowired
    private ArchiveService archiveService;


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap modelMap) {

        List<String> documentsIds = this.archiveService.getDocumentsToArchive();

        modelMap.addAttribute("documentsIds", documentsIds);

        return "init-archive";
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void start(@RequestBody List<String> documentIds) {

        try {
            this.archiveService.archiveDocuments(documentIds);

        } catch (ArchiveException e) {
            LOG.error("An error occurred while archiving documents.", e);
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    public String clean(@RequestBody List<String> documentIds) {

        this.archiveService.cleanArchivedDocuments(documentIds);

        return "clean-documents";
    }


    @Override
    public void notifyThreadCompleted(Thread thread) {
        System.out.println(thread.getName() + " finished!");
    }
}
