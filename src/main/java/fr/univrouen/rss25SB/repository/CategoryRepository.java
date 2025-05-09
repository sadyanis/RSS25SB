package fr.univrouen.rss25SB.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.rss25SB.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
