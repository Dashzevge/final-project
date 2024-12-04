package edu.miu.cse.finalproject.dto.booking.response;

import edu.miu.cse.finalproject.util.BookingStatus;

import java.time.LocalDateTime;

public record BookingResponseDTO(
        Long id,
        Long userId,
        Long jobId,
        BookingStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}

