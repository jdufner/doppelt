package de.jdufner.doppelt.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@Entity
@Table(name = "stich")
public class Stich {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "stch_id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "stch_start", nullable = false)
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime start = LocalDateTime.now();

  @Column(name = "stch_stop", nullable = true)
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime stop;

  @Column(name = "stch_nr", nullable = true)
  private int nr;

  @Column(name = "stch_dauer", nullable = true)
  private Long dauer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "stch_elmt_id", nullable = true)
  private Element sharedElement;

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(final LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getStop() {
    return stop;
  }

  public void setStop(final LocalDateTime stop) {
    this.stop = stop;
  }

  public int getNr() {
    return nr;
  }

  public void setNr(final int nr) {
    this.nr = nr;
  }

  public Long getDauer() {
    return dauer;
  }

  public void setDauer(final Long zeit) {
    this.dauer = zeit;
  }

  public Element getSharedElement() {
    return sharedElement;
  }

  public void setSharedElement(final Element sharedElement) {
    this.sharedElement = sharedElement;
  }

}
