package com.hexhoc.springbootblog.user;

import com.hexhoc.springbootblog.article.ArticleService;
import com.hexhoc.springbootblog.category.CategoryService;
import com.hexhoc.springbootblog.comment.CommentService;
import com.hexhoc.springbootblog.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class UserController {

    CategoryService categoryService;
    ArticleService articleService;
    TagService tagService;
    CommentService commentService;
    UserService userService;

    @Autowired
    public UserController(CategoryService categoryService,
                          ArticleService articleService,
                          TagService tagService,
                          CommentService commentService,
                          UserService userService){
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping({ "", "/", "/index", "/index.html"})
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

    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "The verification code cannot be empty");
            return "admin/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "User name or password cannot be empty");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "Verification code error");
            return "admin/login";
        }
        User user = userService.login(userName, password);
        if (user != null) {
            session.setAttribute("loginUser", user.getUsername());
            session.setAttribute("loginUserId", user.getId());
            //session expiration time is set to 7200 seconds, which is two hours
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "Login failed");
            return "admin/login";
        }
    }

}
