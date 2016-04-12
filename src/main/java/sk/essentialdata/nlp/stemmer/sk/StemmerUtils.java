package sk.essentialdata.nlp.stemmer.sk;

/**
 * @author Filip Bednárik
 * @since 12. 4. 2016
 */
public class StemmerUtils {
    private static SlovakStemmer slovakStemmer = new SlovakStemmer();

    public static String stem(String token) {
        char[] wordCharArray = token.toCharArray();
        int size = slovakStemmer.stem(wordCharArray, wordCharArray.length);
        return new String(wordCharArray, 0, size);
    }
}
