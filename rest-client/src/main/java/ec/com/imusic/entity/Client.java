package ec.com.imusic.entity;

import lombok.Data;


@Data
public class Client {

    private String id;
    private String name;
    private String surname;

    private String mail;
    private Integer age;
    private String gender;

    private Boolean subscriptionActivate;

    //private List<Album> albums;
    //private List<Playlist> playlists;

}
