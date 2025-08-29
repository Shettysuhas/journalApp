package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.Entities.JournalEntry;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/all")
    public ResponseEntity<?> GetALLJournals()
    {
       List<JournalEntry> jentry =journalEntryService.GetAllEntry();
       if(jentry==null || jentry.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(jentry,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> CreateEntry(@RequestBody JournalEntry jEntry)
    {
        try {
            jEntry.setDate(LocalDateTime.now());
            journalEntryService.SaveEntry(jEntry);
            return new ResponseEntity<>(jEntry,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> GetJournalByID(@PathVariable ObjectId myid)
    {
        Optional<JournalEntry> entry= journalEntryService.GetEntryById(myid);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> DeleteJournalByID(@PathVariable ObjectId myid)
    {
        Optional<JournalEntry> entry= journalEntryService.GetEntryById(myid);
        if(entry.isPresent()){
            journalEntryService.DeleteEntryById(myid);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @PutMapping("/id/{myid}")
    public ResponseEntity<?>  UpdateJournal(@PathVariable ObjectId myid,@RequestBody JournalEntry jEntry)
    {
        JournalEntry oldentry=journalEntryService.GetEntryById(myid).orElse(null);
        if(oldentry!=null)
        {
            oldentry.setJournalTitle((jEntry.getJournalTitle()!="" && jEntry.getJournalTitle()!=null)? jEntry.getJournalTitle() : oldentry.getJournalTitle());
            oldentry.setJournalEntry((jEntry.getJournalEntry()!="" && jEntry.getJournalEntry()!=null)? jEntry.getJournalEntry() : oldentry.getJournalEntry());
            journalEntryService.SaveEntry(oldentry);
            return new ResponseEntity<>(oldentry,HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
