package frank.springbootstudy.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import frank.springbootstudy.model.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 定义拦截器
 * 路径匹配到执行
 */
public class LoginInterceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper;
    public LoginInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * controller中请求方法执行前 调用prehandle
     * 根据返回值 判断是否继续执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        //登陆允许访问
        if(session != null && session.getAttribute("user") != null) {
            return true;
        }
        //没有登陆 response 或者重定向
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseResult r = new ResponseResult();
        r.setSuccess(false);
        r.setCode("ERR401");
        r.setMessage("用户未登录，不允许访问");

        String json = objectMapper.writeValueAsString(r);
        PrintWriter printWriter = response.getWriter();
        printWriter.println(json);
        printWriter.flush();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
