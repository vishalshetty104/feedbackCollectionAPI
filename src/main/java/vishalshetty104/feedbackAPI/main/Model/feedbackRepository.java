package vishalshetty104.feedbackAPI.main.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface feedbackRepository extends JpaRepository<Feedback,Long> {
}
