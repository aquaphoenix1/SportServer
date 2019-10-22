package demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "lobby", schema = "public")
public class LobbyEntity {
    private int lobbyId;
    private String description;

    @Id
    @Column(name = "lobby_id", nullable = false)
    @GeneratedValue(generator="lobbySeq")
    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
