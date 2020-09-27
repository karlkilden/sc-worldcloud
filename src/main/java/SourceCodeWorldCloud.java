import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.image.AngleGenerator;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SourceCodeWorldCloud {


    private static String sourceCodeDirectory;

    public static void main(String[] args) throws IOException {

        sourceCodeDirectory = "";
        final MultiAnalyzer frequencyAnalyzer = new MultiAnalyzer();
        frequencyAnalyzer.setStopWords(List.of("abstract",
                "boolean",
                "break",
                "byte",
                "byte",
                "byte",
                "case",
                "catch",
                "char",
                "class",
                "continue",
                "default",
                "do",
                "double",
                "else",
                "enum",
                "extends",
                "final",
                "finally",
                "float",
                "for",
                "if",
                "implements",
                "import",
                "instanceof",
                "int",
                "interface",
                "long",
                "native",
                "new",
                "package",
                "private",
                "protected",
                "public",
                "return",
                "short",
                "static",
                "super",
                "switch",
                "synchronized",
                "this",
                "throw",
                "throws",
                "transient",
                "try",
                "void",
                "volatile",
                "while"));
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.loadAll(sourceCodeDirectory);
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setKumoFont(new KumoFont(new Font("Arial", Font.PLAIN, 10)));
        wordCloud.setPadding(2);
        wordCloud.setAngleGenerator(new AngleGenerator(0));
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("wordcloud.png");
    }
}
