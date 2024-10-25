package com.kadiraksoy.museo_vr.scheduler;

import com.kadiraksoy.museo_vr.model.User;
import com.kadiraksoy.museo_vr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {

    private final UserService userService;

    public ScheduledTasks(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteEmails() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            LocalDateTime createdTime = user.getCreatedAt();
            LocalDateTime time = LocalDateTime.now();
            if (!user.isActive() && createdTime.plusDays(7).isBefore(time)) {
                userService.deleteUser(user.getId());
                log.info("User with id {} deleted", user.getId());
            }
        }

    }
}
