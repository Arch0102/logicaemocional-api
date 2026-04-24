package com.logicaemocional.api2.Controller;

import com.logicaemocional.api2.Dto.Request.EmotionalEntryRequest;
import com.logicaemocional.api2.Dto.Response.EmotionalEntryResponse;
import com.logicaemocional.api2.Service.EmotionalEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emotional-entries")
@RequiredArgsConstructor
public class EmotionalEntryController {

    private final EmotionalEntryService emotionalEntryService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<EmotionalEntryResponse> createEntry(
            Authentication authentication,
            @Valid @RequestBody EmotionalEntryRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emotionalEntryService.createEntry(authentication.getName(), request));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<EmotionalEntryResponse>> getMyEntries(Authentication authentication) {
        return ResponseEntity.ok(emotionalEntryService.getMyEntries(authentication.getName()));
    }

    @GetMapping("/risk/{riskLevel}")
    @PreAuthorize("hasAnyRole('PROFESSIONAL', 'ADMIN')")
    public ResponseEntity<List<EmotionalEntryResponse>> getEntriesByRiskLevel(@PathVariable String riskLevel) {
        return ResponseEntity.ok(emotionalEntryService.getEntriesByRiskLevel(riskLevel));
    }
}