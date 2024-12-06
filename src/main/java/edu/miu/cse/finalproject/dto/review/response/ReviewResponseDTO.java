package edu.miu.cse.finalproject.dto.review.response;

public class ReviewResponseDTO {
    private String content;
    private Integer rating;

    // Getters
    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }

    // Setters
    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ReviewResponseDTO() {
    }

    public ReviewResponseDTO(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
    }
}
