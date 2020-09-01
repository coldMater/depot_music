package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    List<Parent> findAllByNameContainsOrTelContains(String name, String tel);
}
