package ro.betfair.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.betfair.springboot.entity.Employer;

import java.util.UUID;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, UUID> {
}