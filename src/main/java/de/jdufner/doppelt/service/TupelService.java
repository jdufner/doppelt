package de.jdufner.doppelt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.jdufner.doppelt.domain.Tupel;
import de.jdufner.doppelt.domain.Tupel.Groesse;

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

  int liefereZufallszahl(final int vonInklusive, final int bisExklusive) {
    return vonInklusive + (int) (Math.random() * bisExklusive);
  }

  public Tupel mische(final Tupel tupel) {
    int[][] sorted = tupel.getDaten();
    int[][] shuffled = new int[sorted.length][sorted[0].length];
    for (int i = 0; i < sorted.length; i++) {
      //sorted[liefereZufallszahl(0, sorted.length)];
    }
    return null;
  }

}
