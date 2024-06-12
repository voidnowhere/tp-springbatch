package com.example.technomakers.springbatch.exercice.config;

import com.example.technomakers.springbatch.exercice.model.Hero;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class HeroItemReader extends FlatFileItemReader<Hero> {
    public HeroItemReader() {
        setResource(new ClassPathResource("heroes.csv"));
        setLinesToSkip(1);
        setLineMapper(new DefaultLineMapper<Hero>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "score", "experience");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Hero>() {{
                setTargetType(Hero.class);
            }});
        }});
    }
}
