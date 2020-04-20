package ada.spd.startup.Domains;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "STARTUP")
public class Startup {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String startupName;
    private String duration;
    private String briefInfo;
    private String information;
    private double investAmount;
    private double invested;
    private String picture;
    private int clickcount;
    private String category;


    @OneToMany(mappedBy = "startup", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<UserStartup> userStartups = new ArrayList<>();


    @OneToMany(mappedBy = "startup", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ToDo> toDos = new ArrayList<>();


    public Startup(String startupName, String duration, String briefInfo, String information, double investAmount, double invested, String picture, int clickcount, String category) {
        this.startupName = startupName;
        this.duration = duration;
        this.briefInfo = briefInfo;
        this.information = information;
        this.investAmount = investAmount;
        this.invested = invested;
        this.picture = picture;
        this.clickcount = clickcount;
        this.category = category;
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

    public double getInvested() {
        return invested;
    }

    public void setInvested(double invested) {
        this.invested = invested;
    }

    public double getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(double investAmount) {
        this.investAmount = investAmount;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getClickcount() {
        return clickcount;
    }

    public void setClickcount(int clickcount) {
        this.clickcount = clickcount;
    }

    public List<UserStartup> getUserStartups() {
        return userStartups;
    }

    public void setStartupList(List<UserStartup> userStartups) {
        this.userStartups = userStartups;
    }


    public void addStartupList(UserStartup userStartup) {
        userStartups.add(userStartup);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}



