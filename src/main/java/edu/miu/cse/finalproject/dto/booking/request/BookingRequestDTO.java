package edu.miu.cse.finalproject.dto.booking.request;

import java.time.LocalDateTime;

public record BookingRequestDTO(
        Long userId,
        Long jobId,
        String status,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}

