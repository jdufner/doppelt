package de.jdufner.doppelt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jdufner.doppelt.domain.Element;

public interface ElementRepository extends JpaRepository<Element, Integer> {

}
