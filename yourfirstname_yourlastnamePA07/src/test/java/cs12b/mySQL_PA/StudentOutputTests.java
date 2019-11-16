package cs12b.mySQL_PA;

import java.io.File;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StudentOutputTests {

	// Change this if you want to keep output files
	private static boolean SHOULD_KEEP_OUT_FILES = true;
	
	private static GenericConsoleTester tester = new GenericConsoleTester(SHOULD_KEEP_OUT_FILES);
	
	private static void testInput(String expected, String outFilePath, String...testCase) {
		try {
			tester.setUpInputStream(testCase);
			// all output files will be in the same folder: output_files
			// if the directory has not been created, this will create a directory to put them into
			File dir = new File("output_files");
			if (!dir.exists() || !dir.isDirectory()) {
				dir.mkdir();
			}
			// use system file separator to get correct path 
			tester.setUpOutStream("output_files" + System.getProperty("file.separator") + outFilePath);
			Main.main(null); 
			assertEquals(expected, tester.getActual());
		}
		// TODO add exception type 1 they were supposed to catch; if caught: fail("you were supposed to  handle <blank>");
		// TODO add exception type 2 they were supposed to catch; if caught: fail("you were supposed to  handle <blank>");
		// etc. 
		catch (Exception e) {
			fail("You were not supposed to throw any exceptions:\n" + e.toString());
		}
	}
	
	static String createTableFromLines(String... lines) {
		StringJoiner joiner = new StringJoiner("\n");
		for (String str : lines) {
			joiner.add(str);
		}
		return joiner.toString();
	}
	
	@BeforeAll
	static void setUp() {
		tester.storeOldStreams();
	}
	
	@AfterAll
	static void cleanUp() {
		tester.cleanUpStreamsAndFiles();
	} 
	
	@Test
	void testPDFOutput() {
		testInput(createTableFromLines("Y Z A 4", "M D B 3", "D C B 1"), "pdf_test_out.txt", 
				"CREATE [Table_A] (column1A, column2A, column3A, column4A)", 
				"INSERT INTO [Table_A] (1, B, C, D)", 
				"INSERT INTO [Table_A] (2, F, G, H)",
				"INSERT INTO [Table_A] (3, B, D, M)",
				"INSERT INTO [Table_A] (4, A, Z, Y)",
				"CREATE [Table_B] (column1B, column2B, column3B)",
				"INSERT INTO [Table_B] (1, A, C)",
				"INSERT INTO [Table_B] (2, B, D)",
				"SELECT (column4A, column3A, column2B, column1A) FROM [Table_A] JOIN [Table_B] ON [Table_A].column2A = [Table_B].column2B ORDER BY (column1A) DESC",
				"Q"
		);
	
	}	
	
	
	/*
	 * sp.parse("CREATE [table_name] (column1, column2, column3)");
		sp.parse("INSERT INTO [table_name] (cell1, cell2, cell3)");
		sp.parse("INSERT INTO [table_name] (cell1.1, cell2.1, cell3.1)");
		sp.parse("SELECT (column1, column2) FROM [table_name]");
		sp.parse("SELECT * FROM [table_name]");

	 */


}
