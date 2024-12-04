package edu.miu.cse.finalproject.controller;


import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.service.BookingService;
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
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO BookingRequestDTO) {
        BookingResponseDTO savedBooking = bookingService.addBooking(BookingRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> findBookingById(@PathVariable Long id) {
        return bookingService.findBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO updatedBooking) {
        BookingResponseDTO newBooking = bookingService.updateBooking(id, updatedBooking).get();
        return new ResponseEntity<>(newBooking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<BookingResponseDTO> completeBooking(@PathVariable Long id) {
        BookingResponseDTO completedBooking = bookingService.completeBooking(id).get();
        return new ResponseEntity<>(completedBooking, HttpStatus.OK);
    }
}
