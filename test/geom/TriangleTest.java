/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package geom;

import caseine.format.javajunit.Grade;
import caseine.publication.producers.ToCompareProducer;
import caseine.publication.producers.ToCompareProducer.InstanceAndParameters;
import caseine.publication.producers.ToCompareProducer.ResultForComparison;
import caseine.reflect.ReflectUtilities;
import static java.lang.Math.abs;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author yvan
 */
public class TriangleTest {

    private static Random R = new Random();

    private static Point randPoint() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        double x = R.nextDouble();
        double y = R.nextDouble();

        while (abs(x) < 1e-2) {
            x = R.nextDouble();
        }

        while (abs(y) < 1e-2) {
            y = R.nextDouble();
        }

        return ReflectUtilities.getTA(Point.class, double.class, x, double.class, y);
    }

    private static Segment randSegment() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

        return ReflectUtilities.get("geom.Segment", randPoint(), randPoint());
    }

    private static Triangle randTriangle() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

        return ReflectUtilities.get("geom.Triangle", randPoint(), randPoint(), randPoint());
    }

    public static List<ToCompareProducer.InstanceAndParameters> testSetsToString() {
        List<ToCompareProducer.InstanceAndParameters> list = new LinkedList<>();

        for (int i = 0; i < 100; ++i) {
            try {
                list.add(ToCompareProducer.InstanceAndParameters.newInstanceParams(randPoint()));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

            }
        }

        return list;
    }

    public static List<ToCompareProducer.InstanceAndParameters> testSetsToStringSegment() {
        List<ToCompareProducer.InstanceAndParameters> list = new LinkedList<>();

        for (int i = 0; i < 100; ++i) {
            try {

                list.add(ToCompareProducer.InstanceAndParameters.newInstanceParams(ReflectUtilities.get(Segment.class, randPoint(), randPoint())));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

            }
        }

        return list;
    }

    public static List<ToCompareProducer.InstanceAndParameters> testSetsToStringTriangle() {
        List<ToCompareProducer.InstanceAndParameters> list = new LinkedList<>();

        for (int i = 0; i < 100; ++i) {
            try {

                list.add(ToCompareProducer.InstanceAndParameters.newInstanceParams(ReflectUtilities.get(Triangle.class, randPoint(), randPoint(), randPoint())));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

            }
        }

        return list;
    }

    public static String cmpToString(ToCompareProducer.ResultForComparison rfc) {

        String refResult = ((String) rfc.getRefResult()).replaceAll(",", ".");
        String stdResult = ((String) rfc.getStudentResult()).replaceAll(",", ".");

        try (Scanner rin = new Scanner(refResult);
                Scanner sin = new Scanner(stdResult)) {
            rin.useDelimiter("[^0-9.]+");
            rin.useLocale(Locale.US);
            sin.useLocale(Locale.US);
            sin.useDelimiter("[^0-9.]+");
            ArrayList<Double> refTab = new ArrayList<>();
            ArrayList<Double> stdTab = new ArrayList<>();

            while (rin.hasNextDouble()) {
                refTab.add(rin.nextDouble());

            }
            while (sin.hasNextDouble()) {
                stdTab.add(sin.nextDouble());

            }

            if (refTab.size() != stdTab.size()) {
                return "toString() incorrect : attention à respecter le format demandé\n"
                        + "SURTOUT ne pas utiliser de virgule pour séparer les éléments\n"
                        + "attendu : " + rfc.getRefResult() + "\n"
                        + "obtenu  : " + rfc.getStudentResult() + "\n"
                        + "Bien lire l'énoncé et regarder l'exemple";
            }

            for (int i = 0; i < refTab.size(); ++i) {
                if (abs(refTab.get(i) - stdTab.get(i)) > 1e-2) {

                    return "toString() incorrect : attention à respecter le format demandé\n"
                            + "SURTOUT ne pas utiliser de virgule pour séparer les éléments\n"
                            + "attendu : " + rfc.getRefResult() + "\n"
                            + "obtenu  : " + rfc.getStudentResult() + "\n"
                            + "Bien lire l'énoncé et regarder l'exemple";
                }
            }

            return "OK";

        } catch (Exception ex) {
            return "Le retour de toString() semble non conforme : \n"
                    + "attendu : " + rfc.getRefResult() + "\n"
                    + "obtenu  : " + rfc.getStudentResult() + "\n"
                    + "Bien lire l'énoncé et regarder l'exemple";
        }

    }

    public static boolean equals(Class<?> cseg, Object s1, Object s2) {
        try {
            Object p1s1 = ReflectUtilities.getAttribut(cseg, s1, "p1");
            Object p1s2 = ReflectUtilities.getAttribut(cseg, s2, "p1");
            if (!ReflectUtilities.equals(p1s1, p1s2)) {
                return false;
            }
            Object p2s1 = ReflectUtilities.getAttribut(cseg, s1, "p2");
            Object p2s2 = ReflectUtilities.getAttribut(cseg, s2, "p2");
            if (!ReflectUtilities.equals(p2s1, p2s2)) {
                return false;
            }
            return true;
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            return false;
        }
    }

    @Test
    @Grade(2)
    public void testConstruteurTrianglePointPointPoint() {
        try {
            Class<?> ClassePoint = Class.forName("geom.Point");
            Constructor<?> kp = ClassePoint.getDeclaredConstructor(double.class, double.class);
            Class<?> ClasseSegment = Class.forName("geom.Segment");
            Constructor<?> ks = ClasseSegment.getDeclaredConstructor(ClassePoint, ClassePoint);
            Class<?> ClasseTriangle = Class.forName("geom.Triangle");
            Constructor<?> kt = ClasseTriangle.getDeclaredConstructor(ClassePoint, ClassePoint, ClassePoint);
            for (int i = 0; i < 10; ++i) {
                double x1 = R.nextDouble();
                double y1 = R.nextDouble();
                Object p1 = kp.newInstance(x1, y1);
                double x2 = R.nextDouble();
                double y2 = R.nextDouble();
                Object p2 = kp.newInstance(x2, y2);
                double x3 = R.nextDouble();
                double y3 = R.nextDouble();
                Object p3 = kp.newInstance(x3, y3);

                Object s1 = ks.newInstance(p1, p2);
                Object s2 = ks.newInstance(p2, p3);
                Object s3 = ks.newInstance(p3, p1);

                Object triangle = kt.newInstance(p1, p2, p3);
                assertTrue("Problème à la construction d'un triangle : attribut s1 \n" + s1 + "\n" + ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s1"),
                        equals(ClasseSegment, s1, ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s1"))
                );
                assertTrue("Problème à la construction d'un triangle : attribut s2 \n" + s2 + "\n" + ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s2"),
                        equals(ClasseSegment, s2, ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s2"))
                );
                assertTrue("Problème à la construction d'un triangle : attribut s3 \n" + s3 + "\n" + ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s3"),
                        equals(ClasseSegment, s3, ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s3"))
                );
                /*assertTrue("Problème à la construction d'un segment : attribut s2",
                        ReflectUtilities.equals(s2, ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s2"))
                );
                assertTrue("Problème à la construction d'un segment : attribut s3",
                        ReflectUtilities.equals(s3, ReflectUtilities.getAttribut(ClasseTriangle, triangle, "s3"))
                );*/
            }
        } catch (ClassNotFoundException ex) {
            fail("Classe Point ou Segment ou Triangle absente");
        } catch (NoSuchMethodException ex) {
            fail("Constructeur Point(double, double) ou Segment(Point, Point) ou Triangle(Point, Point, Point) absent");
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Constructeur Point(double, double) ou Segment(Point, Point) ou Triangle(Point, Point, Point) inaccessible");
        } catch (NoSuchFieldException ex) {
            fail("Attributs de Point ou de Segment ou de Triangle manquant ou inaccessible");
        }
    }

    public static List<InstanceAndParameters> testSetsTrianglesMethodSansParam() {
        try {
            List<InstanceAndParameters> list = new LinkedList<>();

            for (int i = 0; i < 10; ++i) {
                list.add(InstanceAndParameters.newInstanceParams(randTriangle()));
            }

            return list;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            return null;
        }
    }

    public static List<InstanceAndParameters> testSetsTrianglesMethodUnParam() {
        try {
            List<InstanceAndParameters> list = new LinkedList<>();

            for (int i = 0; i < 10; ++i) {
                list.add(InstanceAndParameters.newInstanceParams(randTriangle(), randPoint()));
            }

            return list;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            return null;
        }
    }

    public static String cmpSetters(ResultForComparison rfc) {
        try {
            if (ReflectUtilities.equals(rfc.getRefInstance(), rfc.getStudentInstance())) {
                return "OK";
            } else {
                return "NOK";
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            return "NOK";
        }
    }
}
