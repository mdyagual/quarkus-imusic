package ec.com.imusic.entity;

import lombok.Data;

import java.util.UUID;


@Data
public class Client {

    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String name;
    private String surname;

    private String mail;
    private Integer age;
    private String gender;

    private Boolean subscriptionActivate;

    //private List<Album> albums;
    //private List<Playlist> playlists;

}
