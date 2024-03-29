package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

// @WebServlet = @Controller + @RequestMapping(서블릿은 메서드 단위가 아닌 클래스 단위 맵핑만 가능)
@WebServlet("/myDispatcherServlet")  // http://localhost:8080/ch2/myDispatcherServlet?year=2021&month=9&day=8
public class MyDispatcherServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<?, ?> map = request.getParameterMap(); // Map<String, String[]>
        Model model = null;
        String viewName = "";

        try {
            Class<?> clazz = Class.forName("com.fastcampus.ch2.DateTellerMVC");
            Object obj = clazz.getDeclaredConstructor().newInstance();

            // 1. main메서드의 정보를 얻는다.
            Method main = clazz.getDeclaredMethod("getDate", int.class, int.class, int.class, Model.class);

            // 2. main메서드의 매개변수 목록(paramArr)을 읽어서 메서드 호출에 사용할 인자 목록(argArr)을 만든다.
            Parameter[] paramArr = main.getParameters();
            Object[] argArr = new Object[main.getParameterCount()];

            for (int i = 0; i < paramArr.length; i++) {
                String paramName = paramArr[i].getName();
                Class<?> paramType = paramArr[i].getType();
                Object value = map.get(paramName);

                // paramType중에 Model이 있으면, Model을 생성 & 저장
                // paramType중에 HttpServletRequest, HttpServletResponse를 처리
                if (paramType == Model.class) {
                    argArr[i] = model = new BindingAwareModelMap();
                } else if (paramType == HttpServletRequest.class) {
                    argArr[i] = request;
                } else if (paramType == HttpServletResponse.class) {
                    argArr[i] = response;
                } else if (value != null) {  // map에 Model타입이 아닌 paramName이 있으면,
                    // value와 parameter의 타입을 비교해서, 다르면 변환해서 저장
                    String strValue = ((String[]) value)[0];    // getParameterMap()에서 꺼낸 value는 String배열이므로 변환 필요
                    argArr[i] = convertTo(strValue, paramType);
                }
            }

            // 3. Controller의 main()을 호출 - YoilTellerMVC.main(int year, int month, int day, Model model)
            viewName = (String) main.invoke(obj, argArr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 4. 텍스트 파일을 이용한 rendering
        render(model, viewName, response);
    }

    private Object convertTo(Object value, Class<?> type) {
        if (type == null || value == null || type.isInstance(value)) {  // 타입이 같으면 그대로 반환
            return value;
        }

        // 타입이 다르면, 변환해서 반환
        /*if (value instanceof String && type == int.class) {
            return Integer.valueOf((String) value);
        } else if (value instanceof String && type == double.class) {
            return Double.valueOf((String) value);
        }*/
        if (value instanceof String intVal && type == int.class) { // String -> int
            return Integer.valueOf(intVal);
        } else if (value instanceof String doubleVal && type == double.class) { // String -> double
            return Double.valueOf(doubleVal);
        }

        return value;
    }

    private String getResolvedViewName(String viewName) {
        return getServletContext().getRealPath("/WEB-INF/views") + "/" + viewName + ".jsp";
    }

    // HttpServletResponse response를 통해 응답 내용을 콘솔이 아닌 브라우저로 출력
    private void render(Model model, String viewName, HttpServletResponse response) throws IOException {
        StringBuilder result = new StringBuilder();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        // 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
        Scanner sc = new Scanner(new File(getResolvedViewName(viewName)), StandardCharsets.UTF_8);

        while (sc.hasNextLine()) {
            result.append(sc.nextLine()).append(System.lineSeparator());
        }

        // 2. model을 map으로 변환
        Map<String, Object> map = model.asMap();

        // 3.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.

        // 4. replace()로 key를 value 치환한다.
        for (String key : map.keySet()) {
            result = new StringBuilder(result.toString().replace("${" + key + "}", map.get(key) + ""));
        }

        // 5.렌더링 결과를 출력한다.
        out.println(result);
    }
}