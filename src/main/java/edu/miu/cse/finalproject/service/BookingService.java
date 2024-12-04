package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.util.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<BookingResponseDTO> addBooking(BookingRequestDTO dto);
    Optional<BookingResponseDTO> findBookingById(Long id);
    List<BookingResponseDTO> findAllBookings();
    Optional<BookingResponseDTO> updateBooking(Long id, BookingRequestDTO dto);
    List<BookingResponseDTO> findAllBookingsByProfessionalId(Long professionalId);
    Optional<BookingResponseDTO> updateBookingStatus(Long bookingId, BookingStatus status);
    Optional<BookingResponseDTO> completeBooking(Long bookingId);
    void deleteBooking(Long id);
}
