package ma.enset.tpspringai.controllers;


import ma.enset.tpspringai.output.ListMovie;
import ma.enset.tpspringai.output.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentStructureController {
    private ChatClient chatClient;


    public AIAgentStructureController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @GetMapping("/askAgent")
    ListMovie askLLM(String query) {
        String message = """
                vous êtes un spécialise en domaine de cinema 
                """;
        return chatClient.prompt().system(message).user(query).call().entity(ListMovie.class);
    }
}
