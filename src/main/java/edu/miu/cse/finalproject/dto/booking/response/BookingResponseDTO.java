package edu.miu.cse.finalproject.dto.booking.response;

import java.time.LocalDateTime;

public record BookingResponseDTO(
        Long id,
        Long userId,
        Long jobId,
        String status,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}

