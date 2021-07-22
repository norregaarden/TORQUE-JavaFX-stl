package sample;

import com.perunlabs.jsolid.d3.Solid;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.perunlabs.jsolid.JSolid.*;

public class JSolidTORQUE {
    public static void stlMake(String stlPathString) {
        double outer_diameter = 200.0;
        double outer_width = 15.0;
        double spoke_width = 10.0;
        double inner_diameter = 20.0;
        int number_of_spokes = 5;
        double thickness = 33.0;

        //Path path = Paths.get("/Users/norregaarden/Documents/GitHub/torque-java-stl/hej.stl");
        //Path path = Paths.get("/Users/norregaarden/Documents/GitHub/torque-java-stl/hej.stl");
        double precision = 0.01;

        double outer_radius = outer_diameter / 2;
        double inner_radius = inner_diameter / 2;
        double spoke_length = outer_radius - outer_width - inner_radius;
        double spoke_round_radius = outer_radius / 42;

        //System.out.println("Hello, World!");
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
