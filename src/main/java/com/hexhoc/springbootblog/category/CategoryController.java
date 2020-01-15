package com.hexhoc.springbootblog.category;

import com.hexhoc.springbootblog.article.ArticleService;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {


    ArticleService articleService;
    CategoryService categoryService;
    ConfigService configService;

    @Autowired
    public CategoryController(ArticleService articleService,CategoryService categoryService, ConfigService configService){
        this.articleService = articleService;
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

    /**
     * Category list page
     *
     * @return
     */
    @GetMapping({"/category/{categoryName}"})
    public String category(Model model, @PathVariable("categoryName") String categoryName) {
        return category(model, categoryName, 1);
    }

    /**
     * Category list page
     *
     * @return
     */
    @GetMapping({"/category/{categoryName}/{page}"})
    public String category(Model model, @PathVariable("categoryName") String categoryName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = articleService.getBlogsPageByCategory(categoryName, page);
        model.addAttribute("blogPageResult", blogPageResult);
        model.addAttribute("pageName", "classification");
        model.addAttribute("pageUrl", "category");
        model.addAttribute("keyword", categoryName);
//        model.addAttribute("newBlogs", articleService.getArticlesForIndexPage(1));
//        model.addAttribute("hotBlogs", articleService.getArticlesForIndexPage(0));
//        model.addAttribute("hotTags", tagService.getBlogTagCountForIndex());
        model.addAttribute("configurations", configService.getAllConfigs());
        return "blog/list";
    }

}
