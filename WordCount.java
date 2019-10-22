import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class WordCount {
	Hashtable<String, Integer> wordCount;

	public WordCount() {
		// Creating a hashtable that maps strings to their integer count
		this.wordCount = new Hashtable<String, Integer>();
	}

	private void readFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		StringTokenizer st;
		while (line != null) {
			// Tokenizing line by line on the space
			st = new StringTokenizer(line, " ");
			while (st.hasMoreElements()) {
				String word = (String)st.nextElement();
				// If word is found, add 1 to the count,
				// else create a new entry
				if (wordCount.containsKey(word)) {
					wordCount.put(word, wordCount.get(word) + 1);
				} else {
					wordCount.put(word, 1);
				}
			}
			line = reader.readLine();
		}
		reader.close();

	}

	public void countWords(String directory, boolean showOutput) throws IOException {
		File dir = new File(directory);
		File[] files = dir.listFiles();
		// Iterate through each file in the directory
		if (files != null) {
			for (File file : files) {
				readFile(file);
			}
		}

		if (showOutput) {
			Enumeration words = wordCount.keys();
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./local_output.txt"), "utf-8"));
	
			// Write the contents of the hashmap to the file
			while (words.hasMoreElements()) {
				String word = (String)words.nextElement();
				
				writer.write(word + " " + this.wordCount.get(word) + "\n");
			}
			writer.flush();
			writer.close();
		}
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		WordCount wordCount = new WordCount();
		// Taking as input the file directory containing the input files
		wordCount.countWords(args[0], true);
		System.out.println("Total Time in Milliseconds " + (System.currentTimeMillis() - startTime));
    System.out.println("Total Time in Seconds " + (System.currentTimeMillis() - startTime) * 0.001F);
	}
}