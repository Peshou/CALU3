package com.javelin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Indexed
@Table
public class BlogPost {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Field(store = Store.NO)
	@Column(nullable = false)
	private String name;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "blog_id", referencedColumnName = "id")
	private Blog blog;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeAdded;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean public_post = true;

	@NotNull
	@Field(store = Store.NO)
	@Column(nullable = false)
	private String text;

	public BlogPost() {
	}

	public BlogPost(String name, Blog blog, Date timeAdded, boolean public_post, String text) {
		this.name = name;
		this.blog = blog;
		this.timeAdded = timeAdded;
		this.public_post = public_post;
		this.text = text;
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

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog_id) {
		this.blog = blog_id;
	}

	public Date getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(Date timeAdded) {
		this.timeAdded = timeAdded;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isPublic_post() {
		return public_post;
	}

	public void setPublic_post(boolean public_post) {
		this.public_post = public_post;
	}

	@Override
	public String toString() {
		return "BlogPost{" +
				"id=" + id +
				", name='" + name + '\'' +
				", blog=" + blog +
				", timeAdded=" + timeAdded +
				", public_post=" + public_post +
				", text='" + text + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BlogPost blogPost = (BlogPost) o;

		if (id != null ? !id.equals(blogPost.id) : blogPost.id != null)
			return false;
		return name != null ? name.equals(blogPost.name) : blogPost.name == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}


}
