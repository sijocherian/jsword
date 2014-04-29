package org.crosswire.jsword.index.search;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.Books;
import org.crosswire.jsword.index.lucene.analysis.AbstractBookAnalyzer;
import org.crosswire.jsword.index.lucene.analysis.EnglishLuceneAnalyzer;
import org.crosswire.jsword.index.lucene.analysis.GermanLuceneAnalyzer;
import org.crosswire.jsword.index.lucene.analysis.TestUtil;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.crosswire.jsword.passage.Passage;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * A test for dev.
 */
public class TestSearchInBook {

    @Test
    public void testSimpleSearch() throws BookException, NoSuchKeyException {
        Book bible = Books.installed().getBook("ESV");
        //String myref = "gen 20";
        //System.out.println(myref+ " , Key: " +  bible.getKey(myref).getName());
        //myref = "[gen-ruth]";
        //System.out.println(myref+ " , Key: " +  bible.getKey(myref).getName());
        org.crosswire.common.util.Version versionObj = (org.crosswire.common.util.Version)bible.getBookMetaData().getProperty("Version");
        System.out.println("Bible ver: " + versionObj);

                // This does a standard op erator search. See the search documentation
        // for more examples of how to search
        String myquery = field+": (john) ";
        //String myquery = "heading:(david)";
        //String myquery = "content:(jesus peter)";
        //String myquery = "+moses +aaron";
        //myquery = "+Moses";

        Key key = bible.find(myquery);
        System.out.println(myquery+ " , ResultList: " + key.getName());

        // You can also trim the result to a more manageable quantity.
        // The test here is not necessary since we are working with a bible. It
        // is necessary if we don't know what it
        // is.
        if (key instanceof Passage) {
            Passage remaining = ((Passage) key).trimVerses(5);
            System.out.println("The first 5 verses : " + key.getName());
            if (remaining != null) {
                System.out.println("The rest of the verses are: " + remaining.getName());
            } else {
                System.out.println("TrimVerse(5) is null");
            }
        }
    }



    //Test analyzer
    @Test
    public void testQueryTokenization() throws ParseException {
        // From john 3:16
        AbstractBookAnalyzer myAnalyzer = new GermanLuceneAnalyzer();
        myAnalyzer.setDoStemming(true);

        QueryParser parser = new QueryParser(Version.LUCENE_29, field, myAnalyzer);

        //Denn Gott hat der Welt seine Liebe dadurch gezeigt, dass er seinen einzigen Sohn für sie hergab, damit jeder, der an ihn glaubt, das ewige Leben hat und nicht verloren geht.
        String testInput = "dass er seinen einzigen Sohn für sie hergab";
        Query query = parser.parse(testInput);
        System.out.println(query.toString());
        // Lowercased test
        assertTrue(query.toString().indexOf(field + ":soh ") > -1);
        assertTrue(query.toString().indexOf(field + ":fur") > -1);

    }

    @Test
    public void testImpactOfStemming() throws ParseException {
        AbstractBookAnalyzer myAnalyzer;
        QueryParser parser;
        myAnalyzer = new EnglishLuceneAnalyzer();
        myAnalyzer.setDoStemming(false);
        parser = new QueryParser(Version.LUCENE_29, field, myAnalyzer);
        String testInput = "Surely will every man walketh";

        Query query = parser.parse(testInput);
        assertTrue(query.toString().indexOf(field + ":surely") > -1);
        assertTrue(query.toString().indexOf(field + ":every") > -1);


        Random rand = TestUtil.newRandom();
        int wordCount=80;
        StringBuilder randStr = new StringBuilder();
        for(int i = 0; i < wordCount ; i++)
            randStr.append(TestUtil.randomSimpleString(rand, 10)).append(' ');

        query = parser.parse(randStr.toString());

        String outStr = query.toString();
        //assertTrue(outStr.indexOf(field + ":surely") > -1);
        //assertTrue(outStr.indexOf(field + ":every") > -1);
        //IndexSearcher searcher = newSearcher(ir);
    }



    protected static final String field = "content";

}
