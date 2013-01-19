
package frigo.dojo;

import com.google.common.annotations.VisibleForTesting;

public class WordRotator {

    public String rotateWord (String word) {
        String odd = getOddCharacters(word);
        String even = getEvenCharacters(word);
        odd = rotateRight(odd);
        even = rotateLeft(even);
        return interleave(odd, even);
    }

    @VisibleForTesting
    String getOddCharacters (String word) {
        return getEverySecondCharacterFrom(word, 0);
    }

    @VisibleForTesting
    String getEvenCharacters (String word) {
        return getEverySecondCharacterFrom(word, 1);
    }

    private String getEverySecondCharacterFrom (String word, int startIndex) {
        StringBuilder builder = new StringBuilder();
        for( int i = startIndex; i < word.length(); i += 2 ){
            builder.append(word.charAt(i));
        }
        return builder.toString();
    }

    @VisibleForTesting
    String rotateRight (String string) {
        if( string.length() == 0 ){
            return string;
        }
        return string.charAt(string.length() - 1) + string.substring(0, string.length() - 1);
    }

    @VisibleForTesting
    String rotateLeft (String string) {
        if( string.length() == 0 ){
            return string;
        }
        return string.substring(1) + string.charAt(0);
    }

    @VisibleForTesting
    String interleave (String odd, String even) {
        StringBuilder builder = new StringBuilder();
        builder.setLength(odd.length() + even.length());
        for( int i = 0; i < odd.length(); i++ ){
            builder.setCharAt(2 * i, odd.charAt(i));
        }
        for( int i = 0; i < even.length(); i++ ){
            builder.setCharAt(2 * i + 1, even.charAt(i));
        }
        return builder.toString();
    }

}
