package controllers;

import models.*;
import org.junit.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

@With(Secure.class
)
public class Admin extends Controller {
    @Before
    static void setConnectedUser() {
        if(Secure.Security.isConnected()) {
            User user = User.find("byEmail", Secure.Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }

    public static void index(){
        String username = Security.connected();
        List<Post> posts = Post.find("author.email",username).fetch();
        render(posts);
    }
    public static void form(Long id){
        if (id != null){
            Post post = Post.findById(id);
            render(id);
        }
        render();
    }
    public static void save(Long id,String title,String content,String tags){

        Post post;
        if (id == null){
            User author = User.find("byEmail",Security.connected()).first();
            post = new Post(author,title,content);
        }else {
            post = Post.findById(id);
            post.title = title;
            post.content = content;
            post.tags.clear();;
        }
        for (String tag : tags.split("\\s+")){
            if (tag.trim().length() > 0){
                post.tags.add(Tag.findOrCreateByName(tag));
            }
        }
        validation.valid(post);
        if (validation.hasErrors()){
            render("@form",post);
        }
        post.save();
        index();
    }

}

