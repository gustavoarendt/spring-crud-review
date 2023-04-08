package crud.review.api.repository;

import crud.review.api.model.Medic;
import crud.review.api.model.enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select m from Medic m
            where
            m.active = 1
            and
            m.specialty = :specialty
            and m.id not in(
                select a.medic.id from Appointment a
                where
                a.dateTime = :dateTime
            )
            order by rand()
            limit 1
            """)
    Medic randomAndAvailableMedic(Specialty specialty, LocalDateTime dateTime);

    @Query("""
            select m.active
            from Medic m
            where
            m.id = :id
            """)
    Boolean findActiveById(Long id);
}
