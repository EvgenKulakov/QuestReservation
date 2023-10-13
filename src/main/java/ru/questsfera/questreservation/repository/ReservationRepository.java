package ru.questsfera.questreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.questsfera.questreservation.entity.Quest;
import ru.questsfera.questreservation.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    LinkedList<Reservation> findAllByQuestAndDateReserveOrderByTimeReserve(Quest quest, LocalDate date);

    boolean existsByQuest(Quest quest);

    void deleteByQuest(Quest quest);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reservation r " +
            "WHERE r.quest = :quest " +
            "AND r.dateReserve = :dateReserve " +
            "AND r.timeReserve = :timeReserve " +
            "AND r.statusType != 'CANCEL'")
    boolean existsByQuestAndDateReserveAndTimeReserve(
            @Param("quest") Quest quest,
            @Param("dateReserve") LocalDate dateReserve,
            @Param("timeReserve") LocalTime timeReserve
    );
}