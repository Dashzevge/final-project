package edu.miu.cse.finalproject.dto.job.response;

import edu.miu.cse.finalproject.util.Category;
import edu.miu.cse.finalproject.util.JobStatus;

public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private JobStatus status;
    private Category category;
    private Long clientId;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public JobStatus getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }

    public Long getClientId() {
        return clientId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public JobResponseDTO() {
    }

    public JobResponseDTO(Long id, String title, String description, Double price, JobStatus status, Category category, Long clientId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.category = category;
        this.clientId = clientId;
    }
}
