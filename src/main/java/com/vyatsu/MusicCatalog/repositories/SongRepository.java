package com.vyatsu.MusicCatalog.repositories;

import com.vyatsu.MusicCatalog.entities.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findByArtistName(String artistName,Pageable pageable);

    Page<Song> findByTitle(String songName,Pageable pageable);

    Page<Song> findSongByArtistNameAndTitle(String authorName, String songName, Pageable pageable);

    List<Song> findSongByArtistId(Long id);

    Song getSongById(Long id);



}
