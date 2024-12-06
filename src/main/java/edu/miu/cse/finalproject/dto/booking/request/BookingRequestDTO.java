package edu.miu.cse.finalproject.dto.booking.request;

import edu.miu.cse.finalproject.util.BookingStatus;

import java.time.LocalDateTime;

public class BookingRequestDTO {
    private Long userId;
    private Long jobId;
    private BookingStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Getters
    public Long getUserId() {
        return userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BookingRequestDTO(Long userId, Long jobId, BookingStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        this.userId = userId;
        this.jobId = jobId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
