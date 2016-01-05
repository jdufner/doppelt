package de.jdufner.doppelt.domain;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class Stich {

  private LocalDateTime start = LocalDateTime.now();
  private LocalDateTime stop;
  private int nr;
  private Duration zeit;
  private Karte sharedElement;

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

  public Duration getZeit() {
    return zeit;
  }

  public void setZeit(final Duration zeit) {
    this.zeit = zeit;
  }

  public Karte getSharedElement() {
    return sharedElement;
  }

  public void setSharedElement(final Karte sharedElement) {
    this.sharedElement = sharedElement;
  }

}
