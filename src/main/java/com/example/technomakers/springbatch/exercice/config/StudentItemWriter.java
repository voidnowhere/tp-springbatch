package com.example.technomakers.springbatch.exercice.config;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.core.io.FileSystemResource;

import com.example.technomakers.springbatch.exercice.model.Student;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StudentItemWriter extends FlatFileItemWriter<Student> {

    private static final String HEADER = "Name,Marks,Mention";
    private static final String FILE_NAME = "students_results.csv"; // Specify the output folder here

    public StudentItemWriter() {
        setResource(new FileSystemResource(FILE_NAME));
        setLineAggregator(new DelimitedLineAggregator<Student>() {{
            setDelimiter(",");
            setFieldExtractor((FieldExtractor<Student>) student -> {
                String mention = "not bad";
                if (student.getMarks() >= 10){
                    mention = "good";
                }
                if (student.getMarks() >= 15){
                    mention = "very good";
                }
                return new Object[] {
                        student.getName(), student.getMarks(), mention
                };
            });
        }});
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        super.open(executionContext);
        writeHeader();
    }

    private void writeHeader() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path) || isFileEmpty(path)) {
            try (Writer writer = Files.newBufferedWriter(path)) {
                writer.write(HEADER);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new ItemStreamException("Erreur de création de header", e);
            }
        }
    }

    private boolean isFileEmpty(Path path) {
        try {
            return Files.size(path) == 0;
        } catch (IOException e) {
            throw new ItemStreamException("Pas de possibilté de vérifier l'xistance de fichier", e);
        }
    }
}