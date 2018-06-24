import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.Map;

public class HeatmapGenerator {
    private int width, heigth;

    /**
     * Simple Constructor that catches a few Exceptions.
     * @param width width of heatmap image.
     * @param heigth heigth of heatmap image.
     */
    HeatmapGenerator(int width,int heigth) {
        if(width < 0){
            throw new IllegalArgumentException("Heatmap cannot have negative width.");
        } else {
            this.width = width;
        }

        if(heigth < 0){
            throw new IllegalArgumentException("Heatmap cannot have negative heigth.");
        } else {
            this.heigth = heigth;
        }
    }

    /**
     * Creates a BufferedImage, fills it with blank pixels.
     * Then proceeds to fill it with the given data.
     * @param fileName should create a .png Image with the specified name.
     */
    public void generate(Map<Coordinate,Double> heatmap,String fileName) {
        BufferedImage bufferedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
        int warmth;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                bufferedImage.setRGB(i, j, 0x00000000);
            }
        }

        //folgende Zeilen sind beispielsweise eingesetzt und muessen noch auf die komplette Map angewandt werden.
        warmth = calcColor(heatmap.get(new Coordinate(0,0)));

        bufferedImage.setRGB(0,0,warmth);
    }



    /**
     * Calculates the "warmth" of a certain pixel.
     * @param intensity a value between 0 and 1
     * @return returns an Int value with the calculated warmth.
     */
    private int calcColor(double intensity){
        Double warmth;
        double red,blue;
        double scaled = 510*intensity;
        scaled = Math.floor(scaled);

        if (scaled > 255) {
            red = 255;
            blue = 255 - (scaled - 255);
        } else {
            blue = 255;
            red = scaled;
        }

        warmth = Math.pow(256,4) + Math.pow(red,3) - Math.pow(256,2) + blue - 1;

        return warmth.intValue();
    }
}
