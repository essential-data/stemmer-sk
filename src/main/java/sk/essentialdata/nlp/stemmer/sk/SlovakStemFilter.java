package sk.essentialdata.nlp.stemmer.sk;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute;

import java.io.IOException;

/**
 * A {@link TokenFilter} that applies {@link sk.essentialdata.nlp.stemmer.sk.SlovakStemmer} to stem Slovak words.
 * <p>
 * To prevent terms from being stemmed use an instance of
 * {@link SetKeywordMarkerFilter} or a custom {@link TokenFilter} that sets
 * the {@link KeywordAttribute} before this {@link TokenStream}.
 * </p>
 * <p><b>NOTE</b>: Input is expected to be in lowercase, 
 * but with diacritical marks</p>
 * @see SetKeywordMarkerFilter
 */
public final class SlovakStemFilter extends TokenFilter {
  private final SlovakStemmer stemmer = new SlovakStemmer();
  private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
  private final KeywordAttribute keywordAttr = addAttribute(KeywordAttribute.class);
  
  public SlovakStemFilter(TokenStream input) {
    super(input);
  }

  @Override
  public boolean incrementToken() throws IOException {
    if (input.incrementToken()) {
      if(!keywordAttr.isKeyword()) {
        final int newlen = stemmer.stem(termAtt.buffer(), termAtt.length());
        termAtt.setLength(newlen);
      }
      return true;
    } else {
      return false;
    }
  }
}
