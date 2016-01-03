package de.jdufner.doppelt.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import de.jdufner.doppelt.domain.Tupel.Groesse;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class TupelTest {

  @Test
  public void whenGetAnzahlElementeExpectRichtigeAnzahlElemente() {
    // arrange
    Groesse g = Groesse.EIGHT;

    // act
    int anzahlElemente = g.getAnzahlElemente();

    // assert
    assertThat(anzahlElemente).isEqualTo(8);
  }

  @Test
  public void whenGetAnzahlKartenExpectRichtigeAnzahlKarten() {
    // arrange
    Groesse g = Groesse.EIGHT;

    // act
    int anzahlKarten = g.getAnzahlKarten();

    // assert
    assertThat(anzahlKarten).isEqualTo(57);
  }

  @Test
  public void whenGetGroesseExceptGroesseByAnzahl() {
    // arrange
    Tupel tupel = new Tupel();
    tupel.setDaten(new int[][] { new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 3 } });

    // act
    Groesse groesse = tupel.getGroesse();

    // assert
    assertThat(groesse).isNotNull().isEqualTo(Groesse.TWO);
  }

  @Test
  public void whenGetGroesseByAnzahlExceptGroesse() {
    // arrange

    // act
    Groesse groesse = Groesse.getByAnzahlElemente(8);

    // assert
    assertThat(groesse).isNotNull().isEqualTo(Groesse.EIGHT);
  }

  @Test
  public void whenGetGroesseByAnzahlExceptNull() {
    // arrange

    // act
    Groesse groesse = Groesse.getByAnzahlElemente(7);

    // assert
    assertThat(groesse).isNull();
  }

}
