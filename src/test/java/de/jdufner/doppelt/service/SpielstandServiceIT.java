package de.jdufner.doppelt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
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
public class SpielstandServiceIT {

  @Autowired
  private SpielstandService spielstandService;

  @Test
  public void test() throws IOException {
    // arrange

    // act
    Spielstand spielstand = spielstandService.loadSpielstand();

    // assert
    assertThat(spielstand).isNotNull();
  }

}
