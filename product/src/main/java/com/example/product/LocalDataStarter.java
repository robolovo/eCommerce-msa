package com.example.product;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class LocalDataStarter implements CommandLineRunner {

    private final DataLoader dataLoader;

    @Override
    public void run(String... args) throws Exception {
        dataLoader.loadData();
    }

}
