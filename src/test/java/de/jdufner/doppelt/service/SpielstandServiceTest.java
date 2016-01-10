package de.jdufner.doppelt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.jdufner.doppelt.domain.Element;
import de.jdufner.doppelt.domain.Karte;
import de.jdufner.doppelt.domain.Spielstand;
import de.jdufner.doppelt.repository.SpielstandRepository;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class SpielstandServiceTest {

  @InjectMocks
  private SpielstandService spielstandService;

  @Mock
  private SpielstandRepository spielstandRepository;

  @Test
  public void testCheckElement() throws IOException {
    // arrange
    doReturn(new SpielstandBuilder().addKarteMitElementen(1, 2).addKarteMitElementen(1, 3).build()).when(spielstandRepository)
        .findByEigentuemer(anyString());

    // act
    Spielstand spielstandNeu = spielstandService.checkElement("jdufner", new Element(1));

    // assert
    assertThat(spielstandNeu).isNotNull();
    assertThat(spielstandNeu.getGesuchteKarteIndex()).isEqualTo(1);
    assertThat(spielstandNeu.getPrivateKarteIndex()).isEqualTo(2);
  }

  public static class SpielstandBuilder {

    private int gesuchteKarteIndex = 1;
    private int privateKarteIndex = 1;
    private List<Karte> karten = new ArrayList<Karte>();

    public SpielstandBuilder setGesuchteKarteIndex(final int gesuchteKarteIndex) {
      this.gesuchteKarteIndex = gesuchteKarteIndex;
      return this;
    }

    public SpielstandBuilder setPrivateKarteIndex(final int privateKarteIndex) {
      this.privateKarteIndex = privateKarteIndex;
      return this;
    }

    public SpielstandBuilder addKarteMitElementen(final Integer... elementeAsInt) {
      if (elementeAsInt != null) {
        karten.add(new Karte(Element.buildList(elementeAsInt)));
      }
      return this;
    }

    public Spielstand build() {
      Spielstand spielstand = new Spielstand();
      spielstand.setGesuchteKarteIndex(gesuchteKarteIndex);
      spielstand.setPrivateKarteIndex(privateKarteIndex);
      spielstand.setKartenvorrat(karten);
      return spielstand;
    }

  }

}
