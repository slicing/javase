package slicing.com.secondObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ClassDeclarationViewer {
	public static void main(String[]args) throws ClassNotFoundException {
		Class<?>clazz = Class.forName("java.util.ArrayList");
		System.out.println("类的标准名称：" + clazz.getCanonicalName());
		System.out.println("类的修饰符：" + Modifier.toString(clazz.getModifiers()));

		TypeVariable<?>[]typeVariables = clazz.getTypeParameters();
		System.out.println("类的泛型参数：");
		if(typeVariables.length!=0){
			for(TypeVariable<?>typeVariable:typeVariables){
				System.out.println(typeVariable + "\t");
			}
		}else {
			System.out.println("空");
		}

		Type[]interfaces = clazz.getGenericInterfaces();
		System.out.println("类所实现的接口 ");
		if(interfaces.length!=0){
			for(Type type:interfaces){
				System.out.println("\t" + type);
			}
		}else{
			System.out.println("\t" + "空");
		}

		Type superClass = clazz.getGenericSuperclass();
		System.out.println("类的直接继承类:");
		if(superClass!=null){
			System.out.println(superClass);
		}else {
			System.out.println("空");
		}

		Annotation[]annotations = clazz.getAnnotations();
		System.out.println("类的注释信息:");
		if(annotations.length != 0){
			for(Annotation annotation:annotations){
				System.out.println("\t" + annotation);
			}
		}else {
			System.out.println("空");
		}


	}
}
