package com.hexhoc.springbootblog.category;

import com.hexhoc.springbootblog.common.util.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();

    Long getTotalCategories();

    /**
     * Query classified paging data
     * @return
     */
    PageResult getBlogCategoryPage(int page, int limit);


    /**
     * Add classification data
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(List<Integer> ids);


}
