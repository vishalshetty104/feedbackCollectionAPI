package vishalshetty104.feedbackAPI.main.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface feedbackRepository extends JpaRepository<Feedback,Long> {

    List<Feedback> findByfeedbackType(feedbackType feedbackType);
}
