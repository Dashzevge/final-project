package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.job.JobNotFoundException;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.service.BookingService;
import edu.miu.cse.finalproject.util.BookingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @Test
    void findAllBooking() {
        List<BookingResponseDTO> mockBookings = List.of(
                new BookingResponseDTO(1L, 1L, 1L, BookingStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new BookingResponseDTO(2L, 2L, 2L, BookingStatus.CONFIRMED, LocalDateTime.now(), LocalDateTime.now().plusHours(2))
        );

        when(bookingService.findAllBookings()).thenReturn(mockBookings);

        ResponseEntity<?> response = bookingController.findAllBooking();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBookings, response.getBody());

        verify(bookingService, times(1)).findAllBookings();
    }

    @Test
    void createBooking() throws JobNotFoundException, UserNotFoundException {
        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO(1L, 1L, BookingStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO(1L, 1L, 1L, BookingStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        when(bookingService.addBooking(bookingRequestDTO)).thenReturn(Optional.of(bookingResponseDTO));

        ResponseEntity<BookingResponseDTO> response = bookingController.createBooking(bookingRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookingResponseDTO, response.getBody());

        verify(bookingService, times(1)).addBooking(bookingRequestDTO);
    }

    @Test
    void findBookingById() throws BookingNotFoundException {
        Long bookingId = 1L;
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO(1L, 1L, 1L, BookingStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        when(bookingService.findBookingById(bookingId)).thenReturn(Optional.of(bookingResponseDTO));

        ResponseEntity<BookingResponseDTO> response = bookingController.findBookingById(bookingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingResponseDTO, response.getBody());

        verify(bookingService, times(1)).findBookingById(bookingId);
    }

    @Test
    void updateBooking() throws BookingNotFoundException {
        Long bookingId = 1L;
        BookingRequestDTO updatedBooking = new BookingRequestDTO(1L, 1L, BookingStatus.CONFIRMED, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        BookingResponseDTO updatedBookingResponse = new BookingResponseDTO(1L, 1L, 1L, BookingStatus.CONFIRMED, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        when(bookingService.updateBooking(bookingId, updatedBooking)).thenReturn(Optional.of(updatedBookingResponse));

        ResponseEntity<BookingResponseDTO> response = bookingController.updateBooking(bookingId, updatedBooking);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBookingResponse, response.getBody());

        verify(bookingService, times(1)).updateBooking(bookingId, updatedBooking);
    }

    @Test
    void deleteBooking() throws BookingNotFoundException {
        Long bookingId = 1L;

        doNothing().when(bookingService).deleteBooking(bookingId);

        ResponseEntity<Void> response = bookingController.deleteBooking(bookingId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(bookingService, times(1)).deleteBooking(bookingId);
    }

    @Test
    void completeBooking() throws BookingNotFoundException {
        Long bookingId = 1L;
        BookingResponseDTO completedBooking = new BookingResponseDTO(1L, 1L, 1L, BookingStatus.COMPLETED, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        when(bookingService.completeBooking(bookingId)).thenReturn(Optional.of(completedBooking));

        ResponseEntity<BookingResponseDTO> response = bookingController.completeBooking(bookingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(completedBooking, response.getBody());

        verify(bookingService, times(1)).completeBooking(bookingId);
    }

    @Test
    void getBookingsByProfessionalId() throws BookingNotFoundException {
        Long professionalId = 1L;
        List<BookingResponseDTO> bookings = List.of(
                new BookingResponseDTO(1L, 1L, 1L, BookingStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new BookingResponseDTO(2L, 1L, 2L, BookingStatus.CONFIRMED, LocalDateTime.now(), LocalDateTime.now().plusHours(2))
        );

        when(bookingService.findAllBookingsByProfessionalId(professionalId)).thenReturn(bookings);

        ResponseEntity<List<BookingResponseDTO>> response = bookingController.getBookingsByProfessionalId(professionalId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookings, response.getBody());

        verify(bookingService, times(1)).findAllBookingsByProfessionalId(professionalId);
    }

    @Test
    void updateBookingStatus() throws BookingNotFoundException {
        Long bookingId = 1L;
        BookingStatus status = BookingStatus.COMPLETED;
        BookingResponseDTO updatedBooking = new BookingResponseDTO(1L, 1L, 1L, status, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        when(bookingService.updateBookingStatus(bookingId, status)).thenReturn(Optional.of(updatedBooking));

        ResponseEntity<BookingResponseDTO> response = bookingController.updateBookingStatus(bookingId, status);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBooking, response.getBody());

        verify(bookingService, times(1)).updateBookingStatus(bookingId, status);
    }
}
