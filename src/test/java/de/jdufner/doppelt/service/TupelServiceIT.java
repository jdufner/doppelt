package de.jdufner.doppelt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jdufner.doppelt.Application;
import de.jdufner.doppelt.domain.Karte;
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
  }

  @Test
  public void whenMischeTupelExpectTupel() throws IOException {
    // arrange
    Tupel tupel = tupelService.getTupel();

    // act
    List<Karte> karten = tupelService.erstelleUndMischeSpielekarten(tupel);

    // assert
    assertThat(karten).isNotNull().isNotEmpty();
  }

}
