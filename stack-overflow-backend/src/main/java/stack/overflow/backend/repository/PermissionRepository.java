package stack.overflow.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stack.overflow.backend.model.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
