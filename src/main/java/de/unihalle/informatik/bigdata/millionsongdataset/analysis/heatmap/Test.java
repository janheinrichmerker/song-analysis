package de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String []args) throws IOException {
        ArtistLocationTranslator altrans = new ArtistLocationTranslator(new File("C:\\Users\\Papa\\Desktop\\BigData-Project Dima\\HeatMapGenerator\\danceabilityCoordinates"));
        HeatMapGenerator heatmapGenerator = new HeatMapGenerator(1440, 720);

        heatmapGenerator.generate(altrans.translate(),"test_new");
    }
}
