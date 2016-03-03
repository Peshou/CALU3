package com.javelin.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Field(store = Store.NO)
	@NotNull
	@Column(nullable = false)
	private String name;

	@Field(store = Store.NO)
	private String description;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	

	@OneToMany(mappedBy = "blog",fetch = FetchType.EAGER)
	private List<BlogPost> blogPosts;

	public Blog() {
	}

	public Blog(String name, String description, User user, List<BlogPost> blogPosts) {
		this.name = name;
		this.description = description;
		this.user = user;
		this.blogPosts = blogPosts;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Blog)) return false;

		Blog blog = (Blog) o;

		if (!getId().equals(blog.getId())) return false;
		if (!getName().equals(blog.getName())) return false;
		if (getDescription() != null ? !getDescription().equals(blog.getDescription()) : blog.getDescription() != null)
			return false;
		return getUser().equals(blog.getUser());

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		result = 31 * result + getUser().hashCode();
		return result;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BlogPost> getBlogPosts() {
		return blogPosts;
	}

	public void setBlogPosts(List<BlogPost> blogPosts) {
		this.blogPosts = blogPosts;
	}
}
