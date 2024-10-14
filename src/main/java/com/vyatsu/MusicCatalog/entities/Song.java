package com.vyatsu.MusicCatalog.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "time_length", nullable = false)
    private double timeLength;
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
    @Column(name = "genius_link", nullable = false)
    private String geniusLink;

}
