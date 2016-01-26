package com.globant.techtalk.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp2 {
	private static final List<Long> IDS = Arrays.asList(1L, 2L, 3L, 6L, 10L);

	public static void main(String[] args) throws Exception {
		System.out.println("Entities to load: " + IDS);

		List<Entity> entities = IDS.stream().map(Entity::load).collect(Collectors.toList());
		System.out.println("Entities loaded: " + entities);

		System.out.println(
			"Entities names #1: " + entities.stream().map(e -> e.getName()).collect(Collectors.joining(" / ")));

		// this loads and maps to names into a single stream
		Stream<Entity> entitiesStream = IDS.stream().map(Entity::load);
		Stream<String> namesStream = entitiesStream.map(Entity::getName);
		String names = namesStream.collect(Collectors.joining(" / ", "{", "}"));
		System.out.println("Entities names #2: " + names);
	}

}

class Entity {
	private Long id;
	private String name;

	public static Entity load(long id) {
		Entity entity = new Entity();
		entity.id = id;
		entity.name = "Entity#" + id;
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + "]";
	}

}
