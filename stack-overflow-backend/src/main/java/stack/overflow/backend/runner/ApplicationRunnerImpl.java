package stack.overflow.backend.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import stack.overflow.backend.model.entity.Account;
import stack.overflow.backend.model.entity.Permission;
import stack.overflow.backend.model.enumeration.PermissionName;
import stack.overflow.backend.service.AccountService;
import stack.overflow.backend.service.PermissionService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Profile("local")
@RequiredArgsConstructor
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final AccountService accountService;
    private final PermissionService permissionService;

    private final Map<PermissionName, Permission> permissionMap = new HashMap<>();
    private final Map<String, Account> accountMap = new HashMap<>();

    private final int adminCount = 5;
    private final int moderatorCount = 15;
    private final int userCount = 50;

    @Override
    public void run(ApplicationArguments args) {
        addPermissions();
        addAdmins();
        addModerators();
        addUsers();
    }

    private void addPermissions() {
        permissionMap.put(PermissionName.ADMIN, permissionService.save(new Permission(PermissionName.ADMIN)));
        permissionMap.put(PermissionName.MODERATOR, permissionService.save(new Permission(PermissionName.MODERATOR)));
        permissionMap.put(PermissionName.USER, permissionService.save(new Permission(PermissionName.USER)));
    }

    private void addAdmins() {
        for (int i = 1; i <= adminCount; i++) {
            Account admin = new Account();
            String username = "admin" + i;
            admin.setUsername(username);
            admin.setPassword("admin");
            admin.setCreationDate(LocalDateTime.now());
            admin.setIsAccountEnabled(Boolean.TRUE);
            admin.getPermissions().add(permissionMap.get(PermissionName.ADMIN));
            accountMap.put(username, accountService.save(admin));
        }
    }

    private void addModerators() {
        for (int i = 1; i <= moderatorCount; i++) {
            Account moderator = new Account();
            String username = "moderator" + i;
            moderator.setUsername(username);
            moderator.setPassword("moderator");
            moderator.setCreationDate(LocalDateTime.now());
            moderator.setIsAccountEnabled(Boolean.TRUE);
            moderator.getPermissions().add(permissionMap.get(PermissionName.MODERATOR));
            accountMap.put(username, accountService.save(moderator));
        }
    }

    private void addUsers() {
        for (int i = 1; i <= userCount; i++) {
            Account user = new Account();
            String username = "user" + i;
            user.setUsername(username);
            user.setPassword("user");
            user.setCreationDate(LocalDateTime.now());
            user.setIsAccountEnabled(Boolean.TRUE);
            user.getPermissions().add(permissionMap.get(PermissionName.USER));
            accountMap.put(username, accountService.save(user));
        }
    }
}
