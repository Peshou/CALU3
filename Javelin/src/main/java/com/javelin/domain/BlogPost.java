package com.javelin.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Intel on 01.03.2016.
 */
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

	@ManyToOne()
	@JoinColumn(name = "blog_id", referencedColumnName = "id")
	private Blog blog_id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeAdded;

	@NotNull
	@Field(store = Store.NO)
	@Column(nullable = false)
	private String text;

	public BlogPost() {
	}

	public BlogPost(Long id, String name, Blog blog) {
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

	public Blog getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Blog blog_id) {
		this.blog_id = blog_id;
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

	@Override
	public String toString() {
		return "BlogPost{" + "id=" + id + ", name='" + name + '\'' + '}';
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
