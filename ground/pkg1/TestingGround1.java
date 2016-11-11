package testing.ground.pkg1;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestingGround1 {

    public static void main(String[] args) {
        try{
            Scanner input = new Scanner(new File("test.txt"));
            ArrayList<String> test = new ArrayList();
            test = parse(input.next().toUpperCase());
            for (int i = 0; i < test.size(); i++) {
                System.out.println(test.get(i));
            }
        } catch (Exception e){
            System.err.println("Caught Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static final Pattern VALID_PATTERN = Pattern.compile("[0-9]+|[A-Z]+");

private static ArrayList<String> parse(String toParse) {
    ArrayList<String> chunks = new ArrayList<String>();
    Matcher matcher = VALID_PATTERN.matcher(toParse);
    while (matcher.find()) {
        chunks.add( matcher.group() );
    }
    return chunks;
}

}