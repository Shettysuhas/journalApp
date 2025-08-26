package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.Entities.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries=new HashMap<>();

    @GetMapping("/all")
    public List<JournalEntry> GetALLJournals()
    {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean CreateEntry(@RequestBody JournalEntry jEntry)
    {
        journalEntries.put((long) jEntry.getJournalId(),jEntry);
        return true;
    }

    @GetMapping("/id/{myid}")
    public JournalEntry GetJournalByID(@PathVariable int myid){
        return journalEntries.get((long)myid);
    }
    @DeleteMapping("/id/{myid}")
    public JournalEntry DeleteJournalByID(@PathVariable int myid){
        return journalEntries.remove((long)myid);
    }
    @PutMapping("/id/{myid}")
    public JournalEntry UpdateJournal(@PathVariable int myid,@RequestBody JournalEntry jEntry){
       return journalEntries.put((long)myid,jEntry);
    }
}
