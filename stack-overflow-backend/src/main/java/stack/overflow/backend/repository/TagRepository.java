package stack.overflow.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stack.overflow.backend.model.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
