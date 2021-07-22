package torqueUI;

import com.perunlabs.jsolid.d3.Solid;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.perunlabs.jsolid.JSolid.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class JSolidTORQUE {
    public static void stlMake(String stlPathString,
                               double outer_diameter,
                               double outer_width,
                               double spoke_width,
                               double inner_diameter,
                               int number_of_spokes,
                               double thickness,
                               double precision
    ) {

        config().setCircleToPolygonPrecision(precision);

        double outer_radius = outer_diameter / 2;
        double inner_radius = inner_diameter / 2;
        double spoke_round_radius = outer_radius / 42;

        Solid outerRing = cylinder(outer_radius, thickness)
                .sub(cylinder((outer_radius - outer_width), thickness));


        Solid innerCyl = cylinder(inner_radius, thickness);

/*        double stokeX1 = sqrt(pow(inner_radius, 2) - pow((spoke_width/2), 2));
        //double stokeX2 = sqrt(pow((outer_radius-outer_width), 2) - pow((spoke_width/2), 2));
        double stokeX2 = sqrt(pow((outer_radius-outer_width/1.1), 2) - pow((spoke_width/2), 2));
        double spoke_length = stokeX2 - stokeX1;

        Solid stokes = cuboid(spoke_length, spoke_width, thickness*2)
                //.cornerR(x(), spoke_round_radius)
                .moveBy(vx(stokeX1+(spoke_length/2)))
                .clone(number_of_spokes, (i, s) -> s.rotate(z(), degrees(i * (360/number_of_spokes))));*/

        double spoke_length = sqrt(pow((outer_radius-outer_width/1.1), 2) - pow((spoke_width/2), 2));

        Solid stokes = cuboid(spoke_length, spoke_width, thickness*2)
                //.cornerR(x(), spoke_round_radius)
                .moveBy(vx((spoke_length/2)))
                .clone(number_of_spokes, (i, s) -> s.rotate(z(), degrees(i * (360.0/(Double.valueOf(number_of_spokes))))));


        Solid torque = outerRing
                .add(innerCyl)
                .add(stokes);

        Path stlPath = Paths.get(stlPathString);
        try {
            com.perunlabs.jsolid.d3.Stl.toStl(torque, stlPath);
        }
        catch (Exception e) {
            System.out.println("cannot generate stl");
        }

    }
 }
