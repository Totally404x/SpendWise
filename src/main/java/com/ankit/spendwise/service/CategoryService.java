package com.ankit.spendwise.service;

import com.google.genai.Client;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Value("${gemini.api.key}")
    private String apiKey;

    public String getCategory(String note) {
            String prompt= createPrompt(note);
            return callGemini(prompt);
    }

    private String createPrompt(String note) {
        return """
                You are an expense categorization assistant.

                Categorize the following expense into EXACTLY one category.

                Categories:
                Food
                Transport
                Hobby
                Entertainment
                Healthcare
                Education
                Recording 
                Other

                Expense:
                %s

                Respond ONLY with the category name.
                """.formatted(note);
    }

    private String callGemini(String prompt) {
        Client client=Client.builder().apiKey(apiKey).build();
        return client.models.generateContent("gemini-2.5-flash",prompt,null).text().trim();
    }
}
