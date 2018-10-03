package slicing.com.secondObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StringUtils {
	public String toString(Object object) {
		Class clazz = object.getClass();
		StringBuilder sb = new StringBuilder();
		Package packsgeName = clazz.getPackage();
		sb.append("包名：：" +packsgeName.getName()+ "\t");
		String className = clazz.getSimpleName();
		sb.append("类名：" + className + "\n");
		sb.append("公共构造方法：\n");
		Constructor[]constructors = clazz.getDeclaredConstructors();
		for(Constructor constructor:constructors){
			String modifier = Modifier.toString(constructor.getModifiers());	//获得修饰符
			if (modifier.contains("public")) {						//查看修饰符是否含有“public”
				sb.append(constructor.toGenericString() + "\n");
			}
		}
		sb.append("公共域：\n");
		Field[] fields = clazz.getDeclaredFields();						//获得代表所有域的Field数组
		for (Field field : fields) {
			String modifier = Modifier.toString(field.getModifiers());
			if (modifier.contains("public")) {						//查看修饰符是否含有“public”
				sb.append(field.toGenericString() + "\n");
			}
		}
		sb.append("公共方法：\n");
		Method[] methods = clazz.getDeclaredMethods();				// 获得代表所有方法的Method[]数组
		for (Method method : methods) {
			String modifier = Modifier.toString(method.getModifiers());
			if (modifier.contains("public")) {						//查看修饰符是否含有“public”
				sb.append(method.toGenericString() + "\n");
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(new StringUtils().toString(new java.util.Date()));
	}
}

