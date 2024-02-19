package com.masai.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.masai.payloads.CategoryDTO;
import com.masai.payloads.CommentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer postId;

private String title;
@Column(length=1000)
private String content;

private String imageName;

private Date addeddate;
@ManyToOne
//@JoinColumn(name="cat_id")
private Category category;
@ManyToOne
private User user;
@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
private Set<Comment> comments=new HashSet<>();
}
