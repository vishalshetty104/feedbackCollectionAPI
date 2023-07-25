package vishalshetty104.feedbackAPI.main.Controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vishalshetty104.feedbackAPI.main.Model.Feedback;
import vishalshetty104.feedbackAPI.main.Model.feedbackType;
import vishalshetty104.feedbackAPI.main.Services.feedbackService;
import org.apache.commons.lang3.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
    public List<Feedback> getAllFeedback(@RequestParam(value = "type", required = false) feedbackType type){
        if(type==null){
            return fbService.getFeedback();
        }
        else {
            return fbService.getFeedback(type);
        }
    }
    @GetMapping("/api/feedback/{id}")
    public Optional<Feedback> getFeedbackById(@PathVariable Long id){
        return fbService.getFeedback(id);
    }
    @PutMapping("/api/feedback/{id}")
    public ResponseEntity<String> editFeedback(@RequestBody Feedback UpdatedFeedback, @PathVariable Long id){
        try {
            Feedback existingFeedback = fbService.getFeedback(id).orElse(null);
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
            if(fbService.getFeedback(id).isEmpty()){
                return new ResponseEntity<>("Feedback not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
            fbService.deleteFeedback(id);
            return new ResponseEntity<>("Feedback deleted successfully with id: "+id, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error occurred while updating feedback.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/exportCSV")
    public ResponseEntity<Resource> exportCSV() { //code reference : https://codeburst.io/returning-csv-content-from-an-api-in-spring-boot-63ea82bbcf0f
        String[] csvHeader = {"Id","username","content","feedbackType"}; //csv headings
        List<Feedback> allFeedbacks = fbService.getFeedback();
        ByteArrayInputStream byteArrayInputStream;
        try (
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(
                    new PrintWriter(out),
                    CSVFormat.DEFAULT.withHeader(csvHeader)         //default csv file format
            );
        ){
            for(Feedback feedback:allFeedbacks){ //adds entries to csv file
                csvPrinter.printRecord(feedback.getId(),feedback.getUsername(),feedback.getContent(),feedback.getFeedbackType());
            }
            csvPrinter.flush();

            byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
        InputStreamResource fileInputStream = new InputStreamResource(byteArrayInputStream);
        String csvFileName = "feedbacks.csv";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+csvFileName);
        headers.set(HttpHeaders.CONTENT_TYPE,"text/csv");
        return new ResponseEntity<>(fileInputStream,headers,HttpStatus.OK);
    }
}
