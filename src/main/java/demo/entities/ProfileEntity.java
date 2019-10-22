package demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "profile", schema = "public")
public class ProfileEntity {
    private int profileId;
    private String description;

    @Id
    @Column(name = "profile_id", nullable = false)
    @GeneratedValue(generator="profileSeq")
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
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
