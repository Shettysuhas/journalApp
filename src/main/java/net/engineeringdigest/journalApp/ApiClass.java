package net.engineeringdigest.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiClass {
    @GetMapping("firstapi")
    public String MyFirstSpringApi(){
        return "hello";
    }
}
