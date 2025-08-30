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
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/all/{username}")
    public ResponseEntity<?> GetALLJournalsOfUser(@PathVariable String username)
    {
        User user = userEntryService.FindByUserName(username);
        List<JournalEntry> jentry =user.getJournalEntryList();
       if(jentry==null || jentry.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(jentry,HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> CreateEntryForUser(@RequestBody JournalEntry jEntry,@PathVariable String username)
    {

        try {
            journalEntryService.SaveEntry(jEntry,username);
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

    @DeleteMapping("/id/{username}/{myid}")
    public ResponseEntity<?> DeleteJournalByID(@PathVariable String username,@PathVariable ObjectId myid)
    {
        Optional<JournalEntry> entry= journalEntryService.GetEntryById(myid);
        if(entry.isPresent()){
            journalEntryService.DeleteEntryById(myid,username);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @PutMapping("/id/{username}/{myid}")
    public ResponseEntity<?>  UpdateJournal(@PathVariable ObjectId myid,@RequestBody JournalEntry jEntry,@PathVariable String username)
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
