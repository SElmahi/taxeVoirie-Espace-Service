package com.taxvoirie.espaceservice.DTO;

import com.taxvoirie.espaceservice.enums.Etat;
import com.taxvoirie.espaceservice.enums.TypeEspace;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EspaceRequest {

  @NotNull(message = "La longueur est obligatoire")
  @Positive(message = "La longueur doit être positive")
  private Float longueur;

  @NotNull(message = "La largeur est obligatoire")
  @Positive(message = "La largeur doit être positive")
  private Float largeur;

  @NotNull(message = "Le type d'espace est obligatoire")
  private TypeEspace type;

  @NotNull(message = "Le prix est obligatoire")
  @PositiveOrZero(message = "Le prix doit être >= 0")
  private Float prixParM2;

  @NotNull(message = "La position X est obligatoire")
  private Float positionX;

  @NotNull(message = "La position Y est obligatoire")
  private Float positionY;

  @NotBlank(message = "La localisation est obligatoire")
  private String localisation;
  private Etat etat;
}

