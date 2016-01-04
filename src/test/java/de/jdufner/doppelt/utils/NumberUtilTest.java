package de.jdufner.doppelt.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import org.junit.Test;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class NumberUtilTest {

  public void whenCallingLiefereZufallszahl1000TimesExpectAllNumberAreRight() {
    // arrange
    for (int i = 0; i < 1000; i++) {

      // act
      int j = NumberUtil.liefereZufallszahl(0, 10);

      // assert
      assertThat(j).isBetween(0, 9);
    }
  }

  @Test
  public void whenLiefereZufallszahlExpectGleichverteilung() {
    // arrange
    final int ITERATIONEN = 10000;
    final int LAENGE = 10;
    int[] array = new int[LAENGE];

    // act
    for (int i = 1; i < ITERATIONEN; i++) {
      array[NumberUtil.liefereZufallszahl(0, LAENGE)]++;
    }

    // assert
    for (int i = 0; i < array.length; i++) {
      double d = (double) array[i] / ITERATIONEN * LAENGE;
      assertThat(d).isCloseTo(1, offset(0.1));
    }
  }

}
