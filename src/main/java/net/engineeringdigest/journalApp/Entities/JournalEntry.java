package net.engineeringdigest.journalApp.Entities;

import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection="Journal_entries")
@Data
public class JournalEntry {

    @Id
    private ObjectId journalId;
    private String journalTitle;
    private String journalEntry;
    private LocalDateTime date;

}
