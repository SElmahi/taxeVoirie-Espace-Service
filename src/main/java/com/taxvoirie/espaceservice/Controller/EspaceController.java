package com.taxvoirie.espaceservice.Controller;


import com.taxvoirie.espaceservice.DTO.EspaceDto;
import com.taxvoirie.espaceservice.DTO.EspaceRequest;
import com.taxvoirie.espaceservice.Service.EspaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/espaces")
@RequiredArgsConstructor
@Tag(name = "Espace API", description = "API pour la gestion des espaces")

public class EspaceController {
  private final EspaceService espaceService;

  @GetMapping
  @Operation(summary = "Récupérer tous les espaces")
  public Flux<EspaceDto> getAllEspaces(@RequestParam(required = false) String keyword) {
    if (keyword != null && !keyword.isEmpty()) {
      return espaceService.searchEspace(keyword);
    }
    return espaceService.getAllEspaces();
  }
  @GetMapping("/{id}")
  @Operation(summary = "Récupérer un espace par son ID")
  public Mono<EspaceDto> getEspaceById(@PathVariable Long id) {
    return espaceService.getEspaceById(id);
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Créer un nouveau espace")
  public Mono<EspaceDto> createEspace(@Valid @RequestBody EspaceRequest espaceRequest) {
    return espaceService.createEspace(espaceRequest);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Mettre à jour un espace existant")
  public Mono<EspaceDto> updateEspace(@PathVariable Long id, @Valid @RequestBody EspaceRequest espaceRequest) {
    return espaceService.updateEspace(id,espaceRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Supprimer un Espace")
  public Mono<Void> deleteEspace(@PathVariable Long id) {
    return espaceService.deleteEspace(id);
  }

}
