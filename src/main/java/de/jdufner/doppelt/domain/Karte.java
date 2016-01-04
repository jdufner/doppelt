package de.jdufner.doppelt.domain;

import java.util.ArrayList;
import java.util.List;

import de.jdufner.doppelt.utils.NumberUtil;

public class Karte {

  private List<Integer> elemente;

  public Karte(final List<Integer> elemente) {
    this.elemente = elemente;
  }

  public void mischeElemente() {
    List<Integer> gemischteElemente = new ArrayList<Integer>();
    int size = elemente.size();
    for (int i = 0; i < size; i++) {
      gemischteElemente.add(elemente.remove(NumberUtil.liefereZufallszahl(0, elemente.size())));
    }
    elemente = gemischteElemente;
  }

  public List<Integer> getElemente() {
    return elemente;
  }

  @Override
  public String toString() {
    return "Karte [elemente=" + elemente + "]";
  }

}
