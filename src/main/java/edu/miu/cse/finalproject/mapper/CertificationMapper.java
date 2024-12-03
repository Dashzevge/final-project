package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.model.Certification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificationMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "authority", target = "authority")
    @Mapping(source = "issueDate", target = "issueDate")
    @Mapping(source = "expiryDate", target = "expiryDate")
    Certification toEntity(CertificationRequestDTO dto);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "authority", target = "authority")
    @Mapping(source = "issueDate", target = "issueDate")
    @Mapping(source = "expiryDate", target = "expiryDate")
    CertificationResponseDTO toResponse(Certification entity);
}
