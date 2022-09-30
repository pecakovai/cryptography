package com.example.cryptography.model.dto;

import com.example.cryptography.model.Answer;
import com.example.cryptography.model.Question;
import lombok.Data;

@Data
public class ShowAsks {

    Question question;
    Answer answer;

    public ShowAsks(Question question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }
}
