package com.masai.Services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.ResourceNotFoundException;
import com.masai.Repositories.CategoryRepo;
import com.masai.Repositories.PostRepo;
import com.masai.Repositories.UserRepo;
import com.masai.Services.PostService;
import com.masai.entities.Category;
import com.masai.entities.Post;
import com.masai.entities.User;
import com.masai.payloads.CategoryDTO;
import com.masai.payloads.PostDTO;
import com.masai.payloads.PostResponse;
import com.masai.payloads.UserDTO;

import jakarta.validation.Valid;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Override
	public PostDTO createPost(PostDTO postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with "+userId+" not present!"));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category with "+categoryId+" not found!"));
		Post post= this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddeddate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDTO.class);
	}


	@Override
	public PostDTO updatePost(PostDTO postDto, Integer postId) {
		// TODO Auto-generated method stub

		Post p1=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with "+postId+" not found!"));
		Post post= this.modelMapper.map(postDto, Post.class);

		p1.setContent(postDto.getContent());
		p1.setTitle(postDto.getTitle());
		p1.setImageName(postDto.getImageName());
		Post newPost=this.postRepo.save(p1);
		
		return this.modelMapper.map(newPost, PostDTO.class);
		
		
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with postId:"+postId+" not found!"));
	    this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> posts=pagePost.getContent();
		List<PostDTO> allPosts=posts.stream().map(post->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(allPosts);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with "+postId+" not found!"));
		
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category of "+categoryId+" not found!"));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDTO> allPostsByCategory=posts.stream().map(post->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return allPostsByCategory;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with "+userId+" not found!"));
		List<Post> allPostsByUser=this.postRepo.findByUser(user);
		List<PostDTO> posts=allPostsByUser.stream().map(post->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return posts;
	}

	@Override
	public List<PostDTO> searchPosts(String keywords) {
		// TODO Auto-generated method stub
		List<Post> searchedPosts=this.postRepo.findByTitleContaining(keywords);
		List<PostDTO> alldtos=searchedPosts.stream().map(post->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return alldtos;
	}


//	@Override
//	public PostDTO updatePost(PostDTO postDTO, Integer postId, Integer userId, Integer categoryId) {
//		// TODO Auto-generated method stub
//		
//		return null;
//	}






}
