package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.AppConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.scheduler.EmailScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private EmailScheduler emailScheduler;

    public String bulidTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "");
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String bulidTasksStatusEmail(String message) {

        Context context = new Context();
        context.setVariable("message", emailScheduler.getMessage());
        context.setVariable("preview", "");
        context.setVariable("welcome", adminConfig.getAdminName());
        context.setVariable("button", "Click to show your tasks");
        context.setVariable("show_button", true);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("app_config", appConfig);
        context.setVariable("goodbye", "If you don't have to, don't print this email. Think about the environment. Thank you!");
        return templateEngine.process("mail/crated-scheduled-mail", context);
    }
}
