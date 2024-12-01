package dev.m4rinho.project_botai.configurations;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig implements CommandLineRunner {

    @Autowired
    private OllamaChatModel chatModel;


    public void generatePirateNames() {
        ChatResponse response = chatModel.call(
                new Prompt(
                        "Me ajude escrevendo um poema baseado em um amor que nunca será correspondido!",
                        OllamaOptions.builder()
                                .withModel(OllamaModel.LLAMA3_2)
                                .withTemperature(0.4)
                                .build()
                )
        );

        System.out.println(response.getResult().getOutput().getContent());
    }

    @Override
    public void run(String... args) throws Exception {
        generatePirateNames();
    }
}
