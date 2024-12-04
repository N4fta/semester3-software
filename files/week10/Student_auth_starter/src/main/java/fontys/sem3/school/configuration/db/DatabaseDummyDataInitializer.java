package fontys.sem3.school.configuration.db;

import fontys.sem3.school.repository.UserRepository;
import fontys.sem3.school.repository.entity.RoleEnum;
import fontys.sem3.school.repository.entity.UserEntity;
import fontys.sem3.school.repository.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@AllArgsConstructor
public class DatabaseDummyDataInitializer {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void populateDatabaseInitialDummyData() {
        if (isDatabaseEmpty()) {
            insertUsers();
        }
    }

    private boolean isDatabaseEmpty() {
        return userRepository.count() == 0;
    }

    private void insertUsers() {
        UserEntity teacher = UserEntity.builder()
                .username("teacher@fontys.nl")
                .password(passwordEncoder.encode("secret"))
                .created_at(LocalDateTime.now().minusDays(1))
                .is_active(true)
                .build();
        UserRoleEntity teacherRole = UserRoleEntity.builder().role(RoleEnum.TEACHER).user(teacher).build();
        teacher.setUserRoles(Set.of(teacherRole));

        UserEntity student1 = UserEntity.builder()
                .username("student1@fontys.nl")
                .password(passwordEncoder.encode("secret"))
                .created_at(LocalDateTime.now().minusDays(2))
                .is_active(false)
                .build();
        UserRoleEntity student1Role = UserRoleEntity.builder().role(RoleEnum.STUDENT).user(student1).build();
        student1.setUserRoles(Set.of(student1Role));
        userRepository.save(student1);

        UserEntity student2 = UserEntity.builder()
                .username("student2@fontys.nl")
                .password(passwordEncoder.encode("secret"))
                .created_at(LocalDateTime.now().minusDays(3))
                .is_active(true)
                .build();
        UserRoleEntity student2Role = UserRoleEntity.builder().role(RoleEnum.STUDENT).user(student2).build();
        student2.setUserRoles(Set.of(student2Role));
        userRepository.save(student2);
    }
}
