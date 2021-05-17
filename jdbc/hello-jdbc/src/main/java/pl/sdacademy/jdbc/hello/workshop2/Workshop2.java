package pl.sdacademy.jdbc.hello.workshop2;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Workshop2 {
    public static void main(String[] args) {
        System.out.println("Podaj nazwę kontynentu:");
        final String continent = new Scanner(System.in).nextLine();

        List<String> countries = getCountries(continent);
        final String countriesString = String.join("\n", countries);
        System.out.println(countriesString);
    }

    private static List<String> getCountries(String continent) {
        final List<String> countries = new LinkedList<>();
        try (final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "19891017Aleksandra")) {
            try (final PreparedStatement statement = connection.prepareStatement("select name from country where continent = ? order by name;")) {
                statement.setString(1, continent);
                final ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    countries.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

        return countries;
    }
}
