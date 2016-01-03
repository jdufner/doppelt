package de.jdufner.doppelt.domain;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class Tupel {

  /**
   *
   * @author Jürgen Dufner
   * @since 1.0
   */
  public static enum Groesse {

    TWO(2), THREE(3), FOUR(4), SIX(6), EIGHT(8), TEN(10);

    private int anzahlElemente;

    private Groesse(final int anzahlElemente) {
      this.anzahlElemente = anzahlElemente;
    }

    public int getAnzahlElemente() {
      return anzahlElemente;
    }

    public int getAnzahlKarten() {
      return anzahlElemente + (anzahlElemente - 1) * (anzahlElemente - 1);
    }

    public static Groesse getByAnzahlElemente(final int gesuchteAnzahlElemente) {
      for (Groesse groesse : values()) {
        if (groesse.anzahlElemente == gesuchteAnzahlElemente) {
          return groesse;
        }
      }
      return null;
    }
  }

  private int[][] daten;

  public int[][] getDaten() {
    return daten;
  }

  public void setDaten(final int[][] daten) {
    this.daten = daten;
  }

  public Groesse getGroesse() {
    return Groesse.getByAnzahlElemente(daten[0].length);
  }

}
