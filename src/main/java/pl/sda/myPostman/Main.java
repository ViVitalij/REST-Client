package pl.sda.myPostman;

import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.messages.CreateUserRequest;
import pl.sda.messages.CreateUserResponse;
import pl.sda.messages.GetUserResponse;
import pl.sda.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by m.losK on 2017-03-06.
 */
public class Main {
    public static final String URL = "http://localhost:8081/sda-JSON/json";

    public static void main(String[] args) throws IOException {
        List<String> userIds = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Hello! Please select:");
            System.out.println();
            System.out.println("1. Add new user");
            System.out.println("2. Print user ids list");
            System.out.println("3. Print users list");

            String choiceRaw = scanner.nextLine();
            Integer choice = Integer.parseInt(choiceRaw);

            switch (choice) {
                case 1:
                    CreateUserRequest createUserRequest = new CreateUserRequest();
                    System.out.println("Enter your name: ");
                    createUserRequest.setName(scanner.nextLine());
                    System.out.println("Enter your login: ");
                    createUserRequest.setLogin(scanner.nextLine());
                    System.out.println("Enter your mail: ");
                    createUserRequest.setMail(scanner.nextLine());

                    ObjectMapper objectMapper = new ObjectMapper();
                    String request = objectMapper.writeValueAsString(createUserRequest);

                    String createUserResponse = Sender.createUser(URL, request);

                    CreateUserResponse response = objectMapper.readValue(createUserResponse, CreateUserResponse.class);
                    System.out.println("Your id: " + response.getId());
                    userIds.add(response.getId());
                    break;

                case 2:
                    System.out.println(userIds.toString());
                    break;
                case 3:
                    List<String> getUserResponses = new ArrayList<>();
                    for (String id : userIds) {
                        getUserResponses.add(Sender.getUser(URL, id));
                    }
                    System.out.println(getUserResponses.toString());
                    break;
                default:
                    break;
            }
        }
    }
}