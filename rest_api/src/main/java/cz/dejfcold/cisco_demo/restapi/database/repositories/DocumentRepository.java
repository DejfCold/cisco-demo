package cz.dejfcold.cisco_demo.restapi.database.repositories;

import cz.dejfcold.cisco_demo.restapi.database.mappings.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DocumentRepository extends CrudRepository<Document, UUID> {
}
