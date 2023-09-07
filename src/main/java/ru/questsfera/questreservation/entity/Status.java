package ru.questsfera.questreservation.entity;

import jakarta.persistence.*;
import ru.questsfera.questreservation.dto.StatusType;

import java.util.*;

@Entity
@Table(name = "statuses", schema = "quest_reservations")
public class Status {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "status_type")
    @Enumerated(value = EnumType.STRING)
    private StatusType type;

    @ManyToMany(mappedBy = "statuses")
    private Set<Quest> quests = new HashSet<>();

    public Status(StatusType statusType) {
        this.id = statusType.getId();
        this.type = statusType;
    }

    public Status() {}

    public static Set<Status> getDefaultStatuses() {
        Set<Status> defaultStatuses = new HashSet<>();
        defaultStatuses.add(new Status(StatusType.NEW_RESERVE));
        defaultStatuses.add(new Status(StatusType.CANCEL));
        return defaultStatuses;
    }

    public static List<Status> getUserStatuses() {
        List<Status> userStatuses = new ArrayList<>();
        userStatuses.add(new Status(StatusType.CONFIRMED));
        userStatuses.add(new Status(StatusType.NOT_COME));
        userStatuses.add(new Status(StatusType.COMPLETED));
        return userStatuses;
    }

    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusType getType() {
        return type;
    }

    public void setType(StatusType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status status)) return false;
        return id != null && Objects.equals(getId(), status.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
