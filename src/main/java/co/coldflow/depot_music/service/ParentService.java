package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Parent;
import co.coldflow.depot_music.repository.ParentRepository;
import co.coldflow.depot_music.web.dto.ParentRequestDto;
import co.coldflow.depot_music.web.dto.ParentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParentService {
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public ParentResponseDto insertParent(ParentRequestDto parentRequestDto){
        Parent parent = new Parent();

        parent.setName(parentRequestDto.getName());
        parent.setTel(parentRequestDto.getTel());

        parentRepository.save(parent);

        return new ParentResponseDto(parent.getId(), parent.getName(), parent.getTel());
    }

    public List<ParentResponseDto> selectParentList(String keyword) {
        List<Parent> parentList = parentRepository.findAllByNameContainsOrTelContains(keyword, keyword);

        List<ParentResponseDto> parentListToBeReturn = new ArrayList();

        for(Parent parent : parentList){
            parentListToBeReturn.add(
                    new ParentResponseDto(
                            parent.getId(),
                            parent.getName(),
                            parent.getTel()
                    )
            );
        }

        return parentListToBeReturn;
    }
}
