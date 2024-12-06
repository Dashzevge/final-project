package edu.miu.cse.finalproject.dto.address.response;

public class AddressResponseDTO {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private Long userId;

    // Getters
    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AddressResponseDTO() {
    }

    public AddressResponseDTO(Long id, String street, String city, String state, String zipCode, Long userId) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.userId = userId;
    }
}
