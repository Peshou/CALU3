package com.javelin.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Intel on 01.03.2016.
 */
@Entity
@Table
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    public BlogPost(String name) {
        this.name = name;
    }

    public BlogPost() {
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogPost blogPost = (BlogPost) o;

        if (id != null ? !id.equals(blogPost.id) : blogPost.id != null) return false;
        return name != null ? name.equals(blogPost.name) : blogPost.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
