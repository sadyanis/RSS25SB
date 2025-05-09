package fr.univrouen.rss25SB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.rss25SB.entity.Item;

import java.time.LocalDateTime;
import java.util.Optional;


public interface ItemRepository extends JpaRepository<Item, Long> {

    public Optional<Item> findById(Long id);
    public void delete(Long id);

    public Optional<Item> findByTitleAndDate(String title, LocalDateTime published);
}
