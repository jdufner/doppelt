package de.jdufner.doppelt.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.jdufner.doppelt.Application;
import de.jdufner.doppelt.domain.Tupel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class TupelServiceIT {

  @Autowired
  private TupelService tupelService;

  @Test
  public void test1() throws IOException {
    String tupelAsString = tupelService.getTupelAsString();
    System.out.println(tupelAsString);
  }

  @Test
  public void test2() throws IOException {
    Tupel tupel = tupelService.getTupel();
    System.out.println(new ObjectMapper().writeValueAsString(tupel));
  }

}
