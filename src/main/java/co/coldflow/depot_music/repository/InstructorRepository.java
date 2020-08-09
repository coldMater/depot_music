package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
