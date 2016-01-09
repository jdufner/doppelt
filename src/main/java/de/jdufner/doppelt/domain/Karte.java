package de.jdufner.doppelt.domain;

import java.util.ArrayList;
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
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import de.jdufner.doppelt.utils.NumberUtil;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@Entity
@Table(name = "karte")
public class Karte {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "kart_id", unique = true, nullable = false)
  private Long id;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "kartenelemente", //
  joinColumns = { @JoinColumn(name = "kael_kart_id", referencedColumnName = "kart_id") }, //
  inverseJoinColumns = { @JoinColumn(name = "kael_elmt_id", referencedColumnName = "elmt_id") })
  @OrderColumn(name = "kael_order")
  private List<Element> elemente;

  public Karte(final List<Element> elemente) {
    this.elemente = elemente;
  }

  public void mischeElemente() {
    List<Element> gemischteElemente = new ArrayList<Element>();
    int size = elemente.size();
    for (int i = 0; i < size; i++) {
      gemischteElemente.add(elemente.remove(NumberUtil.liefereZufallszahl(0, elemente.size())));
    }
    elemente = gemischteElemente;
  }

  public List<Element> getElemente() {
    return elemente;
  }

  public void setElemente(final List<Element> elemente) {
    this.elemente = elemente;
  }

  @Override
  public String toString() {
    return "Karte [elemente=" + elemente + "]";
  }

}
