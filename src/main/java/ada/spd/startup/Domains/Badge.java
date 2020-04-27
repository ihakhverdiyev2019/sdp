package ada.spd.startup.Domains;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String name;

    private String textIcon;


    private String dataTitle;

    private String dataContent;

    private String divIcon;

    @OneToMany(mappedBy = "badge", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BadgeUser> badgeUsers = new ArrayList<>();


    public Badge(String name, String textIcon, String dataTitle, String dataContent, String divIcon) {
        this.name = name;
        this.textIcon = textIcon;
        this.dataTitle = dataTitle;
        this.dataContent = dataContent;
        this.divIcon = divIcon;
    }

    public Badge() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextIcon() {
        return textIcon;
    }

    public void setTextIcon(String textIcon) {
        this.textIcon = textIcon;
    }

    public String getDivIcon() {
        return divIcon;
    }

    public void setDivIcon(String divIcon) {
        this.divIcon = divIcon;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }
}
