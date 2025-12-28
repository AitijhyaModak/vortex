package com.aitijhya.vortex.repositories;

import com.aitijhya.vortex.entities.MediaAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaAsset, String> {
}
