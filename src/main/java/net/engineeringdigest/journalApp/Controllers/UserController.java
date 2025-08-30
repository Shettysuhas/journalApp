package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.Entities.JournalEntry;
import net.engineeringdigest.journalApp.Entities.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<User> GetAllUser(){
        return userEntryService.GetAllUsers();
    }

    @PostMapping
    public void CreateUser (@RequestBody User user){
        userEntryService.SaveUser(user);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> UpdatePassssword(@PathVariable String userName,@RequestBody User user){
        User userInDb=userEntryService.FindByUserName(userName);
        if(userInDb!= null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userEntryService.SaveUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
