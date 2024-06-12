package com.example.technomakers.springbatch.exercice.config;

import com.example.technomakers.springbatch.exercice.model.Hero;
import org.springframework.batch.item.ItemProcessor;

public class HeroItemProcessor implements ItemProcessor<Hero, Hero> {
    @Override
    public Hero process(Hero hero) throws Exception {
        if (hero.getExperience() <= 1) {
            hero.setScore(hero.getScore() + 1);
        }
        if (hero.getExperience() > 1) {
            hero.setScore(hero.getScore() + 2);
        }
        return hero;
    }
}
