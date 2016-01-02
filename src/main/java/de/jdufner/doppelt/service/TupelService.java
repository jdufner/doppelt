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
    Tupel tupel = new ObjectMapper().readValue(jsonFile.getInputStream(), Tupel.class);
    return tupel;
  }

  public String tupelDatenKurz() throws IOException {
    Resource file = jsonFile;
    return new String(Files.readAllBytes(file.getFile().toPath()));
  }

  public String tupelDatenLang() throws IOException {
    Resource file = resourceLoader.getResource("classpath:8.json");
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

}
