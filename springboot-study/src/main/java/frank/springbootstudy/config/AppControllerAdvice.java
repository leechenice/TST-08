package frank.springbootstudy.config;

import frank.springbootstudy.model.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//指定身份，拦截controller中web请求的类
@ControllerAdvice
public class AppControllerAdvice implements ResponseBodyAdvice<Object> {

    //指定处理请求方法中抛出的异常
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Object handle(Exception e) {
        e.printStackTrace();
        return null;
    }

        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;//执行controller中的web请求方法结束，返回数据时，是否重写响应体
        }

        @Override
        public ResponseResult beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            ResponseResult r = new ResponseResult();
            r.setSuccess(true);
            r.setData(o);

            return r;
        }

}

