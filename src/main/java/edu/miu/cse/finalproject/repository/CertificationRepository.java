package edu.miu.cse.finalproject.repository;

import edu.miu.cse.finalproject.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {}

