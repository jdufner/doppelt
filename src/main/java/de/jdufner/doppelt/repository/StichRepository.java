package de.jdufner.doppelt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jdufner.doppelt.domain.Stich;

public interface StichRepository extends JpaRepository<Stich, Integer> {

}
