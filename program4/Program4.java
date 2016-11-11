//NAME: Kaleb Cole
//PROJECT: Project 4
//DATE: 4/16/2016
package program4;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import static program4.CharacterStat.Comparators.*;

public class Program4 {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BB = BLUE + "|" + RESET;
    public static final String PB = PURPLE + "|" + RESET;

    public static List statList = new ArrayList();
    public static final String[] words = {"Strength", "Constitution", "Intelligence", "Wisdom", "Dexterity", "Charisma", "Fighter", "Ranger", "Mage ", "Cleric", "Rouge", "Bard "};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        readFile();
        displayStats("");
        while (!choiceSelection(input)) {
        }
    }

    public static boolean choiceSelection(Scanner input) {
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 1) {
                System.out.printf(BB + BLUE + " %2d : %-12s " + BB + BB + BLUE + " %2d : %-12s " + BB + "\n", (i + 1), words[i], (i + 7), words[i + 6]);
            } else {
                System.out.printf(BB + " %2d : %-12s " + BB + BB + " %2d : %-12s " + BB + "\n", (i + 1), words[i], (i + 7), words[i + 6]);
            }
        }
        System.out.printf(BB + " %2s : %-12s " + BB + GREEN + "  %-12s " + RESET + ": ", "Q", "Quit Program", "Your Choice?");
        String choice = input.next().toLowerCase();
        System.out.println("");
        switch (choice) {
            case "1":
                statList.sort(STRENGTH);
                break;
            case "2":
                statList.sort(CONSTITUTION);
                break;
            case "3":
                statList.sort(INTELLIGENCE);
                break;
            case "4":
                statList.sort(WISDOM);
                break;
            case "5":
                statList.sort(DEXTERITY);
                break;
            case "6":
                statList.sort(CHARISMA);
                break;
            case "7":
                statList.sort(FIGHTER);
                break;
            case "8":
                statList.sort(RANGER);
                break;
            case "9":
                statList.sort(MAGE);
                break;
            case "10":
                statList.sort(CLERIC);
                break;
            case "11":
                statList.sort(ROUGE);
                break;
            case "12":
                statList.sort(BARD);
                break;
            case "q":
                System.out.println(GREEN + "Goodbye!");
                return true;
            default:
                System.out.println(RED + "!! ERROR: Not a valid choice !!\n");
                return false;
        }
        Collections.reverse(statList);
        displayStats(choice);
        return false;
    }

    public static void readFile() {
        try {
            FileInputStream input = new FileInputStream(new File("CharacterStats.dat"));
            int readByte, position = 0, statCount = 1;
            while ((readByte = input.read()) != -1) {
                if (readByte != 0) {
                    switch (statCount) {
                        case 1:
                            statList.add(position, new CharacterStat(position));
                            ((CharacterStat) statList.get(position)).setStrength(readByte);
                            statCount++;
                            break;
                        case 2:
                            ((CharacterStat) statList.get(position)).setConstitution(readByte);
                            statCount++;
                            break;
                        case 3:
                            ((CharacterStat) statList.get(position)).setIntelligence(readByte);
                            statCount++;
                            break;
                        case 4:
                            ((CharacterStat) statList.get(position)).setWisdom(readByte);
                            statCount++;
                            break;
                        case 5:
                            ((CharacterStat) statList.get(position)).setDexterity(readByte);
                            statCount++;
                            break;
                        case 6:
                            ((CharacterStat) statList.get(position)).setCharisma(readByte);
                            statCount = 1;
                            position++;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(RED + "Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void displayStats(String choice) {
        for (int i = 0; i < 12; i++) {
            if (((i + 1) + "").equals(choice)) {
                System.out.printf(PB + PURPLE + "%.5s" + PB, words[i]);
            } else {
                System.out.printf(BB + CYAN + "%.5s" + BB, words[i]);
            }
        }
        System.out.println("");
        for (int i = 0; i < statList.size(); i++) {
            switch (choice) {
                case "1":
                    System.out.printf(PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "2":
                    System.out.printf(BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "3":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "4":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "5":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "6":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "7":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "8":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "9":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "10":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "11":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                case "12":
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + PB + PURPLE + " %-3d " + PB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
                    break;
                default:
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB, stat(i, "st"), stat(i, "co"), stat(i, "in"), stat(i, "wi"), stat(i, "de"), stat(i, "ch"));
                    System.out.printf(BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + BB + " %-3d " + BB + "\n", calc(i, "fi"), calc(i, "ra"), calc(i, "ma"), calc(i, "cl"), calc(i, "ro"), calc(i, "ba"));
            }
        }
        System.out.println("");
    }

    public static int stat(int position, String stat) {
        switch (stat) {
            case "st":
                return ((CharacterStat) statList.get(position)).getStrength();
            case "co":
                return ((CharacterStat) statList.get(position)).getConstitution();
            case "in":
                return ((CharacterStat) statList.get(position)).getIntelligence();
            case "wi":
                return ((CharacterStat) statList.get(position)).getWisdom();
            case "de":
                return ((CharacterStat) statList.get(position)).getDexterity();
            case "ch":
                return ((CharacterStat) statList.get(position)).getCharisma();
            default:
                return 0;
        }
    }

    public static int calc(int i, String profession) {
        switch (profession) {
            case "fi":
                return (stat(i, "st") * 10 + stat(i, "co") * 5 + stat(i, "in") * 1 + stat(i, "wi") * 2 + stat(i, "de") * 5 + stat(i, "ch") * 2);
            case "ra":
                return (stat(i, "st") * 5 + stat(i, "co") * 10 + stat(i, "in") * 2 + stat(i, "wi") * 5 + stat(i, "de") * 2 + stat(i, "ch") * 1);
            case "ma":
                return (stat(i, "st") * 2 + stat(i, "co") * 1 + stat(i, "in") * 10 + stat(i, "wi") * 5 + stat(i, "de") * 2 + stat(i, "ch") * 5);
            case "cl":
                return (stat(i, "st") * 2 + stat(i, "co") * 5 + stat(i, "in") * 2 + stat(i, "wi") * 10 + stat(i, "de") * 1 + stat(i, "ch") * 5);
            case "ro":
                return (stat(i, "st") * 5 + stat(i, "co") * 2 + stat(i, "in") * 5 + stat(i, "wi") * 1 + stat(i, "de") * 10 + stat(i, "ch") * 2);
            case "ba":
                return (stat(i, "st") * 1 + stat(i, "co") * 2 + stat(i, "in") * 5 + stat(i, "wi") * 2 + stat(i, "de") * 5 + stat(i, "ch") * 10);
            default:
                return 0;
        }
    }
}
