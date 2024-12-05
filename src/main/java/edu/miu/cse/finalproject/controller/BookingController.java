package edu.miu.cse.finalproject.controller;


import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.job.JobNotFoundException;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.service.BookingService;
import edu.miu.cse.finalproject.util.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<?> findAllBooking() {
        List<BookingResponseDTO> allBooking = bookingService.findAllBookings();
        return ResponseEntity.status(HttpStatus.OK).body(allBooking);
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO BookingRequestDTO) throws JobNotFoundException, UserNotFoundException {
        BookingResponseDTO savedBooking = bookingService.addBooking(BookingRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> findBookingById(@PathVariable Long id) throws BookingNotFoundException {
        return bookingService.findBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO updatedBooking) throws BookingNotFoundException {
        BookingResponseDTO newBooking = bookingService.updateBooking(id, updatedBooking).get();
        return new ResponseEntity<>(newBooking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) throws BookingNotFoundException{
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<BookingResponseDTO> completeBooking(@PathVariable Long id) throws BookingNotFoundException {
        BookingResponseDTO completedBooking = bookingService.completeBooking(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(completedBooking);
    }
    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByProfessionalId(@PathVariable Long professionalId) throws BookingNotFoundException{
        List<BookingResponseDTO> bookings = bookingService.findAllBookingsByProfessionalId(professionalId);
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingResponseDTO> updateBookingStatus(@PathVariable Long bookingId, @RequestParam BookingStatus status) throws BookingNotFoundException{
        BookingResponseDTO updatedBooking = bookingService.updateBookingStatus(bookingId, status).get();
        return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
    }
}
