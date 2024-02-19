package com.masai.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Services.CommentService;
import com.masai.payloads.CommentDTO;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService; 
	
	@PostMapping("/comment/post/{postId}/user/{userId}")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,@PathVariable Integer postId,@PathVariable Integer userId){
		CommentDTO createdComment=this.commentService.createComment(commentDTO, postId, userId);
		return new ResponseEntity<CommentDTO>(createdComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<>("Comment deleted sucessfully!",HttpStatus.OK);
	}
}
