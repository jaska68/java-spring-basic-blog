package com.pluralsight.blog;

import com.pluralsight.blog.data.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pluralsight.blog.model.Post;

import java.util.List;

@Controller
public class BlogController {

    private PostRepository postRepository;
    //private List<Post> posts;

    public BlogController(PostRepository pr){
        this.postRepository = pr;
    }

    public List<Post> listPosts(){
        List<Post> posts = postRepository.getAllPosts();
        return posts;
    }

    @RequestMapping("/post/{id}")
    public String postDetails(@PathVariable Long id, ModelMap modelMap){
        Post post = postRepository.findById(id);
        modelMap.put("post", post);
        return "post-details";
    }

    @RequestMapping("/")
    public String listPosts(ModelMap mm){
        //mm.put("title", "Blog Post 1");
        mm.put("posts", listPosts());
        return "home";
    }

}
