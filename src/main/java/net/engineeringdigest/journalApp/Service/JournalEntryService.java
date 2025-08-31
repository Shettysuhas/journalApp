package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entities.JournalEntry;
import net.engineeringdigest.journalApp.Entities.User;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void SaveEntry(JournalEntry jentry, String username){
        try {
            User user = userEntryService.FindByUserName(username);
            jentry.setDate(LocalDateTime.now());
            journalEntryRepository.save(jentry);
            user.getJournalEntryList().add(jentry);
            userEntryService.SaveUser(user);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("error",e);
        }

    }
    public void SaveEntry(JournalEntry jentry){
        jentry.setDate(LocalDateTime.now());
        journalEntryRepository.save(jentry);
    }
    public List<JournalEntry> GetAllEntry(){
        return  journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> GetEntryById(ObjectId id){
        return  journalEntryRepository.findById(id);
    }
    public void DeleteEntryById(ObjectId id, String username){
        User user = userEntryService.FindByUserName(username);
        user.getJournalEntryList().removeIf(x -> x.getJournalId().equals(id));
        userEntryService.SaveUser(user);
        journalEntryRepository.deleteById(id);
    }
}

