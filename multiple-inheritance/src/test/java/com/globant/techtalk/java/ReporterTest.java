package com.globant.techtalk.java;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ReporterTest {
	private static final File TXT_FILE = new File("test-output.txt");
	private static final File JSON_FILE = new File("test-output.json");
	private static final File BIN_FILE = new File("test-output.bin");
	private static final List<Supplier<Reporter>> IMPLS = ImmutableList.of(LogReporter::new, JSONLogReporter::new, //
		SerializableLogReporter::new, //
		() -> new FileReporter(TXT_FILE), //
		() -> new JSONFileReporter(JSON_FILE), //
		() -> new SerializableFileReporter(BIN_FILE));
	private static final List<Object> CONTENTS = Arrays.asList( //
		"this is a simple text", //
		ImmutableList.of("a", 4L, true), //
		ImmutableMap.of("a", 1, "b", 4L, "c", true));
	private final Supplier<Reporter> reporterSupplier;
	private final Object content;
	private Reporter reporter;

	@Parameters(name = "{0}-{1}")
	public static Iterable<Object[]> data() {
		List<Object[]> data = new ArrayList<>(IMPLS.size() * CONTENTS.size());
		for (Supplier<Reporter> supplier : IMPLS) {
			for (Object content : CONTENTS) {
				data.add(new Object[] {
					supplier, content
				});
			}
		}
		return data;
	}

	public ReporterTest(Supplier<Reporter> reporterSupplier, Object content) {
		this.reporterSupplier = reporterSupplier;
		this.content = content;
	}

	@BeforeClass
	public static void deleteFiles() {
		Stream.of(TXT_FILE, JSON_FILE, BIN_FILE).forEach(File::delete);
	}

	@Before
	public void setup() {
		reporter = reporterSupplier.get();
	}

	@Test
	public void testReport() {
		reporter.report(content);
	}

}
