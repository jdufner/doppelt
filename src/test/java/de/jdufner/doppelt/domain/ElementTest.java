package de.jdufner.doppelt.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
public class ElementTest {

  @Test
  public void test1() {
    // arrange

    // act
    List<Element> elemente = Element.buildList(1, 2, 3);

    // assert
    assertThat(elemente).isNotNull().containsExactly(new Element(1), new Element(2), new Element(3));
  }

  @Test
  public void test2() {
    // arrange

    // act
    List<Element> elemente = Element.buildList("1" + Element.SEPARATOR + "2" + Element.SEPARATOR + "3");

    // assert
    assertThat(elemente).isNotNull().containsExactly(new Element(1), new Element(2), new Element(3));
  }

  @Test
  public void test3() {
    // arrange

    // act
    String elementsAsString = Element.buildString(Element.buildList(1, 2, 3));

    // assert
    assertThat(elementsAsString).isNotNull().isEqualTo("1" + Element.SEPARATOR + "2" + Element.SEPARATOR + "3");
  }

}
