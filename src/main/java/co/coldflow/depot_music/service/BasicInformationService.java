package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.BasicInformation;
import co.coldflow.depot_music.repository.BasicInformationRepository;
import co.coldflow.depot_music.web.dto.BasicInformationRequestDto;
import co.coldflow.depot_music.web.dto.BasicInformationResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BasicInformationService {
    private BasicInformationRepository basicInformationRepository;

    public BasicInformationService(BasicInformationRepository basicInformationRepository) {
        this.basicInformationRepository = basicInformationRepository;
    }


    public BasicInformationResponseDto selectBasicInformation() {
        Optional<BasicInformation> basicInfo = basicInformationRepository.findById(1L);

        if(basicInfo.isPresent()){
            return new BasicInformationResponseDto(basicInfo.get());
        } else {
            BasicInformation basicInformation = new BasicInformation();
            return new BasicInformationResponseDto(
                basicInformationRepository.save(basicInformation)
            );
        }
    }

    public void updateBasicInformation(BasicInformationRequestDto basicInformationRequestDto) {
        BasicInformation basicInfo = basicInformationRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("기본정보가 존재하지 않습니다."));

        basicInfo.setTitle(basicInformationRequestDto.getTitle());
        basicInfo.setAddress(basicInformationRequestDto.getAddress());
        basicInfo.setChiefName(basicInformationRequestDto.getChiefName());
        basicInfo.setChiefTel(basicInformationRequestDto.getChiefTel());
        basicInfo.setEmail(basicInformationRequestDto.getEmail());
        basicInfo.setTel(basicInformationRequestDto.getTel());
    }
}
