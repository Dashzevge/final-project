package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.mapper.BookingMapper;
import edu.miu.cse.finalproject.model.Booking;
import edu.miu.cse.finalproject.repository.BookingRepository;
import edu.miu.cse.finalproject.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Optional<BookingResponseDTO> addBooking(BookingRequestDTO dto) {
        Booking booking = bookingMapper.toEntity(dto);
        Booking savedBooking = bookingRepository.save(booking);
        return Optional.of(bookingMapper.toResponse(savedBooking));
    }

    @Override
    public Optional<BookingResponseDTO> findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + id));
        return Optional.of(bookingMapper.toResponse(booking));
    }

    @Override
    public List<BookingResponseDTO> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<BookingResponseDTO> updateBooking(Long id, BookingRequestDTO dto) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + id));
        booking.setStartDate(dto.startDate());
        booking.setEndDate(dto.endDate());
        booking.setStatus(dto.status());
        Booking updatedBooking = bookingRepository.save(booking);
        return Optional.of(bookingMapper.toResponse(updatedBooking));
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new EntityNotFoundException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }
}
