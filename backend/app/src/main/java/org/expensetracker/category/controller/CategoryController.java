package org.expensetracker.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.expensetracker.category.dto.CategoryRequestDto;
import org.expensetracker.category.dto.CategoryResponseDto;
import org.expensetracker.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(
            @Valid @RequestBody CategoryRequestDto requestDto
    ) {
        CategoryResponseDto category = categoryService.createCategory(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryResponseDto> getCategoryById(
//            @PathVariable UUID id
//    ) {
//        return ResponseEntity.ok(categoryService.getCategoryById(id));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable UUID id,
            @Valid @RequestBody CategoryRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                categoryService.updateCategory(id, requestDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable UUID id
    ) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}