package de.jdufner.doppelt.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jdufner.doppelt.domain.Element;
import de.jdufner.doppelt.domain.Karte;
import de.jdufner.doppelt.domain.Spielstand;
import de.jdufner.doppelt.domain.Tupel.Groesse;
import de.jdufner.doppelt.repository.SpielstandRepository;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@Service
public class SpielstandService {

  @Autowired
  private SpielstandRepository spielstandRepository;

  @Autowired
  private TupelService tupelService;

  public Spielstand initializeSpielstand() throws IOException {
    List<Karte> karten = tupelService.loadSpielkarten(Groesse.EIGHT);
    Spielstand spielstand = new Spielstand();
    spielstand.setKartenvorrat(karten);
    //    spielstand.setAktuelleStiche(new ArrayList<Stich>());
    //    spielstand.getAktuelleStiche().add(new Stich());
    return spielstand;
  }

  @Transactional
  public Spielstand saveSpielstand(final Spielstand spielstand) {
    Spielstand savedSpielstand = spielstandRepository.save(spielstand);
    return savedSpielstand;
  }

  @Transactional
  public Spielstand checkElement(final String eigentuemer, final Element element) {
    Spielstand spielstand = spielstandRepository.findByEigentuemer(eigentuemer);
    if (spielstand.getGemeinsamesElementVonGesuchterUndPrivaterKarte().equals(element)) {
      spielstand.setGesuchteKarteIndex(spielstand.getPrivateKarteIndex());
      spielstand.setPrivateKarteIndex(spielstand.getPrivateKarteIndex().intValue() + 1);
    }
    return spielstand;
  }

  public Spielstand loadSpielstand(final String eigentuemer) {
    Spielstand savedSpielstand = spielstandRepository.findByEigentuemer(eigentuemer);
    return savedSpielstand;
  }

}
