package com.nila.welcome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@RestController
public class GreetingsController {

    @GetMapping("/")
    public String getGreeting() {
        return "Hello!!!";
    }

    @GetMapping("/quote")
    public String getJoke() {
        List<String> quotes = new ArrayList<>();
        quotes.add("Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better.");
        quotes.add("It does not matter how slowly you go as long as you do not stop.");
        quotes.add("The secret of getting ahead is getting started. -Mark Twain");
        quotes.add("Winners never quit, and quitters never win. -Vince Lombardi");
        quotes.add("When the going gets tough, the tough get going. -Joe Kennedy");
        quotes.add("The best way to predict the future is to create it. -Abraham Lincoln");
        quotes.add("Always make a total effort, even when the odds are against you. -Arnold Palmer");
        quotes.add("Don’t be afraid to give up the good to go for the great. -John D. Rockefeller");
        quotes.add("Don’t let the fear of losing be greater than the excitement of winning. -Robert Kiyosaki");
        quotes.add("The question isn’t who is going to let me; it’s who is going to stop me. -Ayn Rand");
        quotes.add("It is better to fail in originality than to succeed in imitation. -Herman Melville");
        quotes.add("Start where you are. Use what you have. Do what you can. -Arthur Ashe");
        quotes.add("Your present circumstances don’t determine where you can go; they merely determine where you start.\n" +
                "\n -Nido Qubein");
        quotes.add("You must expect great things of yourself before you can do them. -Michael Jordan");
        quotes.add("Do what you have to do until you can do what you want to do. -Oprah Winfrey");
        quotes.add("You can never cross the ocean until you have the courage to lose sight of the shore. -Christopher Columbus");
        quotes.add("One way to keep momentum going is to have constantly greater goals. -Michael Korda");
        quotes.add("You don’t have to see the whole staircase, just take the first step. -Martin Luther King Jr.");

        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size()));
    }

    @GetMapping("/story")
    public String getStory() {
        List<String> story = new ArrayList<>();

        story.add("“It cost too much, staying human. — Bruce Sterling, Sunny Jackson");
        story.add("And they lived happily ever after, without each other. — Harsh Snehanshu");
        story.add("One more time we were strangers, but this time with memories. — Kartik Ghodasara, Kartik Ghodasara");
        story.add("Wrong number said a familiar voice. — Kartik Ghodasara, Kartik Ghodasara");
        story.add("Dad left; a flag came back.— Anjali Bisaria");
        story.add("I met my soulmate. She didn't. — Anjali Bisaria,");
        story.add("Brought roses home. Keys didn't fit.— Anjali Bisaria,");

        Random rand = new Random();
        return story.get(rand.nextInt(story.size()));
    }

    @GetMapping("/number")
    public String getRandomNumber() {
        Random rand = new Random();
        double randDouble = rand.nextDouble();
        int randInt = rand.nextInt();

        double mathRandom = Math.random();
        return "Your random number is: "+randDouble+" or "+randInt+" or "+mathRandom;
    }

}
