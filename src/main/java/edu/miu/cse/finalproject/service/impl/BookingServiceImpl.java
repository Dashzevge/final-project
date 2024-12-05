package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;
import edu.miu.cse.finalproject.mapper.BookingMapper;
import edu.miu.cse.finalproject.mapper.UserMapper;
import edu.miu.cse.finalproject.model.Booking;
import edu.miu.cse.finalproject.model.Job;
import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.repository.BookingRepository;
import edu.miu.cse.finalproject.repository.JobRepository;
import edu.miu.cse.finalproject.repository.UserRepository;
import edu.miu.cse.finalproject.service.BookingService;
import edu.miu.cse.finalproject.util.BookingStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;



    @Override
    public Optional<BookingResponseDTO> addBooking(BookingRequestDTO dto) {

        Job job = jobRepository.findById(dto.jobId())
                .orElseThrow(() -> new EntityNotFoundException("Job not found with ID: " + dto.jobId()));

        User professional = userRepository.findById(dto.userId()) // Assuming you pass the professional's ID in the booking
                .orElseThrow(() -> new EntityNotFoundException("Professional not found with ID: " + dto.userId()));

        if (!professional.isAvailability()) {
            throw new AccessDeniedException("Professional is not available during the selected time.");
        }

        Booking booking = new Booking();
        booking.setJob(job);
        booking.setProfessional(professional);
        booking.setStatus(dto.status());
        booking.setStartDate(dto.startDate());
        booking.setEndDate(dto.endDate());
        //After booking make professional unavailable
        professional.setAvailability(false);
        userRepository.save(professional);
        Booking savedBooking = bookingRepository.save(booking);
        return Optional.of(bookingMapper.toResponse(savedBooking));
    }

    @Override
    public Optional<BookingResponseDTO> findBookingById(Long id) throws BookingNotFoundException{
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
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
    public Optional<BookingResponseDTO> updateBooking(Long id, BookingRequestDTO dto) throws BookingNotFoundException{
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
        booking.setStartDate(dto.startDate());
        booking.setEndDate(dto.endDate());
        booking.setStatus(dto.status());
        Booking updatedBooking = bookingRepository.save(booking);
        return Optional.of(bookingMapper.toResponse(updatedBooking));
    }

    @Override
    public List<BookingResponseDTO> findAllBookingsByProfessionalId(Long professionalId) throws BookingNotFoundException {

        List<Booking> bookings = bookingRepository.findAllBookingsByProfessionalId(professionalId);

        if(bookings.size() > 0)
            return bookingRepository.findAllBookingsByProfessionalId(professionalId)
                    .stream()
                    .map(bookingMapper::toResponse).toList();
        else
           throw new BookingNotFoundException("Booking not found with Professional: " + professionalId);

    }

    @Override
    public Optional<BookingResponseDTO> updateBookingStatus(Long bookingId, BookingStatus status) throws BookingNotFoundException{
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        booking.setStatus(status);
        Booking updatedBooking = bookingRepository.save(booking);
        return Optional.of(bookingMapper.toResponse(updatedBooking));
    }

    @Override
    public Optional<BookingResponseDTO> completeBooking(Long bookingId) throws BookingNotFoundException{
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        if (!BookingStatus.IN_PROCESS.equals(booking.getStatus())) {
            throw new IllegalStateException("Booking cannot be marked as complete in its current state.");
        }

        User professional = findUserById(booking.getProfessional().getId());
        professional.setAvailability(true);
        booking.setStatus(BookingStatus.COMPLETED);
        Booking updatedBooking = bookingRepository.save(booking);

        return Optional.of(bookingMapper.toResponse(updatedBooking));
    }

    @Override
    public void deleteBooking(Long id) throws BookingNotFoundException {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }

    private boolean isValidStatus(BookingStatus status) {

           return (status.equals(BookingStatus.IN_PROCESS) || status.equals(BookingStatus.CONFIRMED));

    }

    private User findUserById(Long professionalId){
      return  userRepository.findById(professionalId)
               .orElseThrow(() -> new EntityNotFoundException("Professional not found with ID: " + professionalId));
    }
}
