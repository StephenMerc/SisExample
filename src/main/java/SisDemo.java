import org.apache.sis.geometry.DirectPosition2D;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.CommonCRS;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;

public class SisDemo {
    public static void main(String[] args) {
        try {

            //a point in California
            DirectPosition position = new DirectPosition2D(-122.406792,37.735299);

            CoordinateReferenceSystem from = CommonCRS.WGS84.normalizedGeographic();

            //Europe/Africa region EPSG coordinate system
            CoordinateReferenceSystem to = CRS.getAuthorityFactory("EPSG")
                    .createCoordinateReferenceSystem("32631");

            CoordinateOperation operation = CRS.findOperation(from, to, null);

            //throws for SIS version 1.0
            //returns a point with coordinates [NaN, NaN] for SIS version 1.1
            DirectPosition result = operation.getMathTransform().transform(position, null);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
