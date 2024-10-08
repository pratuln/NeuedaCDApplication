package com.example.CDLibrary.services;

import com.example.CDLibrary.dataaccess.ArtistRepository;
import com.example.CDLibrary.dataaccess.CdRepository;
import com.example.CDLibrary.model.Artist;
import com.example.CDLibrary.model.CD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CdServiceImpl implements CdService{

    CdRepository cdRepository;
    ArtistRepository artistRepository;

    @Override
    public Iterable<CD> findAll() {
    return cdRepository.findAll();
    }

    @Override
    public List<CD> findByArtistNameOrderByTitle(String name) {
        return cdRepository.findAllByArtistNameOrderedByTitle(name);
    }

    @Override
    public CD saveCD(CD cd) {
        Artist artist = cd.getArtist();
        Optional<Artist> artistOptional = artistRepository.findByName(artist.getName());

        if (artistOptional.isPresent()) {
            artist = artistOptional.get();
        } else {
            // If the artist does not exist in the database, create a new artist and save it in the DB.
            //This will give the artist a DB ID so that we can then commit the CD to the database.
            Artist realArtist = new Artist();
            realArtist.setName(artist.getName());
            artist = artistRepository.save(realArtist);
        }
        cd.setArtist(artist);
        return cdRepository.save(cd);
    }

    @Autowired
    public void setCdRepository(CdRepository cdRepository) {
        this.cdRepository = cdRepository;
    }

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
}


