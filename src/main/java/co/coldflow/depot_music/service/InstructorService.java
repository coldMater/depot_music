package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.web.dto.InstructorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    public Long insertInstructor(InstructorDto instructorDto) {
        Instructor instructorToBeSaved = new Instructor();

        instructorToBeSaved.setNickName(instructorDto.getNickName());
        instructorToBeSaved.setRealName(instructorDto.getRealName());
        instructorToBeSaved.setTel(instructorDto.getTel());
        instructorToBeSaved.setMemo(instructorDto.getMemo());
        instructorToBeSaved.setProfileInfo(instructorDto.getProfileInfo());
        instructorToBeSaved.setUsername(instructorDto.getUsername());
        instructorToBeSaved.setPassword(instructorDto.getPassword());
        instructorToBeSaved.setPortraitFileName(instructorDto.getPortrait().getOriginalFilename());

        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);

        return instructorSaved.getId();
    }
}
