package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entities.JournalEntry;
import net.engineeringdigest.journalApp.Entities.User;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    @Autowired
    private UserRepository userRepository;

    public void SaveUser(User user){
        userRepository.save(user);
    }
    public List<User> GetAllUsers(){
        return  userRepository.findAll();
    }
    public Optional<User> GetUserById(ObjectId id){
        return  userRepository.findById(id);
    }
    public void DeleteUserById(ObjectId id){
        userRepository.deleteById(id);
    }
    public User FindByUserName(String username){
        return userRepository.findByuserName(username);
    }
}

