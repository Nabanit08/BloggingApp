package com.masai.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.masai.Services.FileService;
import com.masai.Services.PostService;
import com.masai.config.AppConstants;
import com.masai.entities.Post;
import com.masai.payloads.PostDTO;
import com.masai.payloads.PostResponse;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDTO createPost=this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber,
	@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false)Integer pageSize,
	@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false)String sortBy,
	@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false)String sortDir){
		
		PostResponse allPosts=this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
	}
	
	@GetMapping("/getAllByCategory/{categoryId}")
	public ResponseEntity<List<PostDTO>> getAllByCategory(@PathVariable Integer categoryId){
		List<PostDTO> getAllByCat=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(getAllByCat,HttpStatus.OK);
	}
	
	@GetMapping("/getAllByUser/{userId}")
	public ResponseEntity<List<PostDTO>> getAllByUser(@PathVariable Integer userId){
		List<PostDTO> getAllByUser=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(getAllByUser,HttpStatus.OK);
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		return new ResponseEntity<PostDTO>(this.postService.getPostById(postId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<String>("Post deleted succesfully!",HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchByKeyword(@PathVariable String keyword){
		List<PostDTO> allPosts=this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDTO>>(allPosts,HttpStatus.OK);
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Integer postId){
		PostDTO updatedPost=this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);		
	}
	
	@PostMapping("/uploadImage/{postId}")
	public ResponseEntity<PostDTO> uploadImage(@RequestParam("image")MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		PostDTO postDTO=this.postService.getPostById(postId);
		String name=this.fileService.uploadImage(path, image);
		postDTO.setImageName(name);
		PostDTO updatedPost= this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);
	}
	
	
	@GetMapping(value="post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response)throws IOException{
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
}
