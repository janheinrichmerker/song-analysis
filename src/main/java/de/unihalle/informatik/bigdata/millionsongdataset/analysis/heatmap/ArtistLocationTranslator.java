package de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ArtistLocationTranslator {
    File data;

    /**
     * Constructor which only initializes the needed file.
     * @param data File which contains the Artist ID's and danceability values.
     */
    ArtistLocationTranslator(File data) {
        this.data = data;
    }

    /**
     * Creates a HashMap via File input.
     * @return HashMap which is used to generate a Heatmap via HeatMapGenerator.
     * @throws IOException
     */
    public HashMap<Coordinate,Double> translate() throws IOException {
        HashMap<Coordinate,Double> hashMap = new HashMap<>();
        BufferedReader in = new BufferedReader(new FileReader(data));
        ArtistLocation location;

        String line,key;
        double value;

        line = in.readLine();
        while (line != null) {
            key = line.substring(0, line.indexOf(" "));
            value = Double.parseDouble(line.substring(line.indexOf(" "), line.length()));

            location = new ArtistLocation(key);
            hashMap.put(new Coordinate(location.getLatitude(),location.getLongitude()),value);
            line = in.readLine();
        }

        return hashMap;
    }
}
