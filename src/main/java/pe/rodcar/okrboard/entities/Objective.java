package pe.rodcar.okrboard.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Entity
@Table(name="objectives")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Objective {

	//@JsonIgnore

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	//@Column(name="is_active", nullable=true)
	//private boolean isActive;

	@Column(name="title", nullable=true, length=500)
	private String title;

	@Column(name="description", nullable=true, length=1000)
	private String description;

	@JsonProperty
	@Column(name="start_date", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@JsonProperty
	@Column(name="due_date", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@JsonProperty
	@Column(name="owner", nullable=true, length=7)
	private String owner;

	@JsonProperty
	@Column(name="created_by", nullable=true)
	private String createdBy;

	@JsonProperty
	@Column(name="created_on", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@JsonProperty
	@Column(name="updated_by", nullable=true)
	private String updatedBy;

	@JsonProperty
	@Column(name="updated_on", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	@OneToMany(mappedBy="objective", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<KeyResult> keyResults;

	@OneToMany(mappedBy="objective", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Sponsor> sponsors;

	//@JsonIgnore
	//@NotNull(message="The Key Result need to be associated with a team")
	//@Column(name="organization", nullable=true, length=500)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", referencedColumnName = "id")
	private Organization organization;



	@JsonIgnore
	@NotNull(message="the objective need to be associated with an user")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id", nullable=false)
	private User user;
	
	public Objective() {
		//this.keyResults = new ArrayList<>();
		//this.sponsors = new ArrayList<>();
		this.user = new User();
		this.createdOn =  this.updatedOn = new Date();
		//this.user.id = setId(1);

	}

	public Long getId() {
		return id;
	}

	//@JsonIgnore
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn (Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy (String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy (String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<KeyResult> getKeyResults() {
		return keyResults;
	}

	public void setKeyResults(List<KeyResult> keyResults) {
		this.keyResults = keyResults;
	}

	public List<Sponsor> getSponsors() {
		return sponsors;
	}

	public void setSponsors(List<Sponsor> sponsors) {
		this.sponsors = sponsors;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

//	public boolean isActive() {
//		return isActive;
//	}
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}
}


