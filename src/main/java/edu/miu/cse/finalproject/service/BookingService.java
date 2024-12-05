package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.job.JobNotFoundException;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.util.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<BookingResponseDTO> addBooking(BookingRequestDTO dto) throws JobNotFoundException, UserNotFoundException;
    Optional<BookingResponseDTO> findBookingById(Long id) throws BookingNotFoundException;
    List<BookingResponseDTO> findAllBookings();
    Optional<BookingResponseDTO> updateBooking(Long id, BookingRequestDTO dto) throws BookingNotFoundException;
    List<BookingResponseDTO> findAllBookingsByProfessionalId(Long professionalId) throws BookingNotFoundException;
    Optional<BookingResponseDTO> updateBookingStatus(Long bookingId, BookingStatus status) throws BookingNotFoundException;
    Optional<BookingResponseDTO> completeBooking(Long bookingId) throws BookingNotFoundException;
    void deleteBooking(Long id) throws BookingNotFoundException;
}
