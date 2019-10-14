package demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "trainer", schema = "public", catalog = "SportDB")
public class TrainerEntity {
    private int trainerId;
    private String name;
    private String surname;
    private int age;
    private ProfileEntity profileByProfileId;

    @Id
    @Column(name = "trainer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = -1)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    public ProfileEntity getProfileByProfileId() {
        return profileByProfileId;
    }

    public void setProfileByProfileId(ProfileEntity profileByProfileId) {
        this.profileByProfileId = profileByProfileId;
    }
}
