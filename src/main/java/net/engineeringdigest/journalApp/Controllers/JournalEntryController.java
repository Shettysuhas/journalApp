package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.Entities.JournalEntry;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/all")
    public List<JournalEntry> GetALLJournals()
    {
       return journalEntryService.GetAllEntry();
    }

    @PostMapping
    public JournalEntry CreateEntry(@RequestBody JournalEntry jEntry)
    {
        jEntry.setDate(LocalDateTime.now());
        journalEntryService.SaveEntry(jEntry);
        return jEntry;
    }

    @GetMapping("/id/{myid}")
    public JournalEntry GetJournalByID(@PathVariable ObjectId myid)
    {
        return journalEntryService.GetEntryById(myid).orElse(null);
    }

    @DeleteMapping("/id/{myid}")
    public void DeleteJournalByID(@PathVariable ObjectId myid)
    {

         journalEntryService.DeleteEntryById(myid);
    }
    @PutMapping("/id/{myid}")
    public JournalEntry UpdateJournal(@PathVariable ObjectId myid,@RequestBody JournalEntry jEntry)
    {
        JournalEntry oldentry=journalEntryService.GetEntryById(myid).orElse(null);
        if(oldentry!=null)
        {
            oldentry.setJournalTitle((jEntry.getJournalTitle()!="" && jEntry.getJournalTitle()!=null)? jEntry.getJournalTitle() : oldentry.getJournalTitle());
            oldentry.setJournalEntry((jEntry.getJournalEntry()!="" && jEntry.getJournalEntry()!=null)? jEntry.getJournalEntry() : oldentry.getJournalEntry());
        }
        journalEntryService.SaveEntry(oldentry);
        return oldentry;
    }
}
