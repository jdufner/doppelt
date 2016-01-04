package de.jdufner.doppelt.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

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
    Karte karte = new Karte(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));

    // act
    karte.mischeElemente();

    // assert
    assertThat(karte.getElemente()).contains(1, 2, 3, 4, 5, 6, 7, 8);
  }

}
