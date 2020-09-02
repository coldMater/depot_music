package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.Report;
import co.coldflow.depot_music.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
