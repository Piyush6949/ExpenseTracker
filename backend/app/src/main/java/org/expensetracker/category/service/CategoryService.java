package org.expensetracker.category.service;

import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.category.dto.CategoryRequestDto;
import org.expensetracker.category.dto.CategoryResponseDto;
import org.expensetracker.category.entity.Category;
import org.expensetracker.category.repository.CategoryRepository;
import org.expensetracker.common.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {

        User user = UserUtil.getCurrentUser();

        Category category = modelMapper.map(requestDto, Category.class);
        category.setUser(user);

        return modelMapper.map(categoryRepository.save(category), CategoryResponseDto.class);
    }

    public List<CategoryResponseDto> getAllCategories() {

        User user = UserUtil.getCurrentUser();

        return categoryRepository.findAllByUser(user)
                .stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                .toList();
    }

    public CategoryResponseDto updateCategory(UUID id, CategoryRequestDto requestDto) {

        User user = UserUtil.getCurrentUser();
        Category category = categoryRepository.findByIdAndUser(id,user)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        modelMapper.map(requestDto, category);

        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory, CategoryResponseDto.class);
    }

    public void deleteCategory(UUID id) {

        User user = UserUtil.getCurrentUser();
        Category category = categoryRepository.findByIdAndUser(id,user)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
}