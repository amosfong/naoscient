package com.naoscient.tools;

import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Amos Fong
 */
public class SourceFormatter {

	public static void main(String[] args) {
		File file = new File(System.getenv("PWD") + "/app/src/main/java");

		_formatJavaFiles(file);
	}

	private static void _formatJavaFiles(File file) {
		if (file.isDirectory()) {
			for (File curFile : file.listFiles()) {
				_formatJavaFiles(curFile);
			}
		}
		else {
			try {
				String source = new String(Files.readAllBytes(file.toPath()));

				String newSource = source.replace("	", "\t");
				newSource = newSource.replace("\t" + System.lineSeparator(), System.lineSeparator());
				newSource = newSource.replace(" " + System.lineSeparator(), System.lineSeparator());

				if (!source.equals(newSource)) {
					System.out.println(file.getCanonicalPath());

					FileWriter fileWriter = new FileWriter(file, false);

					fileWriter.write(newSource);

					fileWriter.close();
				}
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}