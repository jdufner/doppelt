package de.jdufner.doppelt.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
  @Column(name = "spst_eigentuemer", unique = true, nullable = false)
  private String eigentuemer;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "kartenvorrat", //
  joinColumns = { @JoinColumn(name = "kavo_spst_id", referencedColumnName = "spst_id") }, //
  inverseJoinColumns = { @JoinColumn(name = "kavo_kart_id", referencedColumnName = "kart_id") })
  @OrderColumn(name = "kavo_order")
  private List<Karte> kartenvorrat;
  @Column(name = "spst_gesuchte_karte", unique = true, nullable = true)
  private Integer gesuchteKarteIndex = 0;
  @Column(name = "spst_private_karte", unique = true, nullable = true)
  private Integer privateKarteIndex = 1;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "stch_spst_id")
  private List<Stich> aktuelleStiche;

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getEigentuemer() {
    return eigentuemer;
  }

  public void setEigentuemer(final String eigentuemer) {
    this.eigentuemer = eigentuemer;
  }

  public List<Karte> getKartenvorrat() {
    return kartenvorrat;
  }

  public void setKartenvorrat(final List<Karte> kartenvorrat) {
    this.kartenvorrat = kartenvorrat;
  }

  public Integer getGesuchteKarteIndex() {
    return gesuchteKarteIndex;
  }

  public void setGesuchteKarteIndex(final Integer gesuchteKarteIndex) {
    this.gesuchteKarteIndex = gesuchteKarteIndex;
  }

  public Integer getPrivateKarteIndex() {
    return privateKarteIndex;
  }

  public void setPrivateKarteIndex(final Integer privateKarteIndex) {
    this.privateKarteIndex = privateKarteIndex;
  }

  public List<Stich> getAktuelleStiche() {
    return aktuelleStiche;
  }

  public void setAktuelleStiche(final List<Stich> aktuelleStiche) {
    this.aktuelleStiche = aktuelleStiche;
  }

  public Element getGemeinsamesElementVonGesuchterUndPrivaterKarte() {
    for (Element element : kartenvorrat.get(gesuchteKarteIndex).getElemente()) {
      if (kartenvorrat.get(privateKarteIndex).getElemente().contains(element)) {
        return element;
      }
    }
    return null;
  }

}
