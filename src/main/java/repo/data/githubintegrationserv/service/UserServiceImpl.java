package repo.data.githubintegrationserv.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import repo.data.githubintegrationserv.api.domain.githubuser.User;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${github.users.api.baseurl}")
    String github_base_url;

    @Autowired
    RestClient restClient;

    /**
     * This method makes an HTTP GET request to GitHub's API to fetch the given user details.
     *  The results are cached using the "user" cache to optimize performance for subsequent requests with the same username.
     *
     * @param userName GitHub username to fetch the details from
     * @return a {@link User} object representing the user's details
     * @throws RestClientException if an error occurs while making the request or processing the response
     */
    @Override
    @Cacheable("user")
    public User getUserDetails(String userName) {
        return restClient
                .get()
                .uri(github_base_url+userName)
                .retrieve()
                .body(User.class);
    }
}
