package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.dto.profile.response.ProfileResponseDTO;
import edu.miu.cse.finalproject.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, SkillMapper.class, CertificationMapper.class})
public interface ProfileMapper {

        @Mapping(source = "bio", target = "bio")
        @Mapping(source = "phoneNumber", target = "phoneNumber")
        @Mapping(source = "experienceYears", target = "experienceYears")
        @Mapping(source = "address", target = "address") // AddressRequestDTO is directly mapped
        Profile toEntity(ProfileRequestDTO dto);

        @Mapping(source = "id", target = "id")
        @Mapping(source = "bio", target = "bio")
        @Mapping(source = "phoneNumber", target = "phoneNumber")
        @Mapping(source = "experienceYears", target = "experienceYears")
        @Mapping(source = "address", target = "address") // Maps AddressResponseDTO
        @Mapping(source = "skills", target = "skills")   // Maps SkillResponseDTO entity
        @Mapping(source = "certifications", target = "certifications") // Maps CertificationResponseDTO entity
        ProfileResponseDTO toResponse(Profile entity);

}

