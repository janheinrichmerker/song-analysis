import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class HeatmapGenerator {
    private int width, heigth;

    /**
     * Simple Constructor that catches a few Exceptions.
     * @param width width of heatmap image.
     * @param height height of heatmap image.
     */
    HeatmapGenerator(int width,int height) {
        if(width < 0){
            throw new IllegalArgumentException("Heatmap cannot have negative width.");
        } else {
            this.width = width;
        }

        if(height < 0){
            throw new IllegalArgumentException("Heatmap cannot have negative height.");
        } else {
            this.heigth = height;
        }
    }

    //some test stuff
    public static void main(String []args){
        //for testing purposes
        HashMap<Coordinate,Double> hashMap = new HashMap<>();
        Random numGen = new Random();
        HeatmapGenerator mapGen = new HeatmapGenerator(1440,720);

        for (int i=0;i<mapGen.getWidth();i++) {
            for (int j=0;j<mapGen.getHeigth();j++) {
                if (numGen.nextBoolean()) {
                    hashMap.put(new Coordinate(i, j), numGen.nextDouble());
                }
            }
        }

        long startTime,finalTime;
        double seconds;

        startTime = System.currentTimeMillis();
        mapGen.generate(hashMap,"test");
        finalTime = System.currentTimeMillis() - startTime;


        seconds = finalTime;
        seconds /= 1000;

        System.out.println("Elapsed time: " + seconds + " sec");
    }

    /**
     * Creates a BufferedImage, fills it with blank pixels.
     * Then proceeds to fill it with the given HashMap.
     * If a Key Coordinate does not exist in heatmap, it stays blank.
     * @param heatmap the specified heatmap should be provided in the Form of a HashMap. Currently only works if Coordinates are natural Numbers.
     * @param fileName should create a .png Image with the specified name.
     */
    public void generate(HashMap<Coordinate,Double> heatmap,String fileName) {
        BufferedImage bufferedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
        BufferedImage background = new BufferedImage(width,heigth,BufferedImage.TYPE_INT_ARGB);
        File outputFile;
        Double intensity;

        /**
         * First load the background image as a BufferedImage and then copy pixels onto bufferedImage.
         */
        try {
            background = ImageIO.read(new File("C:\\Users\\Papa\\Desktop\\BigData-Project Dima\\HeatmapGenerator\\map-flat-transparent-scaled-1440-720.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i<width;i++){
            for (int j=0;j<heigth;j++) {
                bufferedImage.setRGB(i,j,background.getRGB(i,j));
            }
        }

        for (int i=0;i<width;i++){
            for (int j=0;j<heigth;j++) {
                try {
                    intensity = heatmap.get(new Coordinate(i, j));
                    bufferedImage.setRGB(i, j, calcColor(intensity));
                } catch (NullPointerException n) {

                }
            }
        }

        outputFile = new File(fileName + ".png");
        try {
            ImageIO.write(bufferedImage,"png",outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        scaled = blue * 0x00000001;
        scaled = scaled + (red * 0x00010000);
        scaled = scaled + 0xFF000000;

        warmth = /*((Math.pow(16,8) - Math.pow(16,6)) + (red * (Math.pow(16,4)) - Math.pow(16,4)) + (blue - 1))*/ scaled;

        return warmth.intValue();
    }

    /**
     * Needed getter Methods.
     */
    public int getWidth() {
        return width;
    }
    public int getHeigth() {
        return heigth;
    }
}
