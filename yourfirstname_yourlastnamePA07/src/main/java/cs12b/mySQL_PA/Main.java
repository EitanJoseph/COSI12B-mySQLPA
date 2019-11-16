package cs12b.mySQL_PA;

import java.util.Scanner;

public class Main {

	public static final String SENTINEL = "Q";
	public static final String PROMPT = ">";
	
	public static void main(String[] args) {
		SQLParser parser = new SQLParser(new Database());
		Scanner consoleRdr = new Scanner(System.in);
		boolean running = true;
		String line = "";
		do {
			line = consoleRdr.nextLine();
			if (line.equals(SENTINEL)) {
				running = false;
			}
			else {
				parser.parse(line);
			}
		} while (running);
		
		consoleRdr.close();
	}
}
