package repo.data.githubintegrationserv.api.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * User response DTO
 */
@Getter
@Setter
public class User {
    private String userName;
    private String displayName;
    private String avatar;
    private String geoLocation;
    private String email;
    private String url;
    private String createdAt;
    private List<Repositories> repos;
}