package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.Account;
import co.coldflow.depot_music.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByAccount(Account account);
}
