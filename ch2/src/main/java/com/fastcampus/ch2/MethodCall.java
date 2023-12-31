package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class MethodCall {
    public static void main(String[] args) throws Exception {
        HashMap<String, String> map = new HashMap<>();    // 1. map 생성
        System.out.println("before : " + map);

        ModelController mc = new ModelController();
        String viewName = mc.toDataMap(map);    // 2. 생성한 map을 ModelController의 toDataMap메서드에 넘김
        // 5. 반환된 view 이름을 viewName에 저장
        // map은 이쪽에서 생성해 넘겨준 것이기 때문에 반환 필요 x
        // MethodCall의 map과 ModelController의 map이 같은 주소를 가리킴
        System.out.println("after : " + map);

        render(map, viewName);    // 6. data와 view 이름을 render함수에 넘겨줌
    }

    static void render(HashMap<String, String> map, String viewName) throws IOException {
        StringBuilder result = new StringBuilder();

        // 7. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
        Scanner sc = new Scanner(new File(viewName + ".txt"));

        while (sc.hasNextLine()) {
            result.append(sc.nextLine()).append(System.lineSeparator());
        }

        // 8. map에 담긴 key를 하나씩 읽어서 template의 ${key}를 value바꾼다.

        // 9. replace()로 key를 value 치환한다.
        for (String key : map.keySet()) {
            result = new StringBuilder(result.toString().replace("${" + key + "}", map.get(key)));
        }

        // 10. 렌더링 결과를 콘솔에 출력한다.
        System.out.println(result);
    }
}

class ModelController {
    public String toDataMap(HashMap<String, String> map) {
        map.put("id", "asdf");    // 3. 작업 결과를 넘겨받은 map에 저장
        map.put("pwd", "1111");

        return "txtView2";    // 4. view 이름을 반환
    }
}