package com.taxvoirie.espaceservice.DTO;
import com.taxvoirie.espaceservice.enums.Etat;
import com.taxvoirie.espaceservice.enums.TypeEspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EspaceDto {
  private Long id;
  private Float longueur;
  private Float largeur;
  private Float surface;
  private TypeEspace type;
  private Float prixParM2;
  private Float positionX;
  private Float positionY;
  private String localisation;
  private Etat etat;

}
