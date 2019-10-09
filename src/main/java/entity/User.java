package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String login;
    private String email;
    private String password;
}
