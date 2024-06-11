package com.projects.provider.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProviderVO {
    @JsonProperty("idFornecedor")
    private Long providerId;
    @JsonProperty("nomeFornecedor")
    private String providerName;
}
