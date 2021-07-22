package torqueUI;

import com.perunlabs.jsolid.d3.Solid;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.perunlabs.jsolid.JSolid.*;

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

        double outer_radius = outer_diameter / 2;
        double inner_radius = inner_diameter / 2;
        double spoke_length = outer_radius - outer_width - inner_radius;
        double spoke_round_radius = outer_radius / 42;

        config().setCircleToPolygonPrecision(precision);

        Solid outerRing = cylinder(outer_radius, thickness)
                .sub(cylinder((outer_radius - outer_width), thickness));


        Solid innerCyl = cylinder(inner_radius, thickness);

        Solid stoke1 = cuboid(spoke_length, spoke_width, thickness*2)
                .cornerR(x(), spoke_round_radius)
                .moveBy(vx(inner_radius+(spoke_length/2)));

        Solid torque = outerRing
                .add(innerCyl)
                .add(stoke1);

        Path stlPath = Paths.get(stlPathString);
        try {
            com.perunlabs.jsolid.d3.Stl.toStl(torque, stlPath);
        }
        catch (Exception e) {
            System.out.println("cannot generate stl");
        }

    }
 }
