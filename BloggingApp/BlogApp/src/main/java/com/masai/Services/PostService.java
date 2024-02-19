package com.masai.Services;

import java.util.List;

import com.masai.entities.Post;
import com.masai.payloads.PostDTO;
import com.masai.payloads.PostResponse;

import jakarta.validation.Valid;

public interface PostService {

	PostDTO createPost(PostDTO postDto,Integer userId,Integer categoryId);
	
//	PostDTO updatePost(PostDTO postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDTO getPostById(Integer postId);
	
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	List<PostDTO> getPostByUser(Integer userId);
	
	List<PostDTO> searchPosts(String keywords);

	PostDTO updatePost( PostDTO postDTO, Integer postId);
	
}
