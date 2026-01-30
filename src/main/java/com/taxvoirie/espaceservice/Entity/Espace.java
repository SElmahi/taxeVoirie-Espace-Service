package com.taxvoirie.espaceservice.Entity;

import com.taxvoirie.espaceservice.enums.Etat;
import com.taxvoirie.espaceservice.enums.TypeEspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("espace")
public class Espace {

  @Id
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

  //  mise à jour auto la surface
  public void setLongueur(Float longueur) {
    this.longueur = longueur;
    updateSurface();
  }

  public void setLargeur(Float largeur) {
    this.largeur = largeur;
    updateSurface();
  }

  private void updateSurface() {
    if (this.longueur != null && this.largeur != null) {
      this.surface = this.longueur * this.largeur;
    }
  }
}