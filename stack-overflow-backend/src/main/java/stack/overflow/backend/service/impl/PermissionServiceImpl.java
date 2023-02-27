package stack.overflow.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stack.overflow.backend.model.entity.Permission;
import stack.overflow.backend.repository.PermissionRepository;
import stack.overflow.backend.service.PermissionService;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Transactional
    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }
}
