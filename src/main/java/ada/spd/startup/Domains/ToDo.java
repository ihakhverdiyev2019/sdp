package ada.spd.startup.Domains;


import ada.spd.startup.ENUMS.ToDoEnum;

import javax.persistence.*;


@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;


    private String dueDate;

    private ToDoEnum progress;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "startup_id", nullable = false)
    private Startup startup;

    public ToDo() {
    }

    public ToDo(String name, String description, String dueDate, ToDoEnum progress) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.progress = progress;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public ToDoEnum getProgress() {
        return progress;
    }

    public void setProgress(ToDoEnum progress) {
        this.progress = progress;
    }


    public Startup getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup = startup;
    }
}
