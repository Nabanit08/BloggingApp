 package com.masai.payloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.masai.entities.Comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
private int id;
@NotEmpty
private String name;
@Email(message="Address is not valid!")
private String email;
@NotEmpty
@Size(min=3,max=10,message="Password must be min of 3 chars and max of 10 chars!")
private String password;
@NotEmpty
private String about;
private List<CommentDTO> comments=new ArrayList<>();
}
