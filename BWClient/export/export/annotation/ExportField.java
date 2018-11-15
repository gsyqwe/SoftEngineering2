package export.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ ElementType.FIELD, ElementType.METHOD }) // 定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented // 说明该注解将被包含在javadoc中
/**
 * 用作描述需要導出至Excel的字段
 * 
 * @author 161250051
 *
 */
public @interface ExportField {

	/**
	 * 字段所在頁數
	 * 
	 * @return
	 */
	int sheet() default 0;

	/**
	 * 字段名称
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * 字段名稱所在行數
	 * 
	 * @return
	 */
	int nameRow() default 0;

	/**
	 * 字段名稱所在列數
	 * 
	 * @return
	 */
	int nameCol() default 0;
	//
	// /**
	// * 是否具有值
	// *
	// * @return
	// */
	// boolean containValue() default false;
	//
	// /**
	// * 值所在列數
	// *
	// * @return
	// */
	// int valueRow() default 0;
	//
	// /**
	// * 值所在行數
	// *
	// * @return
	// */
	// int valueCol() default 0;
	//
	// boolean isCollection() default false;

}