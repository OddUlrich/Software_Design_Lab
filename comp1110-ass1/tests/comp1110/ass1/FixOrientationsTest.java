package comp1110.ass1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertTrue;

public class FixOrientationsTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(30);

    @Test
    public void testEmpty() {
        String out = IQStars.fixOrientations("");
        assertTrue("Expected an empty string, but got \"" + out + "\"", out.equals(""));
    }

    @Test
    public void testStandardOrientations() {
        String out = IQStars.fixOrientations("ABACSDETCFWDGOD");
        assertTrue("Expected \"ABACSDETCFWDGOD\", but got \"" + out + "\"", out.equals("ABACSDETCFWDGOD"));
        out = IQStars.fixOrientations("ECAFNAGGC");
        assertTrue("Expected \"ECAFNAGGC\", but got \"" + out + "\"", out.equals("ECAFNAGGC"));
    }

    @Test
    public void testJavadocExamples() {
        String out = IQStars.fixOrientations("DQD");
        assertTrue("Expected \"DIA\", but got \"" + out + "\"", out.equals("DIA"));
        out = IQStars.fixOrientations("ACDBGACEF");
        assertTrue("Expected \"AAABGACEF\", but got \"" + out + "\"", out.equals("AAABGACEF"));
        out = IQStars.fixOrientations("BDCFGHDQD");
        assertTrue("Expected \"BDCFGHDIA\", but got \"" + out + "\"", out.equals("BDCFGHDIA"));
        out = IQStars.fixOrientations("HACDREICD");
        assertTrue("Expected \"HACDEBICD\", but got \"" + out + "\"", out.equals("HACDEBICD"));
    }

    @Test
    public void testAFlipped() {
        String out = IQStars.fixOrientations("ADDCSDETCFWDGOD");
        assertTrue("Expected \"ABACSDETCFWDGOD\", but got \"" + out + "\"", out.equals("ABACSDETCFWDGOD"));
        out = IQStars.fixOrientations("AQEDDAGVF");
        assertTrue("Expected \"ACBDDAGVF\", but got \"" + out + "\"", out.equals("ACBDDAGVF"));
        out = IQStars.fixOrientations("AWFBPCCDADBAFGCGOD");
        assertTrue("Expected \"AKCBPCCDADBAFGCGOD\", but got \"" + out + "\"", out.equals("AKCBPCCDADBAFGCGOD"));
    }

    @Test
    public void testDFlipped() {
        String out = IQStars.fixOrientations("AVADUEEAAFGCGEC");
        assertTrue("Expected \"AVADHBEAAFGCGEC\", but got \"" + out + "\"", out.equals("AVADHBEAAFGCGEC"));
        out = IQStars.fixOrientations("AEABCADRFFBCGIB");
        assertTrue("Expected \"AEABCADMCFBCGIB\", but got \"" + out + "\"", out.equals("AEABCADMCFBCGIB"));
        out = IQStars.fixOrientations("ACBDLDGVF");
        assertTrue("Expected \"ACBDDAGVF\", but got \"" + out + "\"", out.equals("ACBDDAGVF"));
    }
}
