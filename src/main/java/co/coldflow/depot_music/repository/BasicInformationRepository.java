package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicInformationRepository  extends JpaRepository<BasicInformation, Long> {

}
