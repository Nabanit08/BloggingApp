package com.masai.Services;

import java.util.List;

import com.masai.payloads.CategoryDTO;

public interface CategoryService {
public CategoryDTO createCategory(CategoryDTO categoryDto);
public CategoryDTO updateCategory(CategoryDTO categoryDto,Integer categoryId);
public void deleteCategory(Integer categoryId);
public CategoryDTO getCategory(Integer categoryId);
public List<CategoryDTO> getCategories();
}
