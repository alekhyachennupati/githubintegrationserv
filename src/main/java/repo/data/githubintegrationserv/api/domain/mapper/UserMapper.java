package repo.data.githubintegrationserv.api.domain.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import repo.data.githubintegrationserv.api.domain.githubuser.User;

/**
 * Mapper that converts User object from Github to user response DTO
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "login", target = "userName")
    @Mapping(source = "name", target = "displayName")
    @Mapping(source = "avatarUrl", target = "avatar")
    @Mapping(source = "location", target = "geoLocation")
    @Mapping(source = "htmlUrl", target = "url")
    repo.data.githubintegrationserv.api.domain.response.User UserMapperToUserResponseDTO(User user);

}
