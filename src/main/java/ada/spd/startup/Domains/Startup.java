package ada.spd.startup.Domains;

import javax.persistence.*;
import java.util.Optional;


@Entity
public class Startup {
    @Id()
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String startupName;
    private String duration;
    private String briefInfo;
    private String information;

    @ManyToOne
    private Startupper startupper;


    public Startup(long id,String startupName, String duration, String briefInfo, String information) {
       this.id = id;
        this.startupName = startupName;
        this.duration = duration;
        this.briefInfo = briefInfo;
        this.information = information;
    }

    public Startup() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartupName() {
        return startupName;
    }

    public void setStartupName(String startupName) {
        this.startupName = startupName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


    public Startupper getStartupper() {
        return startupper;
    }

    public void setStartupper(Startupper startupper) {
        this.startupper = startupper;
    }
}



