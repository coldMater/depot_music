package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.web.dto.InstructorRequestDto;
import co.coldflow.depot_music.web.dto.InstructorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    public Long insertInstructor(InstructorRequestDto instructorRequestDto, Path filePath) {
        Instructor instructorToBeSaved = new Instructor();

        instructorToBeSaved.setNickName(instructorRequestDto.getNickName());
        instructorToBeSaved.setRealName(instructorRequestDto.getRealName());
        instructorToBeSaved.setTel(instructorRequestDto.getTel());
        instructorToBeSaved.setMemo(instructorRequestDto.getMemo());
        instructorToBeSaved.setProfileInfo(instructorRequestDto.getProfileInfo());
        instructorToBeSaved.setUsername(instructorRequestDto.getUsername());
        instructorToBeSaved.setPassword(instructorRequestDto.getPassword());
        instructorToBeSaved.setFileName(instructorRequestDto.getPortrait().getOriginalFilename());
        instructorToBeSaved.setFilePath(filePath.toString());

        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);

        return instructorSaved.getId();
    }

    public InstructorResponseDto selectInstructor(long id) {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new InstructorResponseDto(existingInstructor);
    }

    public String getImagePath(long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return instructor.getFilePath();
    }
}