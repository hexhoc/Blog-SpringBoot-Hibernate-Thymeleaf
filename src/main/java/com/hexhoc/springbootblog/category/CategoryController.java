package com.hexhoc.springbootblog.category;

import com.hexhoc.springbootblog.config.ConfigService;
import com.hexhoc.springbootblog.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    TagService tagService;
    CategoryService categoryService;
    ConfigService configService;

    @Autowired
    public CategoryController(TagService tagService,CategoryService categoryService, ConfigService configService){
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.configService = configService;
    }

    /**
     * Categories Page (including classification data and label data)
     *
     * @return
     */
    @GetMapping({"/categories"})
    public String categories(Model model) {
//        model.addAttribute("hotTags", tagService.getTagsWithArticlesCount());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("pageName", "Category page");
        model.addAttribute("configurations", configService.getAllConfigs());

        return "blog/categories";
    }

}
