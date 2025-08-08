package ma.enset.tpspringai.controllers;

import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiModelController {
    private ChatClient chatClient;

    @Value("classpath:imgs/architec.png")
    private Resource resourceImage;

    public MultiModelController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/describeImg")
    public String  descripeImage() {
        return chatClient.prompt()
                .system("merci de me donner une description pour cette image")
                .user(u -> u.text("Decrire cette image").media(MediaType.IMAGE_PNG,resourceImage)
                        ).call().content();

    }
}
