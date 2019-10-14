package demo.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "session", schema = "public", catalog = "SportDB")
public class SessionEntity {
    private int sessionId;
    private int price;
    private String description;
    private Date startDate;
    private Date endDate;
    private LobbyEntity lobbyByLobbyId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false)
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JoinColumn(name = "lobby_id", referencedColumnName = "lobby_id", nullable = false)
    public LobbyEntity getLobbyByLobbyId() {
        return lobbyByLobbyId;
    }

    public void setLobbyByLobbyId(LobbyEntity lobbyByLobbyId) {
        this.lobbyByLobbyId = lobbyByLobbyId;
    }
}
