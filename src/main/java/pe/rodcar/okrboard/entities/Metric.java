package pe.rodcar.okrboard.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.MapsId;

@Entity
@Table(name="metrics")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    //@Column(name="metric", nullable=true, length=500)
    private String metricName;
    private Double startValue;
    private Double targetValue;
    private Double currentValue;


  //  private Long krId;


    @OneToOne(mappedBy = "metric")
   // @JoinColumn(name = "krId", nullable=false)
    private KeyResult keyresult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetricName() {
        return metricName;
    }
    public Double getStartValue() {
        return startValue;
    }
    public Double getTargetValue() {
        return targetValue;
    }
    public Double getSCurrentValue() {
        return currentValue;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }
    public void setStartVaLue(Double startValue) {
        this.startValue = startValue;
    }
    public void setTargetVaLue(Double targetValue) {
        this.targetValue = targetValue;
    }
    public void setCurrentVaLue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Metric() {

        this.startValue = 0.0;
        this.targetValue = 100.0;
        this.currentValue= 0.0;

    }

}