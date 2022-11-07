package br.com.eniac.eniac2f2022.controllers;

import br.com.eniac.eniac2f2022.enums.OutputTypes;
import br.com.eniac.eniac2f2022.utils.ObjectGenerator;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/download")
public class FileController {

    private final String FILE_NAME = "src/main/resources/fileToCreate.txt";
    private Faker faker;

    @GetMapping
    public ResponseEntity download() throws Exception {

        File file = new File(FILE_NAME);
        if (file.createNewFile()) {
            log.info("[FileController] >> file created");
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        String data = ObjectGenerator.generateRandomDemoObject(OutputTypes.JSON, faker);
        writer.write(data);

        writer.close();

        return ResponseEntity.ok(writer.toString());
    }
}
