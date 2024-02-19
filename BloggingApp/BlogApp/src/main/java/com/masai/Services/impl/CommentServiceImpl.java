package com.masai.Services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.ResourceNotFoundException;
import com.masai.Repositories.CommentRepo;
import com.masai.Repositories.PostRepo;
import com.masai.Repositories.UserRepo;
import com.masai.Services.CommentService;
import com.masai.entities.Comment;
import com.masai.entities.Post;
import com.masai.entities.User;
import com.masai.payloads.CommentDTO;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId,Integer userId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id:"+postId+" not found!"));
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id:"+userId+" not found!"));
        Comment comment=this.modelMapper.map(commentDTO, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment with id:"+commentId+" not found!"));
        this.commentRepo.delete(comment);
        
	}

}
