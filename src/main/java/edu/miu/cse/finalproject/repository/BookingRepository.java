package edu.miu.cse.finalproject.repository;

import edu.miu.cse.finalproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.professional.id = :professionalId")
    List<Booking> findAllBookingsByProfessionalId(@Param("professionalId") Long professionalId);
}
