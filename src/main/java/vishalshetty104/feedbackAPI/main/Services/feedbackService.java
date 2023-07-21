package vishalshetty104.feedbackAPI.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vishalshetty104.feedbackAPI.main.Model.Feedback;
import vishalshetty104.feedbackAPI.main.Model.feedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class feedbackService {
    @Autowired
    private feedbackRepository feedbackRepo;

    public List<Feedback> getFeedback(){
        return feedbackRepo.findAll();
    }
    public Optional<Feedback> getById(Long id){
        return feedbackRepo.findById(id);
    }

    public Long saveFeedback(Feedback feedback){
        Feedback savedFeedback = feedbackRepo.save(feedback);
        return savedFeedback.getId();
    }

    public void deleteFeedback(Long id){
        feedbackRepo.deleteById(id);
    }

}
