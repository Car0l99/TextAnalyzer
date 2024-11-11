import java.io.*;
import java.util.*;


public class textAnalyzer {

    static void printHeading() {

        System.out.println("********************************************************************************");
        System.out.println("                         Writing Analyzer V1.0                                  ");
        System.out.println("********************************************************************************");

    }


    static void countWords(ArrayList<String> words, LinkedHashMap<String, Integer> linkedHashMap, BufferedReader file) throws IOException {

        /**
         * @param words a list to store words as they're read from the file
         * @param linkedHashMap to store each unique word and its count
         * @param file the BufferedReader object needs to read
         * @throws IOException if file doesn't work when reading
         */

        int total_words = 0;

        int unique_words = 0;

        String data;
        while((data = file.readLine()) != null) {

            words.clear();


            String wordsReplace = data.replaceAll("\\p{Punct}", "");
            wordsReplace = wordsReplace.toLowerCase();
            wordsReplace = wordsReplace.trim();
            String [] wordsArray = (wordsReplace.split("\\s+"));
            words.addAll(Arrays.asList(wordsArray));

            for (String word : words) {

                if (word.isEmpty()) {
                    continue;
                }


                total_words +=1;

                if (linkedHashMap.containsKey(word)) {

                    linkedHashMap.put(word, linkedHashMap.get(word) + 1);

                    continue;

                } else {

                    unique_words += 1;

                    linkedHashMap.put(word, 1);

                }
            }

        }

        System.out.println("There are " + total_words + " words in the text.");
        System.out.println("There are " + unique_words + " words in the text.");

        System.out.println("Here are the unique words and how many times each appeared: ");

        List<String> keys = new ArrayList<>(linkedHashMap.keySet());
        Collections.sort(keys);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (String key : keys) {
            sortedMap.put(key, linkedHashMap.get(key));
        }

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(String.format("%-20s %d", entry.getKey(), entry.getValue()));
        }

    }

    static void averageLength(ArrayList<String> words, BufferedReader file2) throws IOException {

        /**
         * Computes the average length
         * @param words a list to store words as they're read from the file
         * @param file the BufferedReader object needs to read
         * @throws IOException if file doesn't work when reading
         */

        int total_words = 0;
        int total_letters = 0;

        String data;
        while((data = file2.readLine()) != null) {

            words.clear();


            String wordsReplace = data.replaceAll("\\p{Punct}", "");
            wordsReplace = wordsReplace.toLowerCase();
            wordsReplace = wordsReplace.trim();
            String[] wordsArray = (wordsReplace.split("\\s+"));
            words.addAll(Arrays.asList(wordsArray));

            for (String word : words) {

                if (word.isEmpty()) {
                    continue;
                }

                total_words += 1;

                int add = word.length();

                total_letters += add;

            }
        }



            double average_length = (double)total_letters/total_words;

            String result = String.format( "%.2f", average_length);
            System.out.println("The average word length is " + result + ".");



    }

    public static void main(String[] args) throws IOException {

        /**
         * This main method initializes the program, asks user for the file they want to use,
         * and calls the methods.
         * @throws IOException if file doesn't work
         */

        ArrayList<String> words = new ArrayList<>();

        ArrayList<Integer> unique = new ArrayList<>();

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        printHeading();

        Scanner sc = new Scanner(System.in);

        Scanner fsc = null;

        String filename = null;

        while (fsc == null) {

            System.out.print("\nEnter the name of the file to analyze: ");
            filename = sc.nextLine();

            try {

                fsc = new Scanner(new File(filename));

            } catch (Exception ec) {

                System.out.println("What file could not be opened. Try again.");

            }

        }

        BufferedReader file = new BufferedReader(new FileReader(filename));
        BufferedReader file2 = new BufferedReader(new FileReader(filename));


        countWords(words, linkedHashMap, file);

        averageLength( words, file2);


            System.out.println("Thank you for using this program.");


    }
}
