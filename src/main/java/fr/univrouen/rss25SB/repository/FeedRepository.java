package fr.univrouen.rss25SB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.rss25SB.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    
}
