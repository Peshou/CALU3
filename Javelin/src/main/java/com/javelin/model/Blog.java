package com.javelin.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table
public class Blog  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Field
	@NotNull
	@Column(nullable = false)
	private String name;

	@Field
	@Column(length = 4000)
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	

	@OneToMany(mappedBy = "blog")
	//@LazyCollection(LazyCollectionOption.FALSE)
	private List<BlogPost> blogPosts;

	public Blog() {
	}

	public Blog(String name, String description, User user, List<BlogPost> blogPosts) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Blog{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' + '}';
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

		return true;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + description.hashCode();
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
