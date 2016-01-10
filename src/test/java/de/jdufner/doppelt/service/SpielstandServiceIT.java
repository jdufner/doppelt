package de.jdufner.doppelt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jdufner.doppelt.Application;
import de.jdufner.doppelt.domain.Spielstand;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpielstandServiceIT {

  @Autowired
  private SpielstandService spielstandService;

  @Test
  public void whenLoadSpielstandExpectSpielstandGeladen() throws IOException {
    // arrange

    // act
    Spielstand spielstand = spielstandService.initializeSpielstand();

    // assert
    assertThat(spielstand).isNotNull();
  }

  @Test
  @Commit
  public void test1WhenSaveSpielstandExpectSaved() throws IOException {
    // arrange
    Spielstand spielstand = spielstandService.initializeSpielstand();
    spielstand.setEigentuemer("jdufner");

    // act
    Spielstand spielstandSaved = spielstandService.saveSpielstand(spielstand);

    // assert
    assertThat(spielstandSaved).isNotNull();
  }

  @Test
  public void test2WhenLoadSpielstandExpectLoaded() throws IOException {
    // arrange

    // act
    Spielstand spielstandSaved = spielstandService.loadSpielstand("jdufner");

    // assert
    assertThat(spielstandSaved).isNotNull();
    assertThat(spielstandSaved.getKartenvorrat()).isNotNull().hasSize(57);
  }

}
