package com.hexhoc.springbootblog.category;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();

    Long getTotalCategories();

}
