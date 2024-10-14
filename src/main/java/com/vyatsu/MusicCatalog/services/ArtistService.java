package com.vyatsu.MusicCatalog.services;


import com.vyatsu.MusicCatalog.entities.Artist;
import com.vyatsu.MusicCatalog.entities.Song;
import com.vyatsu.MusicCatalog.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists(){
        return artistRepository.findAll();
    }

    public void save(Artist artist){
        artistRepository.save(artist);
    }

    public Artist getArtistById(Long id){
        return artistRepository.getArtistById(id);
    }

    public void deleteById(Long id) {
        artistRepository.deleteById(id);
    }

}
