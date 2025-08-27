package net.engineeringdigest.journalApp.Entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection="Journal_entries")
public class JournalEntry {

    @Id
    private ObjectId journalId;
    private String journalTitle;
    private String journalEntry;
    private LocalDateTime date;


    public ObjectId getJournalId() {
        return journalId;
    }

    public void setJournalId(ObjectId journalId) {
        this.journalId = journalId;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
