package pl.someday.rest_api_fishing_log.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "fishes")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fishes_names_id")
    private FishName fishName;

    @Column(precision = 4, scale = 1)
    private BigDecimal length;

    @Column(precision = 4, scale = 1)
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private FishingSession fishingSession;
}
