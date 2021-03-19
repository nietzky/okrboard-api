package pe.rodcar.okrboard.entities;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="keyresults")
public class KeyResult {
	@JsonProperty
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name ="kr_id",referencedColumnName ="id")
	//@OneToOne(mappedBy = "keyresult", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Metric metric;
	
	@Column(name="title", nullable=true, length=500)
	private String title;

	@JsonProperty
	@Column(name="owner", nullable=true, length=7)
	private String owner;

	@JsonProperty

	@Column(name="start_date", nullable=true)
	//private Date startDate;
	@Temporal(TemporalType.DATE)
	private Calendar startDate;

	@JsonProperty
	@Column(name="due_date", nullable=true)
	@Temporal(TemporalType.DATE)
	private Calendar dueDate;

	@JsonProperty
	private boolean isActive;
	//@NotNull
	//@Size(
	//		max = 1023,
	//		message = "The description of a key result may not be longer than 1023 characters.")
	@Column(name="description", nullable=true, length=1000)
	private String description;

	@Column(name="whyitmatters", nullable=true, length=1000)
	private String whyItMatters;
	
	@Column(name="progress", nullable=false)
	private Double progress;

	@JsonProperty
	@Column(name="created_by", nullable=false)
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

	@JsonIgnore
	//@NotNull(message="The Key Result need to be associated with an objective")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objectives_id", nullable=false)
	private Objective objective;

	//@JsonIgnore
	//@NotNull(message="The Key Result need to be associated with a team")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id", referencedColumnName = "id")
	private Team team;

	//@NotNull(message="The Key Result need to be associated with a product")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id")
	private KeyResultType type;

	public KeyResult() {

		this.type = new KeyResultType();
		this.metric = new Metric();
		this.product = new Product();
		this.team = new Team();
		this.progress = 0.0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWhyItMatters() {
		return whyItMatters;
	}
	public void setWhyItMatters(String whyItMatters) {
		this.whyItMatters = whyItMatters;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Double getProgress() {
		return progress;
	}
	public void setProgress(Double progress) {
		this.progress = progress;
	}
	public Calendar getStartDate() {return startDate;}
	public void setStartDate(Calendar startDate) {this.startDate = startDate;}
	public Date getUpdatedOn() {return updatedOn;}
	public void setUpdatedOn(Date updatedOn) {this.updatedOn = updatedOn;	}
	public String getUpdatedBy() {return updatedBy;	}
	public void setUpdatedBy(String updatedOn) {this.updatedBy = updatedBy;	}
	public Calendar getDueDate() {return dueDate;}
	public void setDueDate(Calendar dueDate) {this.dueDate = dueDate;}
	public Objective getObjective() {
		return objective;
	}
	public void setObjective(Objective objective) {
		this.objective = objective;
	}
	public KeyResultType getKeyResultType() {return type; }
	public void setKeyResultType(KeyResultType type) {
		this.type = type;
	}
	public Team getTeam() {return team;	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Product getProduct() {return product; }
  	public void setProduct(Product product) {
		this.product = product;
	}
	public Metric getMetric() {return metric;	}
	public void setMetric(Metric metric) {this.metric = metric;	}


}
