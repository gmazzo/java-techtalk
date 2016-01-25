package com.globant.techtalk.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ClassDescriber {
	public final Class<?> target;
	public final boolean isInterface;
	public final ClassDescriber superclass;
	public final List<Field> fields;
	public final List<Method> methods;
	public final List<ClassDescriber> implementedInterfaces;

	public ClassDescriber(Class<?> target) {
		this.target = target;
		this.isInterface = target.isInterface();
		this.superclass = target.getSuperclass() != null ? new ClassDescriber(target.getSuperclass()) : null;
		this.fields = Arrays.asList(target.getDeclaredFields());
		this.methods = Arrays.asList(target.getDeclaredMethods());

		this.implementedInterfaces = new ArrayList<>();
		for (Class<?> i : target.getInterfaces()) {
			this.implementedInterfaces.add(new ClassDescriber(i));
		}
	}

	/**
	 * This is not complete, does not show the {@link Constructor}s for example
	 */
	public String describe() {
		StringBuilder sb = new StringBuilder();

		// adds package (if declared)
		if (target.getPackage() != null) {
			sb.append("package ");
			sb.append(target.getPackage().getName());
			sb.append(";\n\n");
		}

		// adds class definition
		describeModifier(sb, target.getModifiers());
		if (!isInterface) {
			sb.append("class ");

		}
		describeType(sb, target, false);
		sb.append(" ");

		// adds superclass 
		if (superclass != null && !Object.class.equals(superclass.target)) {
			sb.append("extends ");
			describeType(sb, superclass.target);
			sb.append(" ");
		}
		// adds interfaces
		if (!implementedInterfaces.isEmpty()) {
			sb.append("implements ");
			for (Iterator<ClassDescriber> it = implementedInterfaces.iterator(); it.hasNext();) {
				describeType(sb, it.next().target);
				sb.append(it.hasNext() ? ", " : " ");
			}
		}
		sb.append("{\n");

		describeFields(sb, this);
		sb.append("\n");
		describeMethods(sb, this);

		sb.append("}\n");
		return sb.toString();
	}

	private void describeModifier(StringBuilder sb, int mod) {
		String modifiers = Modifier.toString(mod);
		if (!modifiers.isEmpty()) {
			sb.append(modifiers);
			sb.append(" ");
		}
	}

	private <U> void describeType(StringBuilder sb, Class<U> clazz) {
		describeType(sb, clazz, true);
	}

	private <U> void describeType(StringBuilder sb, Class<U> clazz, boolean fullName) {
		if (clazz.isArray()) {
			sb.append(clazz.getComponentType());
			sb.append("[]");

		} else {
			sb.append(fullName ? clazz.getName() : clazz.getSimpleName());
		}
		if (clazz.getTypeParameters().length > 0) {
			sb.append("<");

			for (Iterator<TypeVariable<Class<U>>> it = Arrays.asList(clazz.getTypeParameters()).iterator(); it
				.hasNext();) {
				sb.append(it.next().getTypeName());
				sb.append(it.hasNext() ? ", " : "");
			}
			sb.append(">");
		}
	}

	private void indent(StringBuilder sb, int count) {
		for (int i = 0; i < count; i++) {
			sb.append("    ");
		}
	}

	private void describeFields(StringBuilder sb, ClassDescriber describer) {
		for (Field field : describer.fields) {
			indent(sb, 1);
			describeModifier(sb, field.getModifiers());
			describeType(sb, field.getType());
			sb.append(" ");
			sb.append(field.getName());
			sb.append("\n");
		}
	}

	private void describeMethods(StringBuilder sb, ClassDescriber describer) {
		for (Method method : describer.methods) {
			indent(sb, 1);
			describeModifier(sb, method.getModifiers());
			describeType(sb, method.getReturnType());
			sb.append(" ");
			sb.append(method.getName());
			sb.append("(");

			for (Iterator<Parameter> it = Arrays.asList(method.getParameters()).iterator(); it.hasNext();) {
				Parameter p = it.next();

				describeModifier(sb, p.getModifiers());
				sb.append(p.getType());
				sb.append(" ");
				sb.append(p.getName());
				sb.append(it.hasNext() ? ", " : "");
			}
			sb.append(");\n\n");
		}
	}
}
