package br.inatel.cafeteria.adapter.repository;

import br.inatel.cafeteria.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByCategory(String category);
}
