package com.hexhoc.springbootblog.category;

import com.hexhoc.springbootblog.article.Article;
import com.hexhoc.springbootblog.common.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    };

    public Long getTotalCategories(){
        return categoryRepository.count();
    }

    @Override
    public PageResult getBlogCategoryPage(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page-1, limit);
        List<Category> categoriesPage = categoryRepository.findAll(pageRequest).getContent();
        int total = (int)categoryRepository.count();
        PageResult pageResult = new PageResult(categoriesPage, total, limit, page);

        return pageResult;
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        // TODO: 25.07.2021 add trnsaction and try catch

        Category category = new Category();
        category.setName(categoryName);
        category.setIcon(categoryIcon);
        category.setGrade(1000);
        category.setCreateTime(LocalDateTime.now());
        category.setIsDeleted(false);

        categoryRepository.save(category);

        return true;
    }

    @Override
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        // TODO: 25.07.2021 add trnsaction and try catch

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return false;
        }

        Category category = categoryOptional.get();
        category.setName(categoryName);
        category.setIcon(categoryIcon);

        categoryRepository.save(category);

        return true;
    }

    @Override
    public Boolean deleteBatch(List<Integer> ids) {

        // TODO: 25.07.2021 add trnsaction and try catch

        List<Category> categories = categoryRepository.findAllById(ids);
        categoryRepository.deleteAll(categories);

        return true;

    }

}
