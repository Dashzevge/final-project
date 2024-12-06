package edu.miu.cse.finalproject.dto.skill.request;

public class SkillRequestDTO {
    private String name;
    private String description;
    private Long profileId;

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getProfileId() {
        return profileId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public SkillRequestDTO(String name, String description, Long profileId) {
        this.name = name;
        this.description = description;
        this.profileId = profileId;
    }
}

