package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall2 {
	public static void main(String[] args) throws Exception{
		// 1. DateTellerMVC의 객체를 생성
		Class<?> clazz = Class.forName("com.fastcampus.ch2.DateTellerMVC");
		Object obj = clazz.newInstance();

		// 2. main메서드의 정보를 가져온다
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		// 3. Model을 생성
		// Model은 interface이기 때문에 그 구현체인 BindingAwareModelMap class를 통해 객체 생성
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model="+model);
		
		// 4. main메서드를 호출(Reflection API - invoke)
		// 매개변수는 Object 배열로 담는다.
		// String viewName = obj.main(2022, 9, 8, model);	// Reflection API를 이용하지 않은 호출
		String viewName = (String)main.invoke(obj, new Object[] { 2022, 9, 8, model });	// Reflection API를 이용
		System.out.println("viewName="+viewName);	
		
		// Model의 내용을 출력 
		System.out.println("[after] model="+model);
				
		// 5. 텍스트 파일을 이용한 rendering
		render(model, viewName);			
	} // main
	
	static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		// 6. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다. - jsp 이용
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 7. model을 map으로 변환 
		Map<String, Object> map = model.asMap();
		
		// 8.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
		Iterator<String> it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 9. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		// 10. 렌더링 결과를 출력한다.
		System.out.println(result);
	}
}