package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.model.Certification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificationMapper {
    Certification toEntity(CertificationRequestDTO dto);
    CertificationResponseDTO toResponse(Certification entity);
}
