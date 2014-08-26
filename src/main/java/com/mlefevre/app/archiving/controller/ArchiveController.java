package com.mlefevre.app.archiving.controller;

import com.mlefevre.app.archiving.domain.entity.EntityClass;
import com.mlefevre.app.archiving.exception.ArchiveException;
import com.mlefevre.app.archiving.service.ArchiveService;
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
public class ArchiveController {

    private static Logger LOG = LoggerFactory.getLogger(ArchiveController.class);

    @Autowired
    private ArchiveService archiveService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String display() {

        return "display";
    }

    @RequestMapping(value = "/documents", method = RequestMethod.POST)
    public String init(@RequestBody String archiveSqlQuery, ModelMap modelMap) {
        try {
            System.out.println(archiveSqlQuery);
            List<EntityClass> documents = this.archiveService.getDocumentsToArchive(archiveSqlQuery);
            modelMap.addAttribute("documents", documents);

        } catch (ArchiveException e) {
            LOG.error("An error occurred while retrieving documents to archive.", e);
            e.printStackTrace();
        }

        return "display-documents";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String archive(ModelMap modelMap) {
        try {
            this.archiveService.archiveDocuments();

        } catch (ArchiveException e) {
            LOG.error("An error occurred while archiving documents.", e);
            e.printStackTrace();
        }

        return "archived-documents";
    }

    @RequestMapping(value = "/clean", method = RequestMethod.GET)
    public String clean(ModelMap modelMap) {
        try {
            this.archiveService.cleanArchivedDocuments();

        } catch (ArchiveException e) {
            LOG.error("An error occurred while cleaning archived documents.", e);
            e.printStackTrace();
        }

        return "clean-documents";
    }

}
