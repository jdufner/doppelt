package de.jdufner.doppelt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jdufner.doppelt.domain.Element;
import de.jdufner.doppelt.domain.Karte;
import de.jdufner.doppelt.domain.Stich;
import de.jdufner.doppelt.repository.ElementRepository;
import de.jdufner.doppelt.repository.KarteRepository;
import de.jdufner.doppelt.repository.StichRepository;

/**
 *
 * @author Jürgen
 * @since 1.0
 */
@Service
public class KarteService {

  @Autowired
  private KarteRepository karteRepository;

  @Autowired
  private ElementRepository elementRepository;

  @Autowired
  private StichRepository stichRepository;

  public Karte save(final Karte karte) {
    Karte savedKarte = karteRepository.save(karte);
    return savedKarte;
  }

  public Element save(final Element element) {
    Element savedElement = elementRepository.save(element);
    return savedElement;
  }

  public Stich save(final Stich stich) {
    Stich savedStich = stichRepository.save(stich);
    return savedStich;
  }

}
