package ec.com.imusic.dto;

import lombok.Data;

@Data
public class ClientDTO {

    private String id;
    private String name;
    private String surname;

    private String mail;
    private Integer age;
    private String gender;

    private Boolean subscriptionActivate;
}
