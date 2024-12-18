package com.basedatos.controller;

import com.basedatos.entity.AllKendrickTrack;
import com.basedatos.repository.Tracksrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class AllKendrickTrackcontroller {

    @Autowired
    private Tracksrepository allkendricktrackrepository;

    @GetMapping("/alltrack")
    ResponseEntity<List<AllKendrickTrack>> all() {
        return ResponseEntity.ok(allkendricktrackrepository.findAll());
    }

    @GetMapping("/alltrack/{id}")
    ResponseEntity<Map<String, Object>> one(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        return allkendricktrackrepository.findById(id)
                .map(usuario -> {
                    response.put("track", usuario);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.put("error", "track not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    @PostMapping("/track/save")
    ResponseEntity<Map<String, Object>> newTrack(@RequestBody AllKendrickTrack newTrack) {
        Map<String, Object> response = new HashMap<>();
        try {
           AllKendrickTrack savedTrack = allkendricktrackrepository.save(newTrack);
            response.put("track", savedTrack);
            response.put("mensaje", "Track registrado exitosamente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al registrar el track");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/tracks/{id}")
    ResponseEntity<Map<String, Object>> actualizarTrack(@RequestBody AllKendrickTrack newTrack, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            return allkendricktrackrepository.findById(id)
                    .map(usuario -> {
                        if (newTrack.getTrackId() != null) {
                            usuario.setTrackId(newTrack.getTrackId());
                        }
                        if (newTrack.getArtistName() != null) {
                            usuario.setArtistName(newTrack.getArtistName());
                        }
                        if (newTrack.getTrackName() != null) {
                            usuario.setTrackName(newTrack.getTrackName());
                        }
                        if (newTrack.getAlbumName() != null) {
                            usuario.setAlbumName(newTrack.getAlbumName());
                        }
                        if (newTrack.getReleaseDate() != null) {
                            usuario.setReleaseDate(newTrack.getReleaseDate());
                        }
                        if (newTrack.getExplicit() != null) {
                            usuario.setExplicit(newTrack.getExplicit());
                        }
                        if (newTrack.getPopularity() != null) {
                            usuario.setPopularity(newTrack.getPopularity());
                        }
                        if (newTrack.getDurationMs() != null) {
                            usuario.setDurationMs(newTrack.getDurationMs());
                        }
                        if (newTrack.getAlbumArtworkUrl() != null) {
                            usuario.setAlbumArtworkUrl(newTrack.getAlbumArtworkUrl());
                        }
                        if (newTrack.getDanceability() != null) {
                            usuario.setDanceability(newTrack.getDanceability());
                        }
                        if (newTrack.getEnergy() != null) {
                            usuario.setEnergy(newTrack.getEnergy());
                        }
                        if (newTrack.getValence() != null) {
                            usuario.setValence(newTrack.getValence());
                        }
                        if (newTrack.getTempo() != null) {
                            usuario.setTempo(newTrack.getTempo());
                        }
                        if (newTrack.getLoudness() != null) {
                            usuario.setLoudness(newTrack.getLoudness());
                        }
                        if (newTrack.getSpeechiness() != null) {
                            usuario.setSpeechiness(newTrack.getSpeechiness());
                        }
                        if (newTrack.getAcousticness() != null) {
                            usuario.setAcousticness(newTrack.getAcousticness());
                        }
                        if (newTrack.getInstrumentalness() != null) {
                            usuario.setInstrumentalness(newTrack.getInstrumentalness());
                        }
                        if (newTrack.getLiveness() != null) {
                            usuario.setLiveness(newTrack.getLiveness());
                        }
                        if (newTrack.getIsAlbumTrack() != null) {
                            usuario.setIsAlbumTrack(newTrack.getIsAlbumTrack());
                        }
                        if (newTrack.getIsFeature() != null) {
                            usuario.setIsFeature(newTrack.getIsFeature());
                        }
                        if (newTrack.getReleaseYear() != null) {
                            usuario.setReleaseYear(newTrack.getReleaseYear());
                        }

                        if (newTrack.getDurationMin() != null) {
                            usuario.setDurationMin(newTrack.getDurationMin());
                        }
                        AllKendrickTrack updatedUsuario = allkendricktrackrepository.save(usuario);
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

    @DeleteMapping("/tracks/{id}")
    ResponseEntity<Map<String, Object>> borrartrack(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (allkendricktrackrepository.existsById(id)) {
                allkendricktrackrepository.deleteById(id);
                response.put("mensaje", "track eliminado exitosamente");
                return ResponseEntity.ok(response);
            }
            response.put("error", "Track no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("error", "Track al eliminar usuario");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }




}
