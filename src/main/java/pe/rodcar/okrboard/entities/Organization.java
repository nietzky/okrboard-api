package pe.rodcar.okrboard.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="organizations")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Organization {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //@Column(name="is_active", nullable=true)
    //private boolean isActive;

    @Column(name="name", nullable=true, length=500)
    private String organizationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setTeamName(String departmentName) {
        this.organizationName = organizationName;
    }

    @OneToMany(mappedBy="organization", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Objective> objectives;

}
