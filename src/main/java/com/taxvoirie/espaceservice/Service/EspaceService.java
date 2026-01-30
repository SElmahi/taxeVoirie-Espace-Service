package com.taxvoirie.espaceservice.Service;


import com.taxvoirie.espaceservice.DTO.EspaceDto;
import com.taxvoirie.espaceservice.DTO.EspaceRequest;
import com.taxvoirie.espaceservice.Entity.Espace;
import com.taxvoirie.espaceservice.Exception.EspaceNotFoundException;
import com.taxvoirie.espaceservice.Repository.EspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.taxvoirie.espaceservice.enums.Etat;

@Service
@RequiredArgsConstructor
public class EspaceService {

  private final EspaceRepository espaceRepository;
  // Tous les espaces
  public Flux<EspaceDto> getAllEspaces() {
    return espaceRepository.findAll()
       .map(this::mapToDto);
  }

  // Espace par ID
  public Mono<EspaceDto> getEspaceById(Long id) {
    return espaceRepository.findById(id)
       .switchIfEmpty(Mono.error(new EspaceNotFoundException("Espace non trouvé avec l'ID: " + id)))
       .map(this::mapToDto);
  }

  // Créer un espace
  public Mono<EspaceDto> createEspace(EspaceRequest espaceRequest) {
    Espace espace = mapToEntity(espaceRequest);
    return espaceRepository.save(espace).map(this::mapToDto);
  }

  // Mettre à jour un espace
  public Mono<EspaceDto> updateEspace(Long id, EspaceRequest espaceRequest) {
    return espaceRepository.findById(id)
       .switchIfEmpty(Mono.error(new EspaceNotFoundException("Espace non trouvé avec l'ID: " + id)))
       .flatMap(existing -> {
         Espace updated = mapToEntity(espaceRequest);
         updated.setId(existing.getId());
         return espaceRepository.save(updated);
       })
       .map(this::mapToDto);
  }

  // Supprimer un espace
  public Mono<Void> deleteEspace(Long id) {
    return espaceRepository.findById(id)
       .switchIfEmpty(Mono.error(new EspaceNotFoundException("Espace non trouvé avec l'ID: " + id)))
       .flatMap(espaceRepository::delete);
  }

  // Recherche par localisation ou état
  public Flux<EspaceDto> searchEspace(String keyword) {
    if (keyword == null || keyword.isEmpty()) {
      return getAllEspaces();
    }
    Flux<Espace> byLocalisation = espaceRepository.findByLocalisationContainingIgnoreCase(keyword);

    Flux<Espace> byEtat = Flux.empty();
    try {
      Etat etat = Etat.valueOf(keyword.toUpperCase());
      byEtat = espaceRepository.findByEtat(etat);
    } catch (IllegalArgumentException ignored) {}

    return Flux.concat(byLocalisation, byEtat)
       .distinct(Espace::getId)
       .map(this::mapToDto);
  }

  // Mapping entity -> DTO
  private EspaceDto mapToDto(Espace espace) {
    return EspaceDto.builder()
       .id(espace.getId())
       .longueur(espace.getLongueur())
       .largeur(espace.getLargeur())
       .surface(espace.getSurface())
       .localisation(espace.getLocalisation())
       .etat(espace.getEtat())
       .prixParM2(espace.getPrixParM2())
       .build();
  }

  // Mapping DTO -> entity
  private Espace mapToEntity(EspaceRequest espaceRequest) {
    return Espace.builder()
       .longueur(espaceRequest.getLongueur())
       .largeur(espaceRequest.getLargeur())
       .localisation(espaceRequest.getLocalisation())
       .etat(espaceRequest.getEtat())
       .prixParM2(espaceRequest.getPrixParM2())
       .build();
  }
}
