package de.jdufner.doppelt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jdufner.doppelt.Application;
import de.jdufner.doppelt.domain.Tupel;
import de.jdufner.doppelt.domain.Tupel.Groesse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class TupelServiceIT {

  @Autowired
  private TupelService tupelService;

  @Test
  public void whenGetTupelAsStringExpectDatenGeladen() throws IOException {
    // arrange

    // act
    String tupelAsString = tupelService.getTupelAsString();

    // assert
    assertThat(tupelAsString).contains("\"daten\"").contains("[1,2,3,4,5,6,7,8]").contains("[8,15,21,27,33,39,45,51]");
  }

  @Test
  public void whenGetTupelExpectTupelGeladen() throws IOException {
    // arrange

    // act
    Tupel tupel = tupelService.getTupel();

    // assert
    assertThat(tupel).isNotNull();
    assertThat(tupel.getDaten()).isNotNull().hasSize(57).contains(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 })
        .contains(new int[] { 8, 15, 21, 27, 33, 39, 45, 51 });
    assertThat(tupel.getDaten()).isNotNull().hasSize(Groesse.getByAnzahlElemente(tupel.getDaten()[0].length).getAnzahlKarten());
    //    System.out.println(new ObjectMapper().writeValueAsString(tupel));
  }

  @Test
  public void whenGetTupelBySizeExpectTupelGeladen() throws IOException {
    // arrange

    // act
    Tupel tupel = tupelService.getTupel(Groesse.EIGHT);

    // assert
    assertThat(tupel).isNotNull();
    assertThat(tupel.getDaten()).isNotNull().hasSize(57).contains(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 })
        .contains(new int[] { 8, 15, 21, 27, 33, 39, 45, 51 });
    assertThat(tupel.getDaten()).isNotNull().hasSize(Groesse.getByAnzahlElemente(tupel.getDaten()[0].length).getAnzahlKarten());
    //    System.out.println(new ObjectMapper().writeValueAsString(tupel));
  }

  @Test
  public void whenLiefereZufallszahlExpectGleichverteilung() {
    // arrange
    final int ITERATIONEN = 10000;
    final int LAENGE = 10;
    int[] array = new int[LAENGE];

    // act
    for (int i = 1; i < ITERATIONEN; i++) {
      array[tupelService.liefereZufallszahl(0, LAENGE)]++;
    }

    // assert
    for (int i = 0; i < array.length; i++) {
      double d = (double) array[i] / ITERATIONEN * LAENGE;
      assertThat(d).isCloseTo(1, offset(0.1));
    }

  }
}
