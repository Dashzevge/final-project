package edu.miu.cse.finalproject.repository;

import edu.miu.cse.finalproject.model.Job;
import edu.miu.cse.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByClient(User client);
}

