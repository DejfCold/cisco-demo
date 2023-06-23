package cz.dejfcold.cisco_demo.restapi.controllers;

import com.thedeanda.lorem.LoremIpsum;
import cz.dejfcold.cisco_demo.restapi.database.repositories.DocumentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("documents")
public class DocumentController {

    private final DocumentRepository documentRepository;

    DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<String> findById(@PathVariable UUID documentId) {
        var document = documentRepository.findById(documentId).map(it -> {
            return LoremIpsum.getInstance().getParagraphs(1, 1);
        });
        return ResponseEntity.of(document);
    }
}
