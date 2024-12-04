package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.mapper.BookingMapper;
import edu.miu.cse.finalproject.mapper.UserMapper;
import edu.miu.cse.finalproject.model.Booking;
import edu.miu.cse.finalproject.model.Job;
import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.repository.BookingRepository;
import edu.miu.cse.finalproject.repository.JobRepository;
import edu.miu.cse.finalproject.repository.UserRepository;
import edu.miu.cse.finalproject.service.BookingService;
import edu.miu.cse.finalproject.util.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static edu.miu.cse.finalproject.service.impl.UserServiceImpl.isAvailable;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;




    @Override
    public Optional<BookingResponseDTO> addBooking(BookingRequestDTO dto) {
//        User client = userRepository.findById(dto.userId())
//                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + dto.userId()));

        Job job = jobRepository.findById(dto.jobId())
                .orElseThrow(() -> new EntityNotFoundException("Job not found with ID: " + dto.jobId()));

        User professional = userRepository.findById(dto.userId()) // Assuming you pass the professional's ID in the booking
                .orElseThrow(() -> new EntityNotFoundException("Professional not found with ID: " + dto.userId()));

        // Validate if the professional is available
//        if (!professional.isAvailability()) {
//            throw new AccessDeniedException("Professional is not available during the selected time.");
//        }

        // Create and save the booking
        Booking booking = new Booking();
        booking.setJob(job);
        booking.setProfessional(professional);
        booking.setStatus(dto.status());
        booking.setStartDate(dto.startDate());
        booking.setEndDate(dto.endDate());

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
    public Optional<BookingResponseDTO> completeBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        if (!"IN_PROGRESS".equals(booking.getStatus())) {
            throw new IllegalStateException("Booking cannot be marked as complete in its current state.");
        }

        booking.setStatus("COMPLETED"); // Update status
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

//    private Job findJobById(Long jobId){
//      return jobRepository.findById(jobId)
//                .orElseThrow(() -> new EntityNotFoundException("Job not found with ID: " + jobId));
//    }
}
