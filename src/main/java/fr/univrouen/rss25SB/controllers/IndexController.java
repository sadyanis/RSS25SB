package fr.univrouen.rss25SB.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "Hello rss25SB !!";
    }
    @GetMapping("/home")
    public String home(){
        return "Hello rss25SB Home";
    }


}
