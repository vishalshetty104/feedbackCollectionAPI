# feedbackCollectionAPI
Spring boot API that allows users to collect/display feedback.

### JSON format
<code>
{
    "username": "string",
    "content": "feedback content",
    "feedbackType": "POSITIVE OR NEGATIVE OR NEUTRAL"
}
</code>

**feedbackType** is an ENUM which accepts one of the above three inputs.

## Common endpoints

### 1. Submit Feedback
Submit feedback by sending a POST  request to <code>/api/feedback</code>
with a json object as the body.

### 2. Get all feedback
Recieve all current feedback by sending a GET request to <code>/api/feedback</code>

### 3. Get feedback by ID
Recieve a specific feedback by sending a GET request along with ID to <code>/api/feedback/{id}</code>