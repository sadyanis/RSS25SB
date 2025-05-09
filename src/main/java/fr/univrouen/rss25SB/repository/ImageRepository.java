package fr.univrouen.rss25SB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.rss25SB.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}