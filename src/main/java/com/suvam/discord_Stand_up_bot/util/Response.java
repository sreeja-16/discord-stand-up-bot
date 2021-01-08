package com.suvam.discord_Stand_up_bot.util;

import com.suvam.discord_Stand_up_bot.entity.UserResponse;
import com.suvam.discord_Stand_up_bot.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Response {
    private static ResponseService responseService;

    @Autowired
    public Response(ResponseService responseService) {
        Response.responseService = responseService;
    }

    public static void saveResponse(UserResponse userResponse) {
        responseService.saveResponse(userResponse);
    }
}
