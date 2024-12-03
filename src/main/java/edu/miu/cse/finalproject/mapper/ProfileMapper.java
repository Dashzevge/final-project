package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.dto.profile.response.ProfileResponseDTO;
import edu.miu.cse.finalproject.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "experienceYears", target = "experienceYears")
    Profile toEntity(ProfileRequestDTO dto);
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "experienceYears", target = "experienceYears")
    ProfileResponseDTO toResponse(Profile entity);
}
