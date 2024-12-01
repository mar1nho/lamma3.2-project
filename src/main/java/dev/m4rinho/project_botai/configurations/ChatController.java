package dev.m4rinho.project_botai.configurations;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import org.springframework.ai.ollama.OllamaChatModel;

@RestController
@CrossOrigin
public class ChatController {

    private final OllamaChatModel chatModel;

    @Autowired
    public ChatController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String message) {
        ChatResponse response = chatModel.call(
                new Prompt(
                        message,
                        OllamaOptions.builder()
                                .withModel(OllamaModel.LLAMA3_2)
                                .withTemperature(0.4)
                                .build()
                )
        );
        return response.getResult().getOutput().getContent();
    }

    @GetMapping("/stream")
    public Flux<String> chatWithStream(@RequestParam String message) {
        return chatModel.stream(
                        new Prompt(
                                message,
                                OllamaOptions.builder()
                                        .withModel(OllamaModel.LLAMA3_2)
                                        .withTemperature(0.4)
                                        .build()
                        )
                )
                .map(response -> response.getResult().getOutput().getContent());
    }
}
