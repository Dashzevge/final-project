package edu.miu.cse.finalproject.dto.booking.request;

import edu.miu.cse.finalproject.util.BookingStatus;

import java.time.LocalDateTime;

public record BookingRequestDTO(
        Long userId,
        Long jobId,
        BookingStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}

