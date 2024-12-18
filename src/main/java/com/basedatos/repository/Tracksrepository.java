package com.basedatos.repository;

import com.basedatos.entity.AllKendrickTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Tracksrepository extends JpaRepository<AllKendrickTrack,String> {
}
