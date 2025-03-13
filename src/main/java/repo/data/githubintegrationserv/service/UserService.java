package repo.data.githubintegrationserv.service;

import repo.data.githubintegrationserv.api.domain.githubuser.User;


public interface UserService {

    User getUserDetails(String userName);
}
