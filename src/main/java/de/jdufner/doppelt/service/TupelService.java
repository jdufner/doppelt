package de.jdufner.doppelt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.jdufner.doppelt.domain.Element;
import de.jdufner.doppelt.domain.Karte;
import de.jdufner.doppelt.domain.Tupel;
import de.jdufner.doppelt.domain.Tupel.Groesse;
import de.jdufner.doppelt.utils.NumberUtil;

@Service
public class TupelService {

  @Autowired
  private ResourceLoader resourceLoader;

  public Tupel getTupel(final Groesse groesse) throws IOException {
    Resource file = resourceLoader.getResource(String.format("classpath:%d.json", groesse.getAnzahlElemente()));
    Tupel tupel = new ObjectMapper().readValue(file.getInputStream(), Tupel.class);
    return tupel;
  }

  private List<Karte> erstelleSpielkarten(final Tupel tupel) {
    List<Karte> karten = new ArrayList<Karte>();
    int[][] daten = tupel.getDaten();
    for (int i = 0; i < daten.length; i++) {
      List<Element> elemente = new ArrayList<Element>();
      for (int j = 0; j < daten[i].length; j++) {
        elemente.add(new Element(daten[i][j]));
      }
      Karte karte = new Karte(elemente);
      karten.add(karte);
    }
    return karten;
  }

  private Karte getZufaelligeKarte(final List<Karte> karten) {
    return karten.remove(NumberUtil.liefereZufallszahl(0, karten.size()));
  }

  private List<Karte> mischeSpielkarten(final List<Karte> karten) {
    List<Karte> gemischteKarten = new ArrayList<Karte>();
    int size = karten.size();
    for (int i = 0; i < size; i++) {
      Karte karte = getZufaelligeKarte(karten);
      karte.mischeElemente();
      gemischteKarten.add(karte);
    }
    return gemischteKarten;
  }

  public List<Karte> loadSpielkarten(final Groesse groesse) throws IOException {
    return mischeSpielkarten(erstelleSpielkarten(getTupel(groesse)));
  }

}
