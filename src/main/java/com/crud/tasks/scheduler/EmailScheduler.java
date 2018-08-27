package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private EmailScheduler emailScheduler;

    private static final String SUBJECT = "Tasks: Once a day email";
    private String message;

    //@Scheduled(fixedDelay = 30000)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        emailScheduler.createMessageContent();
        simpleEmailService.sendOnSchedule(new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                message));
    }

    private String createMessageContent() {
        long size = taskRepository.count();
        message = "Currently in database you got: " + size + " task" + ((size != 1) ? "s" : "");
        return message;
    }

    public String getMessage() {
        return message;
    }
}
