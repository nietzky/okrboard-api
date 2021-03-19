package pe.rodcar.okrboard.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="sponsors")
public class Sponsor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="sid", nullable=true, length=10)
    private String sid;

    @Column(name="lastname", nullable=true, length=500)
    private String lastName;
    @Column(name="firstname", nullable=true, length=500)
    private String firstName;

    @JsonIgnore
    //@NotNull(message="The sponsor need to be associated with an objective")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objectives_id", nullable=false)
    private Objective objective;

    public Sponsor() {
        //this.progress = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

}
