package edu.miu.cse.finalproject.dto.profile.request;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;

public class ProfileRequestDTO {
    private String bio;
    private String phoneNumber;
    private int experienceYears;
    private AddressRequestDTO address;

    // Getters
    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public AddressRequestDTO getAddress() {
        return address;
    }

    // Setters
    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }

    public ProfileRequestDTO(String bio, String phoneNumber, int experienceYears, AddressRequestDTO address) {
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.experienceYears = experienceYears;
        this.address = address;
    }
}
