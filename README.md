# Feedback Collection API
Spring boot API that allows users to collect/display feedback.

### JSON format
```
{
    "username": "string",
    "content": "feedback content",
    "feedbackType": "feedback type"
}
```

**feedbackType** is an ENUM which accepts one of 3 inputs: _POSITIVE, NEGATIVE OR NEUTRAL_

## Common Features

### 1. Submit Feedback
Submit feedback by sending a POST  request to ```/api/feedback```
with a json object as the body.

### 2. Get all feedback
Recieve all current feedback by sending a GET request to ```/api/feedback```

### 3. Get feedback by ID
Recieve a specific feedback by sending a GET request along with ID to ```/api/feedback/{id}```

### 4. Get feedback by type
Recieve list of feedbacks based on feedback type by sending a GET request to ```/api/feedback?type=type-name``` where type-name = _POSITIVE, NEGATIVE OR NEUTRAL_
### 5. Update feedback by ID
Update feedback content as well as feedback type by sending a PUT request along with Id to ```/api/feedback/{id}```
with the json format: 
```
{
    "content": "updated feedback content",
    "feedbackType":"update feedback type"
}
```

### 5. Delete feedback by ID
Delete a specific feedback by sending a DELETE request along with Id to ```/api/feedback/{id}```

## Other Features

### 1.Export all feedback data to a .CSV file
The API outputs a .csv file to download by sending a GET request to ```/api/exportCSV```