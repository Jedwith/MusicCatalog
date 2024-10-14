package com.vyatsu.MusicCatalog.repositories;

import com.vyatsu.MusicCatalog.entities.Artist;
import com.vyatsu.MusicCatalog.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist getArtistById(Long id);

}
