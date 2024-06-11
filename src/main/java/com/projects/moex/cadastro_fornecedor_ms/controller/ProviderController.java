package com.projects.moex.cadastro_fornecedor_ms.controller;

import com.projects.moex.cadastro_fornecedor_ms.vo.ProviderVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController implements ProviderApi{
    @Override
    public ResponseEntity<Void> save(ProviderVO providerVO) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProviderVO>> findAll() {
        var providerVO = ProviderVO.builder().providerId(1L).providerName("Provider-Name").build();
        return ResponseEntity.ok(List.of(providerVO));
    }

    @Override
    public ResponseEntity<ProviderVO> findByProviderId(String providerId) {
        var providerVO = ProviderVO.builder().providerId(1L).providerName("Provider-Name").build();
        return ResponseEntity.ok(providerVO);
    }

    @Override
    public ResponseEntity<Void> deleteByProviderId(String providerId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateByProviderId(ProviderVO providerVO, String providerId) {
        return null;
    }

}
