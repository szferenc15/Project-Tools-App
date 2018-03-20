package app.sport_mates.class_interface;

import java.sql.Date;
import java.sql.Time;

public class NewEvent {
    private String name;
    private String locale;
    private short price;
    private Date dateOfEvent;
    private Time start;
    private Time finish;
    private short headcount;
    private String audience;
    private String description;

    public String getName() {
        return name;
    }
    
    public String getLocale() {
        return locale;
    }

    public short getPrice() {
        return price;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public Time getStart() {
        return start;
    }

    public Time getFinish() {
        return finish;
    }

    public short getHeadcount() {
        return headcount;
    }

    public String getAudience() {
        return audience;
    }
    
    public String getDescription() {
        return description;
    }
}