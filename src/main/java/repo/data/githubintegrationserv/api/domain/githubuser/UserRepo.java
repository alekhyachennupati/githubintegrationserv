package repo.data.githubintegrationserv.api.domain.githubuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRepo{

    private int id;
    private String name;

    @JsonProperty("html_url")
    private String htmlUrl;

}

