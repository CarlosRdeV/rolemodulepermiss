package com.rmp.rmp.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmp.rmp.entity.ModulePermiso;
import com.rmp.rmp.entity.Role;
import com.rmp.rmp.repository.ModulePermisoRepository;
import com.rmp.rmp.repository.RoleRepository;

@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private ModulePermisoRepository mpRepo;

	@GetMapping("/roles")
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	@GetMapping("/roles/{roleId}")
	public Role getRole(@PathVariable int roleId) {
		Optional<Role> tempRole = roleRepo.findById(roleId);
		Role theRole = null;
		if (tempRole.isPresent()) {
			theRole = tempRole.get();
		} else {
			throw new RuntimeException("Role id not found " + roleId);
		}
		return theRole;
	}

	@PostMapping("/roles")
	public Role addRole(@RequestBody Role theRole) {
		theRole.setId(0);

		roleRepo.save(theRole);

		return theRole;
	}

	@PutMapping("/roles")
	public Role updateRole(@RequestBody Role theUser) {

		roleRepo.save(theUser);

		return theUser;
	}

	@DeleteMapping("/roles/{roleId}")
	public String deleteRole(@PathVariable int roleId) {

		try {
			roleRepo.deleteById(roleId);
			return "Role deleted by id - " + roleId;
		} catch (Exception e) {
			throw new RuntimeException("Role id not found - " + roleId);
		}

	}

	@PutMapping("/roles/{roleId}/{modulePermisoId}")
	public Role addSomething(@PathVariable int roleId, @PathVariable int modulePermisoId) {
		
		//get role
		Optional<Role> tempRole = roleRepo.findById(roleId);
		Role theRole = null;
		if (tempRole.isPresent()) {
			theRole = tempRole.get();
		} else {
			throw new RuntimeException("Role id not found " + roleId);
		}
		
		//get module repo
		Optional<ModulePermiso> tempMp = mpRepo.findById(modulePermisoId);
		ModulePermiso theModulePermiso = null;
		if (tempMp.isPresent()) {
			theModulePermiso = tempMp.get();
		} else {
			throw new RuntimeException("Mp id not found " + modulePermisoId);
		}
		
		//add module repo to role
		theRole.addModulePermiso(theModulePermiso);
		
		//save role
		roleRepo.save(theRole);
		
		return theRole;
	}

}
