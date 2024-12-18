package com.basedatos.controller;

import com.basedatos.entity.KendrickSinglesFeature;
import com.basedatos.repository.KendrickSinglesrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class KendrickSinglescontroller {
    @Autowired
    private KendrickSinglesrepository kendrickSinglesrepository;

    @GetMapping("/allsingles")
    ResponseEntity<List<KendrickSinglesFeature>> all() {
        return ResponseEntity.ok(kendrickSinglesrepository.findAll());
    }

    @GetMapping("/allsingles/{id}")
    ResponseEntity<Map<String, Object>> one(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        return kendrickSinglesrepository.findById(id)
                .map(usuario -> {
                    response.put("singles", usuario);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.put("error", "single not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    @PostMapping("/single/save")
    ResponseEntity<Map<String, Object>> newSingle(@RequestBody KendrickSinglesFeature newSingle) {
        Map<String, Object> response = new HashMap<>();
        try {
            KendrickSinglesFeature savedSingle = kendrickSinglesrepository.save(newSingle);
            response.put("single", savedSingle);
            response.put("mensaje", "Single registrado exitosamente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al registrar el track");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }

    @PutMapping("/singles/{id}")
    ResponseEntity<Map<String, Object>> actualizarSingle(@RequestBody KendrickSinglesFeature newSingle, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            return kendrickSinglesrepository.findById(id)
                    .map(usuario -> {
                        if (newSingle.getTrackId() != null) {
                            usuario.setTrackId(newSingle.getTrackId());
                        }
                        if (newSingle.getArtistName() != null) {
                            usuario.setArtistName(newSingle.getArtistName());
                        }
                        if (newSingle.getTrackName() != null) {
                            usuario.setTrackName(newSingle.getTrackName());
                        }
                        if (newSingle.getAlbumName() != null) {
                            usuario.setAlbumName(newSingle.getAlbumName());
                        }
                        if (newSingle.getReleaseDate() != null) {
                            usuario.setReleaseDate(newSingle.getReleaseDate());
                        }
                        if (newSingle.getExplicit() != null) {
                            usuario.setExplicit(newSingle.getExplicit());
                        }
                        if (newSingle.getPopularity() != null) {
                            usuario.setPopularity(newSingle.getPopularity());
                        }
                        if (newSingle.getDurationMs() != null) {
                            usuario.setDurationMs(newSingle.getDurationMs());
                        }
                        if (newSingle.getAlbumArtworkUrl() != null) {
                            usuario.setAlbumArtworkUrl(newSingle.getAlbumArtworkUrl());
                        }
                        if (newSingle.getDanceability() != null) {
                            usuario.setDanceability(newSingle.getDanceability());
                        }
                        if (newSingle.getEnergy() != null) {
                            usuario.setEnergy(newSingle.getEnergy());
                        }
                        if (newSingle.getValence() != null) {
                            usuario.setValence(newSingle.getValence());
                        }
                        if (newSingle.getTempo() != null) {
                            usuario.setTempo(newSingle.getTempo());
                        }
                        if (newSingle.getLoudness() != null) {
                            usuario.setLoudness(newSingle.getLoudness());
                        }
                        if (newSingle.getSpeechiness() != null) {
                            usuario.setSpeechiness(newSingle.getSpeechiness());
                        }
                        if (newSingle.getAcousticness() != null) {
                            usuario.setAcousticness(newSingle.getAcousticness());
                        }
                        if (newSingle.getInstrumentalness() != null) {
                            usuario.setInstrumentalness(newSingle.getInstrumentalness());
                        }
                        if (newSingle.getLiveness() != null) {
                            usuario.setLiveness(newSingle.getLiveness());
                        }
                        if (newSingle.getIsAlbumTrack() != null) {
                            usuario.setIsAlbumTrack(newSingle.getIsAlbumTrack());
                        }
                        if (newSingle.getIsFeature() != null) {
                            usuario.setIsFeature(newSingle.getIsFeature());
                        }

                        KendrickSinglesFeature updatedUsuario = kendrickSinglesrepository.save(usuario);
                        response.put("track", updatedUsuario);
                        response.put("mensaje", "Track actualizado exitosamente");
                        return ResponseEntity.ok(response);
                    })
                    .orElseGet(() -> {
                        response.put("error", "Track no encontrado");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                    });
        } catch (Exception e) {
            response.put("error", "Error al actualizar usuario");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/single/{id}")
    ResponseEntity<Map<String, Object>> borrarsingle(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (kendrickSinglesrepository.existsById(id)) {
                kendrickSinglesrepository.deleteById(id);
                response.put("mensaje", "albuns eliminado exitosamente");
                return ResponseEntity.ok(response);
            }
            response.put("error", "albuns no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("error", "albuns al eliminar usuario");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
