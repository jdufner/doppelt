package de.jdufner.doppelt.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@Entity
@Table(name = "element")
public class Element {

  static final String SEPARATOR = "|";
  private static final String REGEXP = "\\" + SEPARATOR;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "elmt_id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "elmt_wert", unique = false, nullable = false)
  private Integer wert;

  public Element() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Element(final Integer wert) {
    this.wert = wert;
  }

  public Integer getWert() {
    return wert;
  }

  public void setWert(final Integer wert) {
    this.wert = wert;
  }

  public static List<Element> buildList(final Integer... integers) {
    if (integers == null) {
      return Collections.<Element> emptyList();
    }
    List<Element> elements = new ArrayList<Element>();
    for (int i = 0; i < integers.length; i++) {
      elements.add(new Element(integers[i]));
    }
    return elements;
  }

  public static List<Element> buildList(final String elementsAsString) {
    if (elementsAsString == null || elementsAsString.isEmpty()) {
      return Collections.<Element> emptyList();
    }
    List<Element> elements = new ArrayList<Element>();
    String[] elementsAsArray = elementsAsString.split(REGEXP);
    for (int i = 0; i < elementsAsArray.length; i++) {
      elements.add(new Element(Integer.parseInt(elementsAsArray[i])));
    }
    return elements;
  }

  public static String buildString(final List<Element> elements) {
    if (elements == null) {
      return "";
    }
    String elementsAsString = "";
    for (Element element : elements) {
      if (!elementsAsString.isEmpty()) {
        elementsAsString += SEPARATOR;
      }
      elementsAsString += element.getWert();
    }
    return elementsAsString;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((wert == null) ? 0 : wert.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Element other = (Element) obj;
    if (wert == null) {
      if (other.wert != null) {
        return false;
      }
    } else if (!wert.equals(other.wert)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Element [wert=" + wert + "]";
  }

}
