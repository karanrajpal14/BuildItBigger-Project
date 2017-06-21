package com.karan;

import java.util.Random;

public class JokeTeller {

    Random random = new Random();

    public String makeMeLOL() {

        String[] jokesArray = {"Apple > Google", "Windows 10 is beautiful and stable AF", "Linux is crap", "Ubisoft is the best"};
        int index = random.nextInt(jokesArray.length);
        return jokesArray[index];
    }
}