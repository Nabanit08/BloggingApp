package com.masai.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.masai.entities.Category;
import com.masai.entities.Comment;
import com.masai.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	
private  Integer postId;
//@NotEmpty
private String title;
//@NotEmpty
private String content;

private String imageName;
private Date addedDate;
private CategoryDTO category;
private UserDTO user;
private List<CommentDTO> comments=new ArrayList<>();
}
