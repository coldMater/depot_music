package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Parent;
import co.coldflow.depot_music.repository.ParentRepository;
import co.coldflow.depot_music.web.dto.ParentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ParentService {
    @Autowired
    ParentRepository parentRepository;

    public Long insertParent(ParentRequestDto parentRequestDto){
        Parent parent = new Parent();

        parent.setName(parentRequestDto.getName());
        parent.setTel(parentRequestDto.getTel());

        parentRepository.save(parent);

        return parent.getId();
    }
}
