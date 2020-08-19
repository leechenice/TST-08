package frank.springbootstudy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private String username;
    private String password;
}
