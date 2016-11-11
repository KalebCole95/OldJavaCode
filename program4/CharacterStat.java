//NAME: Kaleb Cole
//PROJECT: Project 4
//DATE: 4/16/2016
package program4;

import java.util.Comparator;

public class CharacterStat implements Comparable<CharacterStat>{

    private int strength, constitution, intelligence, wisdom, dexterity, charisma;

    public CharacterStat(int currentPositionInput) {
        strength = 0;
        constitution = 0;
        intelligence = 0;
        wisdom = 0;
        dexterity = 0;
        charisma = 0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    @Override
    public int compareTo(CharacterStat o){
        return 0;
    }
    
    public static class Comparators{
         public static final Comparator<CharacterStat> STRENGTH = (CharacterStat o1, CharacterStat o2) -> Integer.compare(o1.getStrength(), o2.getStrength());
         public static final Comparator<CharacterStat> CONSTITUTION = (CharacterStat o1, CharacterStat o2) -> Integer.compare(o1.getConstitution(), o2.getConstitution());
         public static final Comparator<CharacterStat> INTELLIGENCE = (CharacterStat o1, CharacterStat o2) -> Integer.compare(o1.getIntelligence(), o2.getIntelligence());
         public static final Comparator<CharacterStat> WISDOM = (CharacterStat o1, CharacterStat o2) -> Integer.compare(o1.getWisdom(), o2.getWisdom());
         public static final Comparator<CharacterStat> DEXTERITY = (CharacterStat o1, CharacterStat o2) -> Integer.compare(o1.getDexterity(), o2.getDexterity());
         public static final Comparator<CharacterStat> CHARISMA = (CharacterStat o1, CharacterStat o2) -> Integer.compare(o1.getCharisma(), o2.getCharisma());
         public static final Comparator<CharacterStat> FIGHTER = (CharacterStat o1, CharacterStat o2) -> Integer.compare(Program4.calc(Program4.statList.indexOf(o1),"fi"),Program4.calc(Program4.statList.indexOf(o2),"fi"));
    public static final Comparator<CharacterStat> RANGER = (CharacterStat o1, CharacterStat o2) -> Integer.compare(Program4.calc(Program4.statList.indexOf(o1),"ra"),Program4.calc(Program4.statList.indexOf(o2),"ra"));
   public static final Comparator<CharacterStat> MAGE = (CharacterStat o1, CharacterStat o2) -> Integer.compare(Program4.calc(Program4.statList.indexOf(o1),"ma"),Program4.calc(Program4.statList.indexOf(o2),"ma"));
   public static final Comparator<CharacterStat> CLERIC = (CharacterStat o1, CharacterStat o2) -> Integer.compare(Program4.calc(Program4.statList.indexOf(o1),"cl"),Program4.calc(Program4.statList.indexOf(o2),"cl"));
   public static final Comparator<CharacterStat> ROUGE = (CharacterStat o1, CharacterStat o2) -> Integer.compare(Program4.calc(Program4.statList.indexOf(o1),"ro"),Program4.calc(Program4.statList.indexOf(o2),"ro"));
   public static final Comparator<CharacterStat> BARD = (CharacterStat o1, CharacterStat o2) -> Integer.compare(Program4.calc(Program4.statList.indexOf(o1),"ba"),Program4.calc(Program4.statList.indexOf(o2),"ba"));
   
    }
}
