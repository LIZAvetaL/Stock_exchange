package stock_exchange.service.impl;

import stock_exchange.model.Role;
import stock_exchange.repository.RoleRepository;
import stock_exchange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRole(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
