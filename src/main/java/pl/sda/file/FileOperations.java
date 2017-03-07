package pl.sda.file;

/**
 * Created by m.losK on 2017-03-07.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {
    public static void main(String[] args) throws IOException {
        String message = "Hello world";
        File file = new File("C:\\Users\\RENT\\Desktop\\test.txt");
//        exampleWriteToFile(message, file);
//        exampleReadFromFile(file);
//        splitLineFromFile(file);
//        readLinesFromFile(file);

        List<User> users = readUsersFromFile(file);
        users.forEach(e -> System.out.println(e));
        System.out.println();

        User user = new User();
        user.setFirstName("Hakuna");
        user.setLastName("Matata");
        user.setAge(18);
        addUserToFile(user, file);

        users = readUsersFromFile(file);
        users.forEach(e -> System.out.println(e));


    }

    public static void addUserToFile(User user, File file) throws IOException {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(user.toString());
        }

    }

    private static List<User> readUsersFromFile(File file) throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] split = nextLine.split(" ");

                User user = new User();
                user.setFirstName(split[0]);
                user.setLastName(split[1]);
                user.setAge(new Integer(split[2]));

                users.add(user);
            }
            return users;
        }
    }

    private static void readLinesFromFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) { //try = auto closeable
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    private static void splitLineFromFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            String nextLine = scanner.nextLine();
            String[] names = nextLine.split(" ");
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i]);
            }
        }
    }

    private static void exampleReadFromFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            String s = scanner.nextLine();
            System.out.println(s);
        }
    }

    public static void exampleWriteToFile(String message, File file) throws IOException {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message);
        }
    }
}