package de.jdufner.doppelt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jdufner.doppelt.domain.Karte;

public interface KarteRepository extends JpaRepository<Karte, Integer> {

}
