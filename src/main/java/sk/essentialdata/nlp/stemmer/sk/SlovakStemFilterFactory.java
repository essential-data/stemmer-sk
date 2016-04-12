package sk.essentialdata.nlp.stemmer.sk;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import java.util.Map;

/**
 * Factory for {@link SlovakStemFilter}.
 * <pre class="prettyprint">
 * &lt;fieldType name="text_skstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.SlovakStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class SlovakStemFilterFactory extends TokenFilterFactory {
  
  /** Creates a new SlovakStemFilterFactory */
  public SlovakStemFilterFactory(Map<String, String> args) {
    super(args);
    if (!args.isEmpty()) {
      throw new IllegalArgumentException("Unknown parameters: " + args);
    }
  }
  
  @Override
  public TokenStream create(TokenStream input) {
    return new SlovakStemFilter(input);
  }
}
