import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class GapMinderResource {
    public List<Integer> getYears() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/gapminder?user=root&password=");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("""
                                        Select DISTINCT g.year
                                        From gapminder as g
                                        ORDER BY g.year asc
                        """)
        ) {
            ArrayList<Integer> years = new ArrayList<>();

            while (resultSet.next()) {
                years.add(resultSet.getInt(1)
                );
            }

            return years;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Country> getCountriesByYear(int year) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/gapminder?user=root&password=");
                PreparedStatement statement = connection.prepareStatement(
                        """
                                Select DISTINCT g.country, g.lifeExp
                                From gapminder as g
                                WHERE g.year = ?
                                """)) {
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();

            List<Country> countries = new ArrayList<>();

            while (resultSet.next()) {
                countries.add(new Country(
                                resultSet.getString(1),
                                resultSet.getDouble(2)
                        )
                );
            }
            return countries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Country> getContinents() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/gapminder?user=root&password=");
                PreparedStatement statement = connection.prepareStatement(
                        """
                                Select g.continent, AVG(g.lifeExp)
                                From gapminder as g
                                GROUP BY g.continent
                                
                                """)) {
            ResultSet resultSet = statement.executeQuery();

            List<Country> lifeExp = new ArrayList<>();

            while (resultSet.next()) {
                lifeExp.add(new Country(
                        resultSet.getString(1),
                        resultSet.getDouble(2))
                );
            }
            return lifeExp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
