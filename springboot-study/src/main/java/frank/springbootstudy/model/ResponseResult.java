package frank.springbootstudy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseResult {

    private boolean success;
    private Object data;//业务数据
    private String message;
    private String code;//错误码
}
