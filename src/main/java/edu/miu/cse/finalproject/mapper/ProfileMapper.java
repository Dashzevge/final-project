package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.dto.profile.response.ProfileResponseDTO;
import edu.miu.cse.finalproject.model.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfileMapper {

        private final AddressMapper addressMapper;
        private final SkillMapper skillMapper;
        private final CertificationMapper certificationMapper;

        public ProfileMapper(AddressMapper addressMapper, SkillMapper skillMapper, CertificationMapper certificationMapper) {
                this.addressMapper = addressMapper;
                this.skillMapper = skillMapper;
                this.certificationMapper = certificationMapper;
        }

        // Mapping ProfileRequestDTO to Profile entity
        public Profile toEntity(ProfileRequestDTO dto) {
                if (dto == null) {
                        return null;
                }

                Profile profile = new Profile();
                profile.setBio(dto.getBio());
                profile.setPhoneNumber(dto.getPhoneNumber());
                profile.setExperienceYears(dto.getExperienceYears());

                // Map address using the AddressMapper
                if (dto.getAddress() != null) {
                        profile.setAddress(addressMapper.toEntity(dto.getAddress()));
                }

                return profile;
        }

        // Mapping Profile entity to ProfileResponseDTO
        public ProfileResponseDTO toResponse(Profile entity) {
                if (entity == null) {
                        return null;
                }

                ProfileResponseDTO response = new ProfileResponseDTO();
                response.setId(entity.getId());
                response.setBio(entity.getBio());
                response.setPhoneNumber(entity.getPhoneNumber());
                response.setExperienceYears(entity.getExperienceYears());

                // Map address using the AddressMapper
                if (entity.getAddress() != null) {
                        response.setAddress(addressMapper.toResponse(entity.getAddress()));
                }

                // Map skills using the SkillMapper
                if (entity.getSkills() != null) {
                        response.setSkills(entity.getSkills().stream()
                                .map(skillMapper::toResponse)
                                .collect(Collectors.toList()));
                }

                // Map certifications using the CertificationMapper
                if (entity.getCertifications() != null) {
                        response.setCertifications(entity.getCertifications().stream()
                                .map(certificationMapper::toResponse)
                                .collect(Collectors.toList()));
                }

                return response;
        }
}
