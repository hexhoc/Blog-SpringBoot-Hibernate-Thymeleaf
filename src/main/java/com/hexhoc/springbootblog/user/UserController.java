package com.hexhoc.springbootblog.user;

import com.hexhoc.springbootblog.article.ArticleService;
import com.hexhoc.springbootblog.category.CategoryService;
import com.hexhoc.springbootblog.comment.CommentService;
import com.hexhoc.springbootblog.tag.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class UserController {

    CategoryService categoryService;
    ArticleService articleService;
    TagService tagService;
    CommentService commentService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(Model model) {
        model.addAttribute("path", "index");
        model.addAttribute("categoryCount", categoryService.getTotalCategories());
        model.addAttribute("blogCount", articleService.getTotalArticle());
        //TODO add link
        //model.addAttribute("linkCount", linkService.getTotalLinks());
        model.addAttribute("tagCount", tagService.getTotalTags());
        model.addAttribute("commentCount", commentService.getTotalComments());
        return "admin/index";
    }


    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }



}
