package vishalshetty104.feedbackAPI.main.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.Mockito;
import vishalshetty104.feedbackAPI.main.Model.Feedback;
import vishalshetty104.feedbackAPI.main.Model.feedbackRepository;
import vishalshetty104.feedbackAPI.main.Model.feedbackType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class feedbackServiceMockTest {
    @Mock
    private feedbackRepository feedbackRepo;

    @InjectMocks
    private feedbackService feedbackService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllFeedback() {

        Feedback feedback1 = new Feedback();
        Feedback feedback2 = new Feedback();
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback1);
        feedbackList.add(feedback2);

        when(feedbackRepo.findAll()).thenReturn(feedbackList);

        List<Feedback> result = feedbackService.getFeedback();

        assertEquals(feedbackList, result);
    }

    @Test
    public void testGetFeedbackById(){
        Feedback feedback1 = new Feedback("testName","testContent",feedbackType.NEUTRAL);
        feedback1.setId(23L);

        when(feedbackRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(feedback1));

        Optional<Feedback> result = feedbackService.getFeedback(23L);
        assertEquals(feedback1,result.get(),"Feedback of username: "+result.get().getUsername());
    }

    @Test
    public void testGetAllNeutralFeedbackType() {
        Feedback feedback1 = new Feedback("testName","neutralContent",feedbackType.NEUTRAL);
        List<Feedback> al = new ArrayList<>();

        al.add(feedback1);
        when(feedbackRepo.findByfeedbackType(Mockito.any(feedbackType.NEUTRAL.getClass()))).thenReturn(al);

        List<Feedback> result = feedbackService.getFeedback(feedbackType.NEUTRAL);

        assertSame(feedbackType.NEUTRAL, result.get(0).getFeedbackType());
    }
    @Test
    public void testGetAllPositiveFeedbackType() {
        Feedback feedback1 = new Feedback("testName", "neutralContent", feedbackType.POSITIVE);
        List<Feedback> al = new ArrayList<>();

        al.add(feedback1);

        when(feedbackRepo.findByfeedbackType(Mockito.any(feedbackType.POSITIVE.getClass()))).thenReturn(al);

        List<Feedback> result = feedbackService.getFeedback(feedbackType.POSITIVE);

        assertSame(feedbackType.POSITIVE, result.get(0).getFeedbackType());
    }
    @Test
    public void testGetAllNegativeFeedbackType() {
        Feedback feedback1 = new Feedback("testName", "neutralContent", feedbackType.NEGATIVE);

        List<Feedback> al = new ArrayList<>();

        al.add(feedback1);
        when(feedbackRepo.findByfeedbackType(Mockito.any(feedbackType.NEGATIVE.getClass()))).thenReturn(al);

        List<Feedback> result = feedbackService.getFeedback(feedbackType.NEGATIVE);

        assertSame(feedbackType.NEGATIVE, result.get(0).getFeedbackType());
    }


}