package demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "client_session", schema = "public", catalog = "SportDB")
@IdClass(ClientSessionEntityPK.class)
public class ClientSessionEntity {
    private String mail;
    private int sessionId;
    private boolean isPaid;
    private ClientEntity clientByMail;
    private SessionEntity sessionBySessionId;

    @Id
    @Column(name = "mail", nullable = false, length = -1)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Id
    @Column(name = "session_id", nullable = false)
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "is_paid", nullable = false)
    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @ManyToOne
    @JoinColumn(name = "mail", referencedColumnName = "email", nullable = false, insertable = false, updatable = false)
    public ClientEntity getClientByMail() {
        return clientByMail;
    }

    public void setClientByMail(ClientEntity clientByMail) {
        this.clientByMail = clientByMail;
    }

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "session_id", nullable = false, insertable = false, updatable = false)
    public SessionEntity getSessionBySessionId() {
        return sessionBySessionId;
    }

    public void setSessionBySessionId(SessionEntity sessionBySessionId) {
        this.sessionBySessionId = sessionBySessionId;
    }
}
