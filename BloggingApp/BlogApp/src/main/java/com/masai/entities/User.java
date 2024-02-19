package com.masai.entities;






import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.masai.payloads.CommentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@Column(name="user_name",nullable=false,length=100)
	@NotNull
private String name;
	@NotNull
	@Email
private String email;
	@NotNull
private String password;
	@NotNull
private String about;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
private List<Post> posts=new ArrayList<>();		
@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
private List<Comment> comments=new ArrayList<>();
}
