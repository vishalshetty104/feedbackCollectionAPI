package vishalshetty104.feedbackAPI.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vishalshetty104.feedbackAPI.main.Model.Feedback;
import vishalshetty104.feedbackAPI.main.Services.feedbackService;

import java.util.List;
import java.util.Optional;

@RestController
public class feedbackController {
    @Autowired
    feedbackService fbService;

    @GetMapping("/api")
    public String testResponse(){
        return "API says Hi!";
    }
    @PostMapping("/api/feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback){
        try{
            Long id = fbService.saveFeedback(feedback);
            return new ResponseEntity<>("Feedback Submitted Successfully!" +" Feedback Id = "+id, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error occurred while submitting feedback.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/feedback")
    public List<Feedback> getAllFeedback(){
        return fbService.getFeedback();
    }
    @GetMapping("/api/feedback/{id}")
    public Optional<Feedback> getFeedbackById(@PathVariable Long id){
        return fbService.getById(id);
    }
    @PutMapping("/api/feedback/{id}")
    public ResponseEntity<String> editFeedback(@RequestBody Feedback UpdatedFeedback, @PathVariable Long id){
        try {
            Feedback existingFeedback = fbService.getById(id).orElse(null);
            if (existingFeedback == null) {
                return new ResponseEntity<>("Feedback not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
            existingFeedback.setContent(UpdatedFeedback.getContent());
            existingFeedback.setFeedbackType(UpdatedFeedback.getFeedbackType());
            Long savedFeedback = fbService.saveFeedback(existingFeedback);
            return new ResponseEntity<>("Feedback updated successfully with id: "+savedFeedback, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error occurred while updating feedback.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/api/feedback/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id){
        try {
            if(fbService.getById(id).isEmpty()){
                return new ResponseEntity<>("Feedback not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
            fbService.deleteFeedback(id);
            return new ResponseEntity<>("Feedback deleted successfully with id: "+id, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error occurred while updating feedback.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
