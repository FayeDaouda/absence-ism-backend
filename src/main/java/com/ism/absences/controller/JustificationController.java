package com.ism.absences.controller;

import com.ism.absences.entity.Justification;
import com.ism.absences.service.JustificationService;
import com.ism.absences.utils.MultipartInputStreamFileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/justifications")
public class JustificationController {

    @Autowired
    private JustificationService justificationService;

    @Autowired
    private RestTemplate restTemplate;

    // 🔁 GET toutes les justifications
    @GetMapping
    public List<Justification> getAll() {
        return justificationService.getAllJustifications();
    }

    // 🔁 GET justifications par étudiant
    @GetMapping("/etudiant/{id}")
    public List<Justification> getByEtudiant(@PathVariable String id) {
        return justificationService.getByEtudiantId(id);
    }

    // 🔁 GET justifications par absence
    @GetMapping("/absence/{id}")
    public List<Justification> getByAbsence(@PathVariable String id) {
        return justificationService.getByAbsenceId(id);
    }

    // 🔁 GET justifications par statut
    @GetMapping("/statut/{statut}")
    public List<Justification> getByStatut(@PathVariable String statut) {
        return justificationService.getByStatut(statut);
    }

    // ➕ POST créer une justification simple
    @PostMapping
    public Justification create(@RequestBody Justification justification) {
        return justificationService.create(justification);
    }

    // ✏️ PUT mise à jour
    @PutMapping("/{id}")
    public ResponseEntity<Justification> update(@PathVariable String id, @RequestBody Justification updated) {
        Optional<Justification> justification = justificationService.update(id, updated);
        return justification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ❌ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        justificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📤 POST justification avec fichiers
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<Justification> createWithFiles(
            @RequestPart("justification") String justificationJson,
            @RequestPart("files") List<MultipartFile> files
    ) {
        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
    
        try {
            // Désérialiser le JSON en objet Justification
            ObjectMapper mapper = new ObjectMapper();
            Justification justification = mapper.readValue(justificationJson, Justification.class);
    
            List<String> urls = new ArrayList<>();
    
            for (MultipartFile file : files) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", new MultipartInputStreamFileResource(
                        file.getInputStream(),
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType()
                ));
    
                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    
                // 🔁 Correction de l'URL du microservice
                String imageServiceUrl = "https://image-service-et99.onrender.com/api/images/upload";
    
                String uploadedUrl = restTemplate.postForObject(imageServiceUrl, requestEntity, String.class);
                urls.add(uploadedUrl);
            }
    
            justification.setFichiers(urls);
            justification.setDateSoumission(LocalDate.now());
            justification.setStatut("En attente");
    
            Justification saved = justificationService.create(justification);
            return ResponseEntity.ok(saved);
    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
