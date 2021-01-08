package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.UserResponse;
import com.suvam.discord_Stand_up_bot.repository.UserResponseRepository;
import com.suvam.discord_Stand_up_bot.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private UserResponseRepository userResponseRepository;

    public void saveResponse(UserResponse userResponse) {
        userResponseRepository.save(userResponse);
    }

    public ByteArrayInputStream loadResponses() {
        List<UserResponse> responses = userResponseRepository.findAll();
        ByteArrayInputStream in = CSVHelper.responsesToCSV(responses);
        return in;
    }

    public List<UserResponse> loadResponsesByDate (Date selectedDate) {
        return userResponseRepository.loadResponsesByDate(selectedDate);
    }

}
