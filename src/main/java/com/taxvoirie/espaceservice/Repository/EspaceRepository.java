package com.taxvoirie.espaceservice.Repository;

import com.taxvoirie.espaceservice.Entity.Espace;
import com.taxvoirie.espaceservice.enums.Etat;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EspaceRepository extends R2dbcRepository<Espace, Long> {
  Flux<Espace> findByLocalisationContainingIgnoreCase(String localisation);
  Flux<Espace> findByEtat(Etat etat);

}
