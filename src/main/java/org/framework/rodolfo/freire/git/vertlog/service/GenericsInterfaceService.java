package org.framework.rodolfo.freire.git.vertlog.service;

import java.util.List;
import java.util.Optional;

/**
 * Defines a generic service interface that provides basic CRUD (Create, Read, Update, Delete) operations for entities of type `T`.
 * <p>
 * This interface utilizes generics to allow for type-safe operations on various entity types. It assumes that the `T` type represents an entity managed by the service.
 *
 * @param <T> The type of the entities managed by this service.
 */
public interface GenericsInterfaceService<T> {

    /**
     * Retrieves all entities of type `T` from the underlying data source.
     *
     * @return A list containing all entities of type `T`, or an empty list if no entities exist.
     */
    List<T> findAll();

    /**
     * Retrieves a specific entity of type `T` by its unique identifier.
     *
     * @param id The unique identifier of the entity to retrieve.
     * @return An `Optional` object containing the entity with the specified ID, or an empty `Optional` if no entity is found.
     */
    Optional<T> findById(int id);

    /**
     * Saves an entity of type `T` to the underlying data source.
     * <p>
     * If the entity already exists with the same identifier, it will be updated. Otherwise, it will be created as a new entity.
     *
     * @param t The entity to be saved.
     * @return The saved entity after any modifications have been applied.
     */
    T save(T t);
}