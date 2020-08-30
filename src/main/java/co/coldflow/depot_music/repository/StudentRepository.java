package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
