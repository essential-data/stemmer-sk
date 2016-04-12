package sk.essentialdata.nlp.stemmer.sk;

import static org.apache.lucene.analysis.util.StemmerUtil.*;

/**
 * Light Stemmer for Slovak.
 * <p>
 * Implements the algorithm described in:
 * <i>
 * Indexing and stemming approaches for the Slovak language
 * </i>
 * </p>
 */
public class SlovakStemmer {
    //TODO: spracovanie slov končiacich na "nenie"
    //TODO: slovesá

    public static void main(String[] args) {
        SlovakStemmer slovakStemmer = new SlovakStemmer();
        String word = "najžľaznatejšieho";
        char[] wordCharArray = word.toCharArray();
        int size = slovakStemmer.stem(wordCharArray, wordCharArray.length);
        String stemmed = new String(wordCharArray, 0, size);
        System.out.println(stemmed);
    }

    /**
     * Stem an input buffer of Slovak text.
     *
     * @param s   input buffer
     * @param len length of input buffer
     * @return length of input buffer after normalization
     * <p>
     * <p><b>NOTE</b>: Input is expected to be in lowercase,
     * but with diacritical marks</p>
     */
    public int stem(char s[], int len) {
        len = removePredpona(s, len);
        len = removeCase(s, len);
        len = removePossessives(s, len);
        if (len > 0) {
            len = normalize(s, len);
        }
        if (len > 0) {
            len = normalize2(s, len);
        }
        return len;
    }

    private int removePredpona(char[] s, int len) {
        if (len > 6 && startsWith(s, len, "naj")) {
            return deleteN(s, 0, len, 3);
        }
        return len;
    }

    private int removeCase(char s[], int len) {
        if (len > 9 && endsWith(s, len, "ejšieho")
                || endsWith(s, len, "ejšiemu"))
            return len - 7;

        if (len > 8 && (endsWith(s, len, "ejších") ||
                endsWith(s, len, "encoch") ||
                endsWith(s, len, "ejšími") ||
                endsWith(s, len, "encami")))
            return len - 6;

        if (len > 7 && (endsWith(s, len, "ejšia") ||
                endsWith(s, len, "atami") ||
                endsWith(s, len, "atách") ||
                endsWith(s, len, "eniec") ||
                endsWith(s, len, "encom") ||
                endsWith(s, len, "ejšom") ||
                endsWith(s, len, "ejším") ||
                endsWith(s, len, "ejšej") ||
                endsWith(s, len, "ejšou") ||
                endsWith(s, len, "ejšiu") ||
                endsWith(s, len, "ejšie")))
            return len - 5;

        if (len > 6 &&
                (endsWith(s, len, "eťom") ||
                        endsWith(s, len, "iami") ||
                        endsWith(s, len, "atám") ||
                        endsWith(s, len, "aťom") ||
                        endsWith(s, len, "ovia") ||
                        endsWith(s, len, "iach") ||
                        endsWith(s, len, "atám") ||
                        endsWith(s, len, "ence") ||
                        endsWith(s, len, "ieho") ||
                        endsWith(s, len, "iemu") ||
                        endsWith(s, len, "ieme") ||
                        endsWith(s, len, "iete") ||
                        endsWith(s, len, "ejší")))
            return len - 4;

        if (len > 5 &&
                (endsWith(s, len, "ich") || //From cz
                        endsWith(s, len, "eho") ||
                        endsWith(s, len, "ych") ||
                        endsWith(s, len, "ích") ||//From cz
                        endsWith(s, len, "ého") ||//From cz
                        endsWith(s, len, "emi") ||//From cz
                        endsWith(s, len, "ému") ||//From cz
                        endsWith(s, len, "emu") ||
                        /*endsWith(s, len, "iho") ||*///Veľmi malý vplyv
                        endsWith(s, len, "ími") ||//From cz
                        endsWith(s, len, "imi") ||
                        endsWith(s, len, "ách") ||//From cz
                        endsWith(s, len, "ých") ||//From cz
                        endsWith(s, len, "ami") ||//From cz
/*                        endsWith(s, len, "ové") ||
                        endsWith(s, len, "ový") ||
                        endsWith(s, len, "oví") ||*/
                        endsWith(s, len, "ovi") ||//From cz
                        endsWith(s, len, "ieť") ||
                        endsWith(s, len, "ieš") ||
                        endsWith(s, len, "ejú") ||
                        endsWith(s, len, "ajú") ||
                        endsWith(s, len, "ujú") ||
                        endsWith(s, len, "ejú") ||
                        endsWith(s, len, "eme") ||
                        endsWith(s, len, "íte") ||
                        endsWith(s, len, "íme") ||
                        endsWith(s, len, "ými") ||//From cz
                        endsWith(s, len, "ymi") ||
                        endsWith(s, len, "ach") ||
                        endsWith(s, len, "iam") ||
                        /*endsWith(s, len, "atá") ||*/
                        endsWith(s, len, "iac") ||
                        endsWith(s, len, "ite") ||
                        endsWith(s, len, "ili") ||
                        endsWith(s, len, "ila") ||
                        endsWith(s, len, "ilo") ||
                        endsWith(s, len, "ime") ||
                        endsWith(s, len, "och")
                ))
            return len - 3;

        if (len > 4 &&
                (       /*endsWith(s, len, "ín") ||*/
                        endsWith(s, len, "ím") ||//From cz
                        endsWith(s, len, "ám") ||//From cz
                        endsWith(s, len, "am") ||
                        endsWith(s, len, "us") ||//From cz
                        endsWith(s, len, "ým") ||//From cz
                        endsWith(s, len, "ym") ||
                        endsWith(s, len, "mi") ||//From cz
                        endsWith(s, len, "ou") ||//From cz
                        endsWith(s, len, "om") ||
                        endsWith(s, len, "ej") ||
                        endsWith(s, len, "ov") ||
                        endsWith(s, len, "ia") ||
                        endsWith(s, len, "ie") ||
                        endsWith(s, len, "iu") ||
                        endsWith(s, len, "im") ||
                        endsWith(s, len, "ho") ||
                        endsWith(s, len, "mu") ||
                        endsWith(s, len, "me") ||
                        endsWith(s, len, "te") ||
                        endsWith(s, len, "ať") ||
                        endsWith(s, len, "aš") ||
                        endsWith(s, len, "úť") ||
                        endsWith(s, len, "iť") ||
                        endsWith(s, len, "íš") ||
                        endsWith(s, len, "iš") ||
                        endsWith(s, len, "il") ||
                        endsWith(s, len, "úc") ||
                        endsWith(s, len, "eš")))
            return len - 2;

        if (len > 3) {
            switch (s[len - 1]) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'ú':
                /*case 'ô':*/
                case 'y':
                case 'á':
                case 'é':
                case 'í':
                case 'ý':
                    return len - 1;
            }
        }

