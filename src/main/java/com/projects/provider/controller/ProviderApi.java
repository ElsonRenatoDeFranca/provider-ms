package com.projects.provider.controller;


import com.projects.provider.vo.ProviderVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/provider")
public interface ProviderApi {
    @PostMapping(value = "/add-provider", produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Save a provider to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Save a provider to database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406",
                    description = "The provider is already at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> save(@RequestBody ProviderVO providerVO);


    @GetMapping(value = "/findall", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find all providers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find all providers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<List<ProviderVO>> findAll();


    @GetMapping(value = "/findprovider/{providerId}", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find provider by providerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find a provider by providerId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "provider not found at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<ProviderVO> findByProviderId(@PathVariable("providerId") String providerId);

    @DeleteMapping("/deleteprovider/{providerId}")
    @Operation(summary = "Delete by providerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete by providerId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "provider not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> deleteByProviderId(@PathVariable("providerId") String providerId);


    @RequestMapping(value = "/updateprovider/{providerId}", method = RequestMethod.PUT, produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Update by providerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update by providerId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "provider not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> updateByProviderId(@RequestBody ProviderVO providerVO, @PathVariable("providerId") String providerId);




}
