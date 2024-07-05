package org.framework.rodolfo.freire.git.vertlog.repository;

import org.framework.rodolfo.freire.git.vertlog.document.Buy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Spring Data Mongo repository for managing `Buy` entities.
 * <p>
 * This interface provides common CRUD (Create, Read, Update, Delete) operations for `Buy` entities in a MongoDB database.
 * It extends the `MongoRepository` class, which offers additional features like query methods and sorting options.
 *
 * @see Buy
 * @see MongoRepository
 */

@Repository
public interface BuyRepository extends MongoRepository<Buy, Integer> {

    @Query("{'orders.date': {$eq: ?0, $eq: ?1}}")
    List<Buy> findByDate(Date startDate, Date endDate);

}
