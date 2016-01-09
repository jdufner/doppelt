package de.jdufner.doppelt.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jdufner.doppelt.Application;
import de.jdufner.doppelt.domain.Element;
import de.jdufner.doppelt.domain.Karte;
import de.jdufner.doppelt.domain.Stich;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class KarteServiceIT {

  @Autowired
  private KarteService karteService;

  @Test
  public void testSaveKarte() {
    // arrange
    Karte karteIn = new Karte(Element.buildList(1, 2, 3, 4));

    // act
    Karte karteOut = karteService.save(karteIn);

    // assert
    assertThat(karteOut).isNotNull().isEqualTo(karteIn);
  }

  @Test
  public void testSaveElement() {
    // arrange
    Element elementIn = new Element(1);

    // act
    Element elementOut = karteService.save(elementIn);

    // assert
    assertThat(elementOut).isNotNull().isEqualTo(elementIn).isSameAs(elementIn);
  }

  @Test
  public void testSaveStich() {
    // arrange
    Stich stich = new Stich();

    // act
    Stich stichOut = karteService.save(stich);

    // assrt
    assertThat(stichOut).isNotNull().isEqualTo(stich);
  }

}
