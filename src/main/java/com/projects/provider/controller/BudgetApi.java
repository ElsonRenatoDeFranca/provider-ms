package com.projects.provider.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface BudgetApi {

    @GetMapping(value = "/download", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "download budget information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Download budget information",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<byte[]> download() throws IOException;


    @PostMapping(value = "/upload", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "upload budget information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Download budget information",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file ) throws IOException;

}
