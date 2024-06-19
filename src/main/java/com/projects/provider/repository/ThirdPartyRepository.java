package com.projects.provider.repository;


import com.projects.provider.model.ThirdPartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdPartyEntity, String> {
    ThirdPartyEntity findByName(String thirdPartyName);
    ThirdPartyEntity findByThirdPartyId(String thirdPartyId);
    void deleteByName(String thirdPartyName);

}
