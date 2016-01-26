package com.globant.techtalk.java;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp2_NoLambdas {
	private static final List<Long> IDS = Arrays.asList(1L, 2L, 3L, 6L, 10L);

	public static void main(String[] args) throws Exception {
		System.out.println("Entities to load: " + IDS);

		List<Entity> entities = IDS.stream().map(LOAD_ENTITY).collect(Collectors.toList());
		System.out.println("Entities loaded: " + entities);

		System.out
			.println("Entities names #1: " + entities.stream().map(ENTITY_TO_NAME).collect(Collectors.joining(" / ")));

		// this loads and maps to names into a single stream
		Stream<Entity> entitiesStream = IDS.stream().map(LOAD_ENTITY);
		Stream<String> namesStream = entitiesStream.map(ENTITY_TO_NAME);
		String names = namesStream.collect(Collectors.joining(" / ", "{", "}"));
		System.out.println("Entities names #2: " + names);
	}

	private static final Function<Long, Entity> LOAD_ENTITY = new Function<Long, Entity>() {

		@Override
		public Entity apply(Long id) {
			return Entity.load(id);
		}

	};

	private static final Function<Entity, String> ENTITY_TO_NAME = new Function<Entity, String>() {

		@Override
		public String apply(Entity entity) {
			return entity.getName();
		}

	};

}
