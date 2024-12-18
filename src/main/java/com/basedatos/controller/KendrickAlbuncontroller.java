package com.basedatos.controller;

import com.basedatos.entity.KendrickAlbumTrack;
import com.basedatos.repository.KendrickAlbuntrackrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class KendrickAlbuncontroller {
    @Autowired
    private KendrickAlbuntrackrepository kendrickAlbuntrackrepository;

    @GetMapping("/albuns")
    ResponseEntity<List<KendrickAlbumTrack>> all() {
        return ResponseEntity.ok(kendrickAlbuntrackrepository.findAll());
    }

    @GetMapping("/albuns/{id}")
    ResponseEntity<Map<String, Object>> one(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        return kendrickAlbuntrackrepository.findById(id)
                .map(usuario -> {
                    response.put("track", usuario);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.put("error", "track not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }


    @PostMapping("/albuns/save")
    ResponseEntity<Map<String, Object>> newAl(@RequestBody KendrickAlbumTrack newAl) {
        Map<String, Object> response = new HashMap<>();
        try {
            KendrickAlbumTrack savedTrack = kendrickAlbuntrackrepository.save(newAl);
            response.put("track", savedTrack);
            response.put("mensaje", "Track registrado exitosamente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al registrar el track");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/albuns/{id}")
    ResponseEntity<Map<String, Object>> actualizarTrack(@RequestBody KendrickAlbumTrack newTrack, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            return kendrickAlbuntrackrepository.findById(id)
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

                        KendrickAlbumTrack updatedUsuario = kendrickAlbuntrackrepository.save(usuario);
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

    @DeleteMapping("/albuns/{id}")
    ResponseEntity<Map<String, Object>> borrartrack(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (kendrickAlbuntrackrepository.existsById(id)) {
                kendrickAlbuntrackrepository.deleteById(id);
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
