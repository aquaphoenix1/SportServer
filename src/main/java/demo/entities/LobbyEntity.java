package demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "lobby", schema = "public", catalog = "SportDB")
public class LobbyEntity {
    private int lobbyId;
    private String description;

    @Id
    @Column(name = "lobby_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
