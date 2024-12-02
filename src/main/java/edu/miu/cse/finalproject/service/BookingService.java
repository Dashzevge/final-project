package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<BookingResponseDTO> addBooking(BookingRequestDTO dto);
    Optional<BookingResponseDTO> findBookingById(Long id);
    List<BookingResponseDTO> findAllBookings();
    Optional<BookingResponseDTO> updateBooking(Long id, BookingRequestDTO dto);
    void deleteBooking(Long id);

    BookingResponseDTO createBookingFromClient(BookingRequestDTO dto);
}
