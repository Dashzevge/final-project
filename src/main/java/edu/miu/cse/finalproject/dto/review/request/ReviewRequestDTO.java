package edu.miu.cse.finalproject.dto.review.request;

public class ReviewRequestDTO {
    private Long bookingId;
    private String content;
    private Integer rating;

    // Getters
    public Long getBookingId() {
        return bookingId;
    }

    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }

    // Setters
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ReviewRequestDTO(Long bookingId, String content, Integer rating) {
        this.bookingId = bookingId;
        this.content = content;
        this.rating = rating;
    }
}
