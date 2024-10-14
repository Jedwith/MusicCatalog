package com.vyatsu.MusicCatalog.services;

import com.vyatsu.MusicCatalog.entities.Song;
import com.vyatsu.MusicCatalog.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public void save(Song song) {
        songRepository.save(song);
    }

    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

    public Song getSongById(Long id){
        return songRepository.getSongById(id);
    }
    public Page<Song> getSongByArtist(String artistName,Pageable pageable){
        return songRepository.findByArtistName(artistName, pageable);
    }

    public Page<Song> findWithSort(String artistName, String songName, Pageable pageable){

        if(!artistName.isEmpty() && !songName.isEmpty()){
            return songRepository.findSongByArtistNameAndTitle(artistName, songName,pageable);
        } else if(!artistName.isEmpty()){
            return songRepository.findByArtistName(artistName,pageable);
        } else if(!songName.isEmpty()){
            return songRepository.findByTitle(songName,pageable);
        } else {
            return songRepository.findAll(pageable);
        }

    }

    public List<Song> getSongsByArtistId(Long id) {
        return songRepository.findSongByArtistId(id);
    }
}
