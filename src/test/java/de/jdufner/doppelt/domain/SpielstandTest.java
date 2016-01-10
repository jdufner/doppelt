package de.jdufner.doppelt.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class SpielstandTest {

  @Test
  public void whenSpielstandGemeinsamesElementExistsExpectFirst() {
    // arrange
    Spielstand spielstand = new Spielstand();
    spielstand.setKartenvorrat(new ArrayList<>());
    spielstand.getKartenvorrat().add(new Karte(Element.buildList(1, 2)));
    spielstand.getKartenvorrat().add(new Karte(Element.buildList(1, 3)));

    // act
    Element gemeinsamesElement = spielstand.getGemeinsamesElementVonGesuchterUndPrivaterKarte();

    // assert
    assertThat(gemeinsamesElement).isNotNull().isEqualTo(new Element(1));
  }

  @Test
  public void whenSpielstandGemeinsamesElementExistsExpectNull() {
    // arrange
    Spielstand spielstand = new Spielstand();
    spielstand.setKartenvorrat(new ArrayList<>());
    spielstand.getKartenvorrat().add(new Karte(Element.buildList(1, 2)));
    spielstand.getKartenvorrat().add(new Karte(Element.buildList(3, 4)));

    // act
    Element gemeinsamesElement = spielstand.getGemeinsamesElementVonGesuchterUndPrivaterKarte();

    // assert
    assertThat(gemeinsamesElement).isNull();
  }

}
