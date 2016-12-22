import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static final String[][] team_and_location = {{"texan","houston"}, {"cowboy", "dallas"},
            {"charger", "san diego"}, {"bear", "chicago"}, {"ram", "los angeles"}, {"dolphin", "miami"},
            {"patriot", "new england"}, {"packer", "green bay"}, {"jet", "new york"}, {"bill", "buffalo"},
            {"bronco", "denver"}, {"lion", "detroit"}, {"raider", "oakland"}, {"brown", "cleveland"},
            {"buccaneer", "tampa bay"}, {"redskin", "washington"}, {"viking", "minnesota"}, {"jaguar", "jacksonville"},
            {"saint", "new orleans"}, {"eagle", "philadelphia"}, {"chief", "kansas city"}, {"raven", "baltimore"},
            {"falcon", "atlanta"}, {"colt", "indianapolis"}, {"steeler", "pittsburgh"}, {"cardinal", "arizona"},
            {"titan", "tennessee"}, {"seahawk", "seattle"}, {"bengal", "cincinnati"}};

    public static final String[] other_assorted_locations = {"eatonville", "ravenswood", "florida", "georgia",
            "orlando", "vermont", "university park", "xavier", "yosemite national park"};

    public static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    public static String team = team_and_location[random_team(team_and_location.length)][0];
    public static ArrayList<String> description = new ArrayList<>();

    public static Scanner scanner_for_loop;

    public static String guess_for_loop;

    public static char response_for_loop;
    //Methods

    public static int random_team(int max)
    {
        int range = max - 1;
        return (int)(Math.random() * range);
    }

    /**
     * This method finds a location that starts with a given character
     * @param c a character to find a location
     * @return location sharing that character as a first letter
     */
    public static String use_letter(char c){
        for(int i = 0; i<team_and_location.length; i++) {
            if (team_and_location[i][1].charAt(0) == c)
                return team_and_location[i][1];
        }
        for(int i=0; i<other_assorted_locations.length; i++) {
            if (other_assorted_locations[i].charAt(0) == c)
                return other_assorted_locations[i];
        }
        return "error";
    }

    /**
     * This method updates a 2D string array to a randomized version. Order within each array is not swapped.
     *
     * @param arr a 2D string array to shuffle
     */

    public static void shuffleArray(String[][] arr) {
        Random rgen = new Random();

        for (int i = 0; i < arr.length; i++) {
            int randPos = rgen.nextInt(arr.length);
            String[] tmp = arr[i];
            arr[i] = arr[randPos];
            arr[randPos] = tmp;
        }
    }

    /**
     * This method creates a description using locations around the country, given an NFL football team
     * @param team the football team to create a description for
     * @return description
     */
    public static String team_description(String team) {

        String[] vowel_times = {"a night", "two nights", "three nights", "four nights", "five nights"};
        char[] team_char_array = new char[team.length()];


        String final_description = "";
        //splits team into characters and puts each character into team_char_array
        for (int i = 0; i < team.length(); i++) {
            team_char_array[i] = team.charAt(i);
        }


        //Creates syntax agreement for the first character, accommodating "for X nights"

        int first_loop_counter = 0;
        for(int j = 0; j<5; j++) {
            if (team_char_array[1] == vowels[j]) {
                description.add("First I went to " + use_letter(team_char_array[0]));
            }
            else {
                first_loop_counter++;
            }
        }
        if(first_loop_counter == 5)
            description.add("First I went to " + use_letter(team_char_array[0]) + ",");


        //Here begins description syntax arithmetic, starting at i=1 since the first character has already been done.

        for (int i = 1; i < team_char_array.length; i++) {

            boolean cons_before_letter = true;
            //if cons_before_letter = true after the loop, there is no vowel before this character.
            for (int j = 0; j < 5; j++) {
                if (team_char_array[i - 1] == vowels[j])
                    cons_before_letter = false;
            }
            if (cons_before_letter) {
                    int counter = 0;
                    for (int j = 0; j<5; j++) {
                        if (team_char_array[i] == vowels[j])
                            description.add(" for " + vowel_times[j] + ",");
                        else
                            counter++;
                    }
                    //this 'if' statement makes the test if the current character is not a vowel.
                    int second_loop_counter = 0;
                    if (counter == 5) {
                        for (int j = 0; j < 5; j++) {
                            //testing if the next character is a vowel
                            try {
                                if (team_char_array[i + 1] == vowels[j]) {
                                    description.add(" then I went to " + use_letter(team_char_array[i]));
                                } else
                                    second_loop_counter++;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                description.add(" then I went to " + use_letter(team_char_array[i]) + ".");
                                break;
                            }
                        }
                        if (second_loop_counter == 5)
                            description.add(" then I went to " + use_letter(team_char_array[i]) + ",");
                    }
                }

            else {
                int second_loop_counter = 0;
                for (int j = 0; j < 5; j++) {
                      //testing if the next character is a vowel
                      try {
                          if (team_char_array[i + 1] == vowels[j]) {
                                description.add(" then I went to " + use_letter(team_char_array[i]));
                          } else
                              second_loop_counter++;
                          }
                      catch (ArrayIndexOutOfBoundsException e) {
                              description.add(" then I went to " + use_letter(team_char_array[i]) + ".");
                              break;
                          }
                          }
                          if (second_loop_counter == 5)
                            description.add(" then I went to " + use_letter(team_char_array[i]) + ",");
            }
        }

        //extracts all elements from the array list and makes a description

        for(int i = 0; i<description.size(); i++){
            final_description = final_description + description.get(i);
        }

        return final_description;
    }






    /**
     * This answer checks to see if your guess was correct, and will respond with a message
     * @param guess your guess for the location
     * @return a string responding whether or not you were correct
     */
    public static String answer_check(String guess) {
        for(int i=0;i<team_and_location.length; i++){
             if(team_and_location[i][0].equals(team)){
                 if(team_and_location[i][1].equals(guess))
                     return "You got it ;)";
                 else
                     return "Nope. It was " + team_and_location[i][1];
             }
        }
        return "answer_check method failed";
    }

    public static void main(String[] args) {
        System.out.println(team_description(team));
        Scanner scan = new Scanner(System.in);
        System.out.println("Where am I?");
        String guess = scan.nextLine();

        System.out.println(answer_check(guess));

        boolean desire_to_play;

        System.out.println("Want to play again? Y or N");
        char response = scan.next().charAt(0);

        if (response == 'Y') {
            desire_to_play = true;
            while (desire_to_play) {
                shuffleArray(team_and_location);
                team = team_and_location[random_team(team_and_location.length)][0];
                description.clear();

                System.out.println(team_description(team));
                scanner_for_loop = new Scanner(System.in);
                System.out.println("Where am I?");
                guess_for_loop = scanner_for_loop.nextLine();
                System.out.println(answer_check(guess_for_loop));
                System.out.println("Want to play again? Y or N");
                response_for_loop = scanner_for_loop.next().charAt(0);

                if (response_for_loop == 'N'){break;}
            }
        }
    }
}