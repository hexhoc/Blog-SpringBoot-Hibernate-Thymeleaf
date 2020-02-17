package com.hexhoc.springbootblog.category;

import com.hexhoc.springbootblog.article.ArticleService;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.common.util.PostResponse;
import com.hexhoc.springbootblog.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    // TODO: 23.07.2021  move these endpoint to ArticleController
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



    @GetMapping("/admin/categories")
    public String categoryPage(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }

    /**
     * Category List
     */
    @RequestMapping(value = "/admin/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public PostResponse list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return PostResponse.genFailResult("Parameter exception!");
        }
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        return PostResponse.genSuccessResult(categoryService.getBlogCategoryPage(page, limit));
    }

    @RequestMapping(value = "/admin/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public PostResponse save(@RequestParam("categoryName") String categoryName,
                       @RequestParam("categoryIcon") String categoryIcon) {
        if (StringUtils.isEmpty(categoryName)) {
            return PostResponse.genFailResult("Please enter the category name!");
        }
        if (StringUtils.isEmpty(categoryIcon)) {
            return PostResponse.genFailResult("Please select the category icon!");
        }
        if (categoryService.saveCategory(categoryName, categoryIcon)) {
            return PostResponse.genSuccessResult();
        } else {
            return PostResponse.genFailResult("Duplicate category name");
        }
    }


    /**
     * Category modification
     */
    @RequestMapping(value = "/admin/categories/update", method = RequestMethod.POST)
    @ResponseBody
    public PostResponse update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon) {
        if (StringUtils.isEmpty(categoryName)) {
            return PostResponse.genFailResult("Please enter the category name!");
        }
        if (StringUtils.isEmpty(categoryIcon)) {
            return PostResponse.genFailResult("Please select the category icon!");
        }
        if (categoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            return PostResponse.genSuccessResult();
        } else {
            return PostResponse.genFailResult("Duplicate category name");
        }
    }

    /**
     * Category delete
     */
    @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
    @ResponseBody
    public PostResponse delete(@RequestBody List<Integer> ids) {
        if (ids.size() <1) {
            return PostResponse.genFailResult("Parameter exception!");
        }
        if (categoryService.deleteBatch(ids)) {
            return PostResponse.genSuccessResult();
        } else {
            return PostResponse.genFailResult("Delete failed");
        }
    }


}
