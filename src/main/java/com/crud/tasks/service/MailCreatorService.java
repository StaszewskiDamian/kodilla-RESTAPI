package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending task to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "https://staszewskidamian.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        context.setVariable("company",adminConfig.getCompanyName() + " phone: " + adminConfig.getCompanyPhone());
        context.setVariable("goodbye", "Fareweel my friend.");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    @Scheduled(cron = "0 0 11 * * *")
    public void sendMoreInformationEmail() {


    }
}
