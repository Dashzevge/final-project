package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.review.request.ReviewRequestDTO;
import edu.miu.cse.finalproject.dto.review.response.ReviewResponseDTO;
import edu.miu.cse.finalproject.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "content", target = "content")
    @Mapping(source = "rating", target = "rating")
    Review toEntity(ReviewRequestDTO dto);
    @Mapping(source = "content", target = "content")
    @Mapping(source = "rating", target = "rating")
    ReviewResponseDTO toResponse(Review entity);
}
