package fr.jbavril.restServer.service;

import java.util.Collection;
import java.util.stream.Stream;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbavril.restServer.dao.RoleRepository;
import fr.jbavril.restServer.model.Role;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public Collection<Role> getAllRoles() { //Avant Java 8
		return IteratorUtils.toList(roleRepository.findAll().iterator());
	}

	@Override
	public Stream<Role> getAllRolesStream() { //Java 8
		return roleRepository.getAllRolesStream();
	}
	

}
