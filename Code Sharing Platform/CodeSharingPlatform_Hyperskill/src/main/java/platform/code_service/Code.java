package platform.code_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@JsonPropertyOrder({"date","code"})
@Entity
public class Code {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column
    private String date;

    @Column(columnDefinition = "text")
    private String code;

    @Column
    private Long time;

    @Column
    private int views;

    @JsonIgnore
    @Column
    private boolean timeIsRequired = false;

    @JsonIgnore
    @Column
    private boolean viewIsRequired = false;

    @JsonIgnore
    @Column
    private boolean hidden = false;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid_id")
    private UUIDEntity uuid;

    public Code() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public UUIDEntity getUuid() {
        return uuid;
    }

    public void setUuid(UUIDEntity uuid) {
        this.uuid = uuid;
    }

    public boolean isTimeIsRequired() {
        return timeIsRequired;
    }

    public void setTimeIsRequired(boolean timeIsRequired) {
        this.timeIsRequired = timeIsRequired;
    }

    public boolean isViewIsRequired() {
        return viewIsRequired;
    }

    public void setViewIsRequired(boolean viewIsRequired) {
        this.viewIsRequired = viewIsRequired;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
