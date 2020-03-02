package com.rmp.rmp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<User> users;

	@ManyToMany
	@JoinTable(name = "Role_has_ModulePermiso", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "modulePermiso_id"))
	private List<ModulePermiso> modulePermisos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ModulePermiso> getModulePermisos() {
		return modulePermisos;
	}

	public void setModulePermisos(List<ModulePermiso> modulePermisos) {
		this.modulePermisos = modulePermisos;
	}

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}
	
	public void addModulePermiso(ModulePermiso theModulePermiso) {
		if(modulePermisos == null) {
			modulePermisos = new  ArrayList<>();
		}
		
		modulePermisos.add(theModulePermiso);
	}

}
