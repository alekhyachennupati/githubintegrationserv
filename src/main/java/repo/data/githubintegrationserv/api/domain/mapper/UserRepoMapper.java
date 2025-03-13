package repo.data.githubintegrationserv.api.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import repo.data.githubintegrationserv.api.domain.githubuser.UserRepo;
import repo.data.githubintegrationserv.api.domain.response.Repositories;

import java.util.List;

/**
 * Mapper that converts GitHub repository details to UserRepo response DTO
 */
@Mapper
public interface UserRepoMapper {
    UserRepoMapper INSTANCE = Mappers.getMapper(UserRepoMapper.class);

    @Mapping(source = "htmlUrl", target = "url")
    Repositories UserRepoMapperToRepositoriesDTO(UserRepo userRepoDetails);

    List<Repositories> UserRepoMapperToRepositoriesDTO(List<UserRepo> userRepoDetails);
}