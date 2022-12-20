package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Person> people = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/users.csv"))) {
            String delimiter = ",|#|\"\"|;";
            String line;
            Pattern pattern = Pattern.compile(",|#|\"\"|;");
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String[] columns = line.split(delimiter);
                    int id = Integer.parseInt(columns[0]);
                    String name = columns[1];
                    String email = columns[2];
                    String country = columns[3];
                    people.add(new Person(id, name, email, country));
                }
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        for (Person p : people) {
            p.PrintPerson();
        }
    }
}