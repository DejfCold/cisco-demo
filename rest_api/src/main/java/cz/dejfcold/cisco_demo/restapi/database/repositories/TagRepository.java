package cz.dejfcold.cisco_demo.restapi.database.repositories;

import cz.dejfcold.cisco_demo.restapi.database.mappings.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends CrudRepository<Tag, UUID> {
    Optional<Tag> findByNameIgnoreCase(String name);

}
