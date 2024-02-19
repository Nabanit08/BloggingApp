package com.masai.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Services.CategoryService;
import com.masai.payloads.ApiResponse;
import com.masai.payloads.CategoryDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
	private CategoryService categoryService ;
    
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO categoryDto){
    	CategoryDTO createCat=this.categoryService.createCategory(categoryDto);
    	return new ResponseEntity<>(createCat,HttpStatus.CREATED);
    }
    
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer categoryId,@RequestBody @Valid CategoryDTO categoryDto){
    	CategoryDTO updateCat=this.categoryService.updateCategory(categoryDto, categoryId);
    	return new ResponseEntity<>(updateCat,HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteCategory/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
    	this.categoryService.deleteCategory(catId);
    	return new ResponseEntity<>(new ApiResponse("Category deleted succesfully!", true),HttpStatus.OK);
    }
    
    @GetMapping("/getCategory/{catId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId){
    	CategoryDTO catDto=this.categoryService.getCategory(catId);
    	return new ResponseEntity<>(catDto,HttpStatus.OK);
    }
    
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDTO>> getAll(){
    	List<CategoryDTO> cats= this.categoryService.getCategories();
    	return new ResponseEntity<>(cats,HttpStatus.OK);
    }
    
}
