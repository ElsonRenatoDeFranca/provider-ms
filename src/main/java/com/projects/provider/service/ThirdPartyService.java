package com.projects.provider.service;

import com.projects.provider.dto.ThirdPartyDTO;
import com.projects.provider.exception.ThirdPartyNotFoundException;
import com.projects.provider.mapper.ThirdPartyMapper;
import com.projects.provider.repository.ThirdPartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThirdPartyService {

    private final ThirdPartyRepository thirdPartyRepository;
    private final ThirdPartyMapper mapper;

    private static final String THIRD_PARTY_NOT_FOUND_EXCEPTION_MESSAGE = "Third party not found";
    private static final String THIRD_PARTY_MISMATCH_EXCEPTION_MESSAGE = "Third party already exists";


    public ThirdPartyService(ThirdPartyRepository thirdPartyRepository, ThirdPartyMapper mapper) {
        this.thirdPartyRepository = thirdPartyRepository;
        this.mapper = mapper;
    }

    public void save(ThirdPartyDTO thirdPartyDTO) {
        thirdPartyRepository.save(mapper.dtoToEntity(thirdPartyDTO));
    }

    public List<ThirdPartyDTO> findAll() {
        return mapper.entityListToDtoList(thirdPartyRepository.findAll());
    }

    public ThirdPartyDTO findByThirdPartyName(String thirdPartyId) {
        return mapper.entityToDto(thirdPartyRepository.findByThirdPartyId(thirdPartyId));
    }

    public void deleteByThirdPartyName(String thirdPartyName) {
        thirdPartyRepository.deleteByName(thirdPartyName);
    }

    public void updateByThirdPartyName(ThirdPartyDTO thirdPartyDTO, String thirdPartyName) {
        var existingThirdParty = thirdPartyRepository.findByName(thirdPartyName);

        if (existingThirdParty == null) {
            throw new ThirdPartyNotFoundException(THIRD_PARTY_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        thirdPartyDTO.setId(existingThirdParty.getId());
        var updatedThirdParty = mapper.dtoToEntity(thirdPartyDTO);
        thirdPartyRepository.deleteByName(thirdPartyName);
        thirdPartyRepository.save(updatedThirdParty);
    }
}
