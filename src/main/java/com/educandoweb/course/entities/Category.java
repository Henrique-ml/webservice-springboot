package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	// Associação com a classe Product
	
	// Como ainda faltam coisas (provisório) o JPA não está reconhecendo a coleção Set, implementar essa Annotation
	// - @Transient: impedir que o JPA tente interpretar isso
	@Transient
	
	// Não será feito com List
	// Será feito com Set, que representa um conjunto e garante que não terá um Product com mais de uma ocorrência da mesma categoria...
	// ...ou seja, o MESMO produto não pode ter a MESMA Category MAIS DE UMA VEZ
	// - new HashSet<>(): garantir que a coleção comece VAZIA e INSTANCIADA e não NULA
	private Set<Product> products = new HashSet<>();
	
	public Category() {
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	
	// Não se cria um método SET para coleções para elas não serem substituidas por uma outra nova coleção

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}