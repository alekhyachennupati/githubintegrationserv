package repo.data.githubintegrationserv.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import repo.data.githubintegrationserv.api.domain.githubuser.UserRepo;

import java.util.List;

@Service
@Slf4j
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    RestClient restClient;

    @Value("${github.users.api.baseurl}")
    private String github_base_url;

    /**
     * This method makes an HTTP GET request to GitHub's API to fetch a list of repositories
     * for the provided username. The results are cached using the "userRepo" cache to optimize
     * performance for subsequent requests with the same username.
     *
     * @param userName GitHub username to get the repositories for.
     * @return a list of {@link UserRepo} with repository details.
     * @throws RestClientException if an error occurs while making the request or processing the response
     */
    @Override
    @Cacheable("userRepo")
    public List<UserRepo> getUserRepoDetails(String userName) {
        return restClient
                .get()
                .uri(github_base_url + userName + "/repos")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
