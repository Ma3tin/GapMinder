import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;


@Named
@RequestScoped
public class GapMinderService {
    GapMinderResource gmr = new GapMinderResource();

    public List<Integer> getYears() {
        return gmr.getYears();
    }
    public int getParam() {
        return Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("year"));
    }

    public List<Country> getCountries() {
        return gmr.getCountriesByYear(getParam());
    }

    public List<Country> getContinents() {
        return gmr.getContinents();
    }
}
