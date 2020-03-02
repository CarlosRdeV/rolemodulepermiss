package com.rmp.rmp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "module")
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "module", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<ModulePermiso> modulesPermisos;

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

	public List<ModulePermiso> getModulesPermisos() {
		return modulesPermisos;
	}

	public void setModulesPermisos(List<ModulePermiso> modulesPermisos) {
		this.modulesPermisos = modulesPermisos;
	}

	public Module() {

	}

	public Module(String name) {
		this.name = name;
	}

}
