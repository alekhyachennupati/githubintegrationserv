package repo.data.githubintegrationserv.service;

import repo.data.githubintegrationserv.api.domain.githubuser.UserRepo;
import java.util.List;


public interface RepositoryService {

    List<UserRepo> getUserRepoDetails(String userName);

}


