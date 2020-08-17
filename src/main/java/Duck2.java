import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Duck2 {
    private String name;
    private Integer age;
    private Duck2 next;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setNext(Duck2 next) {
        this.next = next;
    }
}
