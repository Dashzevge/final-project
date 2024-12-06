package edu.miu.cse.finalproject.dto.skill.response;

public class SkillResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long profileId;

    // Getters
    public Long getId() {
        return id;
    }

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
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public SkillResponseDTO() {
    }

    public SkillResponseDTO(Long id, String name, String description, Long profileId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.profileId = profileId;
    }
}
