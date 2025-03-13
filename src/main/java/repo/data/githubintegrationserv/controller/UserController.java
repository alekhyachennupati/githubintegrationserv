package repo.data.githubintegrationserv.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import repo.data.githubintegrationserv.api.domain.githubuser.UserRepo;
import repo.data.githubintegrationserv.api.domain.mapper.UserMapper;
import repo.data.githubintegrationserv.api.domain.mapper.UserRepoMapper;
import repo.data.githubintegrationserv.api.domain.response.Repositories;
import repo.data.githubintegrationserv.api.domain.response.User;
import repo.data.githubintegrationserv.service.RepositoryService;
import repo.data.githubintegrationserv.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@Slf4j
public class UserController {

    UserMapper userMapper = UserMapper.INSTANCE;

    UserRepoMapper userRepoMapper = UserRepoMapper.INSTANCE;

    private final UserService userService;

    private final RepositoryService repositoryService;

    public UserController(UserService userService, RepositoryService repositoryService) {
        this.userService = userService;
        this.repositoryService = repositoryService;
    }

    /**
     * Regular expression that verifies if user input is a valid GitHub username. Otherwise, throws BAD_REQUEST exception
     * ^  Start of the string
     * [a-zA-Z\d]  The first character must be a letter (uppercase/lowercase) or a digit
     * (?:[a-zA-Z\d]|-(?=[a-zA-Z\d])){0,38}
     *     Allows letters, digits, and hyphens (-), but:
     *     A hyphen must be followed by a letter or digit (prevents consecutive or trailing hyphens)
     * $  End of the string
     * {0,38}  Ensures the total length is between 1 and 39 characters
     */
    private static final String GIT_HUB_USERNAME_REGEX = "^[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}$";


    /**
     * Retrives GitHub user and repository detalils and maps to response DTO.
     * @param userName the GitHub username to fetch data for
     * @return a {@link User} object containing user details and repositories
     * @throws ResponseStatusException if the username is not a valid GitHub username.
     */
    @GetMapping("/api/v1/user/{userName}")
    public User getUserRepositoryData(@PathVariable String userName) {
        log.info("getUserRepositoryData userName: {}", userName);
        if(!Pattern.matches(GIT_HUB_USERNAME_REGEX,userName))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Valid GitHub Username");

        repo.data.githubintegrationserv.api.domain.githubuser.User user = userService.getUserDetails(userName);
        List<UserRepo> userRepoDetailsList = repositoryService.getUserRepoDetails(userName);
        User userDTO =  userMapper.UserMapperToUserResponseDTO(user);
        List<Repositories> repositoryDTOList = userRepoMapper.UserRepoMapperToRepositoriesDTO(userRepoDetailsList);
        userDTO.setRepos(repositoryDTOList);
        return userDTO;
    }
}
