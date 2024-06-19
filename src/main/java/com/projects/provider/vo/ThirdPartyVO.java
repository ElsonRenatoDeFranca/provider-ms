package com.projects.provider.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ThirdPartyVO implements Serializable {
    @JsonIgnore
    private Long id;
    private String thirdPartyId;
    private String name;
    private String lpuProfile;
    private String provider;
    private String manager;
    private String valuePerHour;
}
