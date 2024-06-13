package com.projects.provider.repository;


import com.projects.provider.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, String> {
    Provider findByProviderId(String providerId);

    void deleteByProviderId(String providerId);

}
