package repo.data.githubintegrationserv.api.domain.githubuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    private String login;
    private Integer id;
    private String name;
    private String location;
    private String email;

    @JsonProperty("avatar_url")
    public String avatarUrl;

    @JsonProperty("html_url")
    public String htmlUrl;

    @JsonProperty("created_at")
    public String createdAt;

}
