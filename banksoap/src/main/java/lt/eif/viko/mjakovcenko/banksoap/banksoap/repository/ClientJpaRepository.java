package lt.eif.viko.mjakovcenko.banksoap.banksoap.repository;


import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Client entities.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface ClientJpaRepository extends JpaRepository<Client,Long> {
}
