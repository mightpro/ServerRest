package fr.jbavril.restServer.service;

import java.util.Collection;
import java.util.stream.Stream;

import fr.jbavril.restServer.model.Role;

public interface RoleService {
	
	Role findByRoleName(String roleName);

	Collection<Role> getAllRoles();
	
	Stream<Role> getAllRolesStream();
}
