package cz.dejfcold.cisco_demo.restapi.controllers;

import cz.dejfcold.cisco_demo.restapi.controllers.requestParams.TaggedContentRequestParams;
import cz.dejfcold.cisco_demo.restapi.database.mappings.Document;
import cz.dejfcold.cisco_demo.restapi.database.mappings.Tag;
import cz.dejfcold.cisco_demo.restapi.database.repositories.TagRepository;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("taggedContent")
public class TaggedContentController {
    private final TagRepository tagRepository;

    TaggedContentController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Cacheable("taggedContentByRequestParams")
    @GetMapping
    public ResponseEntity<Collection<URI>> findByRequestParameters(@Valid TaggedContentRequestParams requestParams) {
        var tag = tagRepository.findByNameIgnoreCase(requestParams.getTag());
        var documentUris = tag.map(TaggedContentController::getDocumentUrisFromTag);
        return ResponseEntity.of(documentUris);
    }

    private static Collection<URI> getDocumentUrisFromTag(Tag tag) {
        return getDocumentsFromTag(tag).stream()
                .map(TaggedContentController::documentToUri)
                .collect(Collectors.toList());
    }

    private static URI documentToUri(Document document) {
        var documentId = document.getId();
        return WebMvcLinkBuilder.linkTo(DocumentController.class).slash(documentId).toUri();
    }

    private static Set<Document> getDocumentsFromTag(Tag tag) {
        var documents = new HashSet<>(tag.getDocuments());

        while (tag.getParentTag() != null) {
            tag = tag.getParentTag();
            documents.addAll(tag.getDocuments());
        }

        return documents;
    }
}
