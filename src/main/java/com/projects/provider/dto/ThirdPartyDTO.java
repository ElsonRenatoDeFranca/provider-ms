package com.projects.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ThirdPartyDTO {
    private Long id;
    private String thirdPartyId;
    private String name;
    private String lpuProfile;
    private String provider;
    private String manager;
    private String valuePerHour;
}
