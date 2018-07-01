import java.util.Random;

public class ArtistLocation {
    private double latitude, longitude;

    //TODO: Replace the Random generator with data from dataset.
    private Random gen = new Random();

    ArtistLocation(String id){
        //TODO: Use id to extract coordinates

        double latitude,longitude;

        latitude = gen.nextDouble()*360 - 180;
        longitude = gen.nextDouble()*180 - 90;

        this.latitude = (latitude + 180);
        this.longitude = (longitude + 90);
    }


    /**
     * Helper function.
     * @param unscaled latitude or longitude.
     * @return scaled latitude or longitude.
     */
    private double scale4th(double unscaled) {
        double floor = Math.floor(unscaled);
        double rest = unscaled - floor;

        if (rest < 0.125) {
            rest = 0;
        } else {
            if (rest >= 0.875) {
                rest = 1;
            } else {
                if (rest < 0.325) {
                    rest = 0.25;
                } else {
                    if (rest >= 0.675) {
                        rest = 0.75;
                    } else {
                        rest = 0.5;
                    }
                }
            }
        }

        return 4*(floor + rest);
    }

    /**
     * Calculates latitude for a picture that is 1440 pixels wide.
     * @return scaled latitude.
     */
    public double getLatitude() {
        latitude = scale4th(latitude);

        return latitude;
    }

    /**
     * Calculates longitude for a picture that is 720 pixels high.
     * @return scaled longitude.
     */
    public double getLongitude() {
        longitude = scale4th(longitude);

        return longitude;
    }

    public static void main(String []args){
        ArtistLocation test = new ArtistLocation("0");

        System.out.println(test.getLatitude() + "," + test.getLongitude());
    }
}
