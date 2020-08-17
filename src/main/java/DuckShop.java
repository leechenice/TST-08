import lombok.ToString;

import java.util.List;

@ToString
public class DuckShop {
    private List<Duck> ducks;

    public List<Duck> getDucks() {
        return ducks;
    }

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }
}
