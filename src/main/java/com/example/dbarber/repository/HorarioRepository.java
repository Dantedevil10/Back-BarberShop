package com.example.dbarber.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dbarber.models.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long>{

    boolean existsByStartAtBetweenOrEndAtBetween(OffsetDateTime startAt, 
    OffsetDateTime endAt, OffsetDateTime startAt2,OffsetDateTime endAt2);

    boolean existsByStartAtBetweenOrEndAtBetweenAndIdNot(OffsetDateTime startAt, 
    OffsetDateTime endAt,OffsetDateTime startAt2, OffsetDateTime endAt2, Long id);

        @Query("""
                SELECT COUNT(h) > 0 FROM Horario h 
                WHERE (:startAt < h.endAt AND :endAt > h.startAt)
                """)
    boolean existsByOverlappingTime(@Param("startAt") OffsetDateTime startAt, @Param("endAt") OffsetDateTime endAt);

}
