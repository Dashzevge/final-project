package edu.miu.cse.finalproject.repository;

import edu.miu.cse.finalproject.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {}

