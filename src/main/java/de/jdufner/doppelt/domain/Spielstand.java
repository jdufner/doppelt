package de.jdufner.doppelt.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@Entity
@Table(name = "spielstand")
public class Spielstand {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "spst_id", unique = true, nullable = false)
  private Integer id;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "kartenvorrat", //
  joinColumns = { @JoinColumn(name = "kavo_spst_id", referencedColumnName = "spst_id") }, //
  inverseJoinColumns = { @JoinColumn(name = "kavo_kart_id", referencedColumnName = "kart_id") })
  @OrderColumn(name = "kavo_order")
  private List<Karte> kartenvorrat;
  @Column(name = "spst_gesuchte_karte", unique = true, nullable = true)
  private Integer gesuchteKarte = 0;
  @Column(name = "spst_private_karte", unique = true, nullable = true)
  private Integer privateKarte = 1;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "stch_spst_id")
  private List<Stich> aktuelleStiche;

  public List<Karte> getKartenvorrat() {
    return kartenvorrat;
  }

  public void setKartenvorrat(final List<Karte> kartenvorrat) {
    this.kartenvorrat = kartenvorrat;
  }

  public Integer getGesuchteKarte() {
    return gesuchteKarte;
  }

  public void setGesuchteKarte(final Integer gesuchteKarte) {
    this.gesuchteKarte = gesuchteKarte;
  }

  public Integer getPrivateKarte() {
    return privateKarte;
  }

  public void setPrivateKarte(final Integer privateKarte) {
    this.privateKarte = privateKarte;
  }

  //  public List<Stich> getAktuelleStiche() {
  //    return aktuelleStiche;
  //  }
  //
  //  public void setAktuelleStiche(final List<Stich> aktuelleStiche) {
  //    this.aktuelleStiche = aktuelleStiche;
  //  }

}