        return len;
    }

    private int removePossessives(char s[], int len) {
        if (len > 5 && (endsWith(s, len, "in") ||
                endsWith(s, len, "ov")))
            return len - 2;

        return len;
    }

    private int normalize(char s[], int len) {
        //toto pravidlo znižuje FP ale zvyšuje FN
/*        if (len > 1 && s[len - 2] == 'i' && s[len-1]=='c') {
            s[len - 2] = s[len - 1]; // e* > *
            return len - 1;
        }*/
        switch (s[len - 1]) {
            case 'c': // [cč] -> k
            case 'č':
                s[len - 1] = 'k';
                return len;
            case 'ľ': // [ľ] -> l
                s[len - 1] = 'l';
                return len;
            case 'ň': // [ľ] -> l
                s[len - 1] = 'n';
                return len;
            case 'ť': // [ľ] -> l
                s[len - 1] = 't';
                return len;
        }

        if (len > 3 && s[len - 3] == 'i' && (s[len - 2] == 'e' || s[len - 2] == 'a' || s[len - 2] == 'u')) {
            s[len - 3] = s[len - 2];
            s[len - 2] = s[len - 1];
            return len - 1;
        }

        return len;
    }

    private int normalize2(char s[], int len) {
        //Žiadny efekt
        /*if (len > 1 && s[len - 2] == 'z' && s[len - 2] == 'e' && s[len-1]=='ň') {
            s[len - 3] = s[len - 1]; // zeň > ň
            return len - 2;
        }*/

        if (len > 3 && s[len - 2] == 'e') {
            s[len - 2] = s[len - 1]; // e* > *
            return len - 1;
        }
        //Trochu znižuje false negative a dosť zvyšuje false positive
        /*if (len > 3 && s[len - 2] == 'í') {
            s[len - 2] = 'i'; // e* > *
            return len;
        }*/

        if (len > 3 && s[len - 2] == 'o' && s[len-1]=='k') {
            s[len - 2] = s[len - 1]; // e* > *
            return len - 1;
        }
        if (len > 3 && s[len - 2] == 'o' && s[len-1]=='l') {
            s[len - 2] = s[len - 1]; // e* > *
            return len - 1;
        }

        return len;
    }
}
