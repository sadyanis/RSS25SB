package fr.univrouen.rss25SB.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.rss25SB.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
