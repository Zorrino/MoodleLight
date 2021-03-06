package fr.uca.springbootstrap.repository;

import fr.uca.springbootstrap.models.Resource.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByName(String name);

    Optional<Question> findById(Long name);
}

