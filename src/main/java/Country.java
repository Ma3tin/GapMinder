public class Country {
    String country;
    Double lifeExp;

    public Country(String country, Double lifeExp) {
        this.country = country;
        this.lifeExp = lifeExp;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLifeExp() {
        return lifeExp;
    }
    public void setLifeExp(Double lifeExp) {
        this.lifeExp = lifeExp;
    }
}
