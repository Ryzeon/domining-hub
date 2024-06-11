package me.ryzeon.domininghub.shared.security.events;

import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.shared.security.model.Role;
import me.ryzeon.domininghub.shared.security.model.Roles;
import me.ryzeon.domininghub.shared.security.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 09/06/24 @ 23:37
*/
@Service
@AllArgsConstructor
public class ApplicationReadyEventHandler {

    private final RoleRepository roleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var appName = event.getApplicationContext().getId();
        LOGGER.info("Starting roles and permissions setup for {}...", appName);
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(role));
                LOGGER.info("Role {} created", role);
            } else {
                LOGGER.info("Role {} already exists", role);
            }
        });
    }
}
