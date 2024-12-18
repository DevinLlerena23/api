package com.basedatos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "all_kendrick_tracks")
public class AllKendrickTrack {
    @Id
    @Column(name = "track_id", nullable = false)
    private String trackId;

    @Lob
    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Lob
    @Column(name = "track_name", nullable = false)
    private String trackName;

    @Lob
    @Column(name = "album_name")
    private String albumName;

    @Lob
    @Column(name = "release_date")
    private String releaseDate;

    @Lob
    @Column(name = "explicit")
    private String explicit;

    @Column(name = "popularity")
    private Integer popularity;

    @Column(name = "duration_ms")
    private Integer durationMs;

    @Lob
    @Column(name = "album_artwork_url")
    private String albumArtworkUrl;

    @Column(name = "danceability")
    private Double danceability;

    @Column(name = "energy")
    private Double energy;

    @Column(name = "valence")
    private Double valence;

    @Column(name = "tempo")
    private Double tempo;

    @Column(name = "loudness")
    private Double loudness;

    @Column(name = "speechiness")
    private Double speechiness;

    @Column(name = "acousticness")
    private Double acousticness;

    @Column(name = "instrumentalness")
    private Double instrumentalness;

    @Column(name = "liveness")
    private Double liveness;

    @Lob
    @Column(name = "is_album_track")
    private String isAlbumTrack;

    @Lob
    @Column(name = "is_feature")
    private String isFeature;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "duration_min")
    private Double durationMin;

    @OneToOne
    private KendrickAlbumTrack kendrickAlbumTrack;

    @OneToOne
    private KendrickSinglesFeature kendrickSinglesFeature;

}