package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entities.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void SaveEntry(JournalEntry jentry){
        journalEntryRepository.save(jentry);
    }
    public List<JournalEntry> GetAllEntry(){
        return  journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> GetEntryById(ObjectId id){
        return  journalEntryRepository.findById(id);
    }
    public void DeleteEntryById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}

