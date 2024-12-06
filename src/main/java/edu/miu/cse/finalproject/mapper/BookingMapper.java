package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    // Mapping BookingRequestDTO to Booking
    public Booking toEntity(BookingRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setStatus(dto.getStatus());
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());

        return booking;
    }

    // Mapping Booking entity to BookingResponseDTO
    public BookingResponseDTO toResponse(Booking entity) {
        if (entity == null) {
            return null;
        }

        BookingResponseDTO response = new BookingResponseDTO();
        response.setStatus(entity.getStatus());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());

        // Map job.id to jobId and professional.id to userId in DTO
        if (entity.getProfessional() != null) {
            response.setUserId(entity.getProfessional().getId());
        }

        if (entity.getJob() != null) {
            response.setJobId(entity.getJob().getId());
        }

        return response;
    }
}
