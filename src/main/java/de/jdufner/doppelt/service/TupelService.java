package de.jdufner.doppelt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.jdufner.doppelt.domain.Karte;
import de.jdufner.doppelt.domain.Tupel;
import de.jdufner.doppelt.domain.Tupel.Groesse;
import de.jdufner.doppelt.utils.NumberUtil;

@Service
public class TupelService {

  @Value("classpath:8.json")
  private Resource jsonFile;

  @Autowired
  private ResourceLoader resourceLoader;

  public String getTupelAsString() throws IOException {
    String tupelDatenAsString = tupelDatenKurz();
    return tupelDatenAsString;
  }

  public Tupel getTupel() throws IOException {
    Resource file = jsonFile;
    Tupel tupel = new ObjectMapper().readValue(file.getInputStream(), Tupel.class);
    return tupel;
  }

  public Tupel getTupel(final Groesse groesse) throws IOException {
    Resource file = resourceLoader.getResource(String.format("classpath:%d.json", groesse.getAnzahlElemente()));
    Tupel tupel = new ObjectMapper().readValue(file.getInputStream(), Tupel.class);
    return tupel;
  }

  private String tupelDatenKurz() throws IOException {
    Resource file = jsonFile;
    return new String(Files.readAllBytes(file.getFile().toPath()));
  }

  private String tupelDatenLang() throws IOException {
    Resource file = jsonFile;
    InputStreamReader isr = new InputStreamReader(file.getInputStream());
    BufferedReader br = new BufferedReader(isr);
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line);
      line = br.readLine();
    }
    return sb.toString();
  }

  private List<Karte> erstelleSpielkarten(final Tupel tupel) {
    List<Karte> karten = new ArrayList<Karte>();
    int[][] daten = tupel.getDaten();
    for (int i = 0; i < daten.length; i++) {
      List<Integer> elemente = new ArrayList<Integer>();
      for (int j = 0; j < daten[i].length; j++) {
        elemente.add(daten[i][j]);
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

  public List<Karte> erstelleUndMischeSpielekarten(final Tupel tupel) {
    return mischeSpielkarten(erstelleSpielkarten(tupel));
  }

}
