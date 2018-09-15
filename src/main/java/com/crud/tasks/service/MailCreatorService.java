package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
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

    @Autowired
    private TaskRepository taskRepository;

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
        context.setVariable("company", adminConfig.getCompanyName() + " phone: " + adminConfig.getCompanyPhone());
        context.setVariable("goodbye", "Fareweel my friend.");
        return templateEngine.process("mail/scheduled-mail", context);
    }


    public String sendMoreInformationEmail() {
        long size = taskRepository.count();
        String message = "Currently in database you got: " + size + " " + (size != 1 ? "tasks" : "task");
        Context context = new Context();
        context.setVariable("prev_message", message);
        context.setVariable("message", message);
        context.setVariable("button_url", "https://staszewskidamian.github.io/");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("button", "Show website");
        context.setVariable("goodbye", "Fareweel my friend.");
        context.setVariable("company", adminConfig.getCompanyName() + " phone: " + adminConfig.getCompanyPhone());
        return templateEngine.process("mail/scheduled-mail", context);
    }
}
