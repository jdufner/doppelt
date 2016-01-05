package de.jdufner.doppelt.domain;

import java.util.List;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class Spielstand {

  private List<Karte> kartenvorrat;
  private int gesuchteKarte = 0;
  private int privateKarte = 1;
  private List<Stich> aktuelleStiche;

  public List<Karte> getKartenvorrat() {
    return kartenvorrat;
  }

  public void setKartenvorrat(final List<Karte> kartenvorrat) {
    this.kartenvorrat = kartenvorrat;
  }

  public int getGesuchteKarte() {
    return gesuchteKarte;
  }

  public void setGesuchteKarte(final int gesuchteKarte) {
    this.gesuchteKarte = gesuchteKarte;
  }

  public int getPrivateKarte() {
    return privateKarte;
  }

  public void setPrivateKarte(final int privateKarte) {
    this.privateKarte = privateKarte;
  }

  public List<Stich> getAktuelleStiche() {
    return aktuelleStiche;
  }

  public void setAktuelleStiche(final List<Stich> aktuelleStiche) {
    this.aktuelleStiche = aktuelleStiche;
  }

}
