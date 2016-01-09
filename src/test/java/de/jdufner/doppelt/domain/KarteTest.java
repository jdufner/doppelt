package de.jdufner.doppelt.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class KarteTest {

  @Test
  public void whenMischeElementeExpectReihenfolgeDerElementeGeaendert() {
    // arrange
    Karte karte = new Karte(Element.buildList(1, 2, 3, 4, 5, 6, 7, 8));

    // act
    karte.mischeElemente();

    // assert
    assertThat(karte.getElemente()).contains(new Element(1), new Element(2), new Element(3), new Element(4), new Element(5),
        new Element(6), new Element(7), new Element(8));
  }

}
