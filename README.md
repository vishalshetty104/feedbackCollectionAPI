# Feedback Collection API
Spring boot API that allows users to collect/display feedback.

### JSON format
<code>
{
    "username": "string",
    "content": "feedback content",
    "feedbackType": "feedback type"
}
</code>

**feedbackType** is an ENUM which accepts one of 3 inputs: _POSITIVE, NEGATIVE OR NEUTRAL_

## Common Features

### 1. Submit Feedback
Submit feedback by sending a POST  request to <code>/api/feedback</code>
with a json object as the body.

### 2. Get all feedback
Recieve all current feedback by sending a GET request to <code>/api/feedback</code>

### 3. Get feedback by ID
Recieve a specific feedback by sending a GET request along with ID to <code>/api/feedback/{id}</code>

### 4. Get feedback by type
Recieve list of feedbacks based on feedback type by sending a GET request to <code>/api/feedback?type=type-name</code> where type-name = _POSITIVE, NEGATIVE OR NEUTRAL_
### 5. Update feedback by ID
Update feedback content as well as feedback type by sending a PUT request along with Id to <code>/api/feedback/{id}</code>
with the json format: <code>
{
"content": "updated feedback content",
"feedbackType":"update feedback type"
}
</code>

### 5. Delete feedback by ID
Delete a specific feedback by sending a DELETE request along with Id to <code>/api/feedback/{id}</code>

## Other Features

### 1.Export all feedback data to a .CSV file
The API outputs a .csv file to download by sending a GET request to <code>/api/exportCSV</code>