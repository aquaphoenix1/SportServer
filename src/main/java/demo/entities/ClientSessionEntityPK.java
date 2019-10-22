package demo.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ClientSessionEntityPK implements Serializable {
    private String mail;
    private int sessionId;

    public ClientSessionEntityPK(){}

    public ClientSessionEntityPK(String mail, int sessionId) {
        this.mail = mail;
        this.sessionId = sessionId;
    }

    @Column(name = "mail", nullable = false, length = -1)
    @Id
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "session_id", nullable = false)
    @Id
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
