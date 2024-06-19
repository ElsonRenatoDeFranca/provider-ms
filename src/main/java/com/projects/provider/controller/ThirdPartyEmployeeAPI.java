package com.projects.provider.controller;

import com.projects.provider.vo.ProviderVO;
import com.projects.provider.vo.ThirdPartyVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface ThirdPartyEmployeeAPI {
    @PostMapping(value = "/add", produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Save a third party employee  to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Save a third party employee to database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406",
                    description = "The third party employee is already at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> save(@RequestBody ThirdPartyVO thirdPartyVO);


    @GetMapping(value = "/findall", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find all third party employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find all providers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<List<ThirdPartyVO>> findAll();


    @GetMapping(value = "/findByThirdPartyName/{thirdPartyName}", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find by thirdPartyId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find by thirdPartyId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "thirdPartyId not found at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<ThirdPartyVO> findByThirdPartyName(@PathVariable("thirdPartyName") String thirdPartyName);

    @DeleteMapping("/delete/{thirdPartyName}")
    @Operation(summary = "Delete by thirdPartyName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete by thirdPartyName",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "thirdPartyId not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> deleteByThirdPartyName(@PathVariable("thirdPartyName") String thirdPartyName);


    @RequestMapping(value = "/update/{thirdPartyName}", method = RequestMethod.PUT, produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Update by thirdPartyName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update by thirdPartyName",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "thirdPartyName not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> updateByThirdPartyName(@RequestBody ThirdPartyVO thirdPartyVO, @PathVariable("thirdPartyName") String thirdPartyName);

}
