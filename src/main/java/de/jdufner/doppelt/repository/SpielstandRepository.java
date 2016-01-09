package de.jdufner.doppelt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jdufner.doppelt.domain.Spielstand;

public interface SpielstandRepository extends JpaRepository<Spielstand, Integer> {

}
