package com.javelin.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	private User user_id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "blog_id")
	private List<BlogPost> blogPosts;

	public Blog() {
	}

	public Blog(String name, String description, User user_id, List<BlogPost> blogPosts) {
		this.name = name;
		this.description = description;
		this.user_id = user_id;
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
		return getUser_id().equals(blog.getUser_id());

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		result = 31 * result + getUser_id().hashCode();
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

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public List<BlogPost> getBlogPosts() {
		return blogPosts;
	}

	public void setBlogPosts(List<BlogPost> blogPosts) {
		this.blogPosts = blogPosts;
	}
}
