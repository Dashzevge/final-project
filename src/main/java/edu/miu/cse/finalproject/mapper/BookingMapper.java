package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "status", target = "status")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    Booking toEntity(BookingRequestDTO dto);
    @Mapping(source = "status", target = "status")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "professional.id", target = "userId")
    @Mapping(source = "job.id", target = "jobId")
    BookingResponseDTO toResponse(Booking entity);
}
