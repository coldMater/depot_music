package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.web.dto.InstructorRequestDto;
import co.coldflow.depot_music.web.dto.InstructorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.Path;

@Service
@Transactional
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

    public List<InstructorResponseDto> selectInstructorList() {
        List<Instructor> existingInstructorList = instructorRepository.findAll();

        ArrayList<InstructorResponseDto> instructorResponseDtoArrayList = new ArrayList<>();
        for(Instructor instructor: existingInstructorList){
            instructorResponseDtoArrayList.add(new InstructorResponseDto(instructor));
        }

        return instructorResponseDtoArrayList;
    }

    public void updateInstructor(Long id, InstructorRequestDto instructorRequestDto) {
        Instructor instructorToBeEdited = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        instructorToBeEdited.setNickName(instructorRequestDto.getNickName());
        instructorToBeEdited.setRealName(instructorRequestDto.getRealName());
        instructorToBeEdited.setTel(instructorRequestDto.getTel());
        instructorToBeEdited.setMemo(instructorRequestDto.getMemo());
        instructorToBeEdited.setProfileInfo(instructorRequestDto.getProfileInfo());
    }

    public String getImagePath(long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return instructor.getFilePath();
    }
}