package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.DiscordUser;
import com.suvam.discord_Stand_up_bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscordUserServiceImpl implements DiscordUserService {
    @Autowired
    private UserRepository userRepository;

  /*  @Autowired
    public DiscordUserServiceImpl() { //UserRepository userRepository
        //this.userRepository = userRepository;
    }*/

    @Override
    public List<DiscordUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public DiscordUser findById(long id) {
        Optional<DiscordUser> result = userRepository.findById(id);
        DiscordUser question = null;

        if (result.isPresent()) {
            question = result.get();
        }
        return question;
    }

    @Override
    public DiscordUser findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void save(DiscordUser discordUser) {
        userRepository.save(discordUser);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
