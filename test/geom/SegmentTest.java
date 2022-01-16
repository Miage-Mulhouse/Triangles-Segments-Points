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
import caseine.reflect.ReflectUtilities;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
public class SegmentTest {
    private static Random R = new Random();
    
   

    @Test
    @Grade(2)
    public void testConstruteurSegmentPointPoint() {
        try {
            Class<?> ClassePoint = Class.forName("geom.Point");
            Constructor<?> kp = ClassePoint.getDeclaredConstructor(double.class, double.class);
            Class<?> ClasseSegment = Class.forName("geom.Segment");
            Constructor<?> ks = ClasseSegment.getDeclaredConstructor(ClassePoint, ClassePoint);
            for(int i = 0; i < 10; ++i) {
                double x1 = R.nextDouble();
                double y1 = R.nextDouble();
                Object p1 = kp.newInstance(x1, y1);
                double x2 = R.nextDouble();
                double y2 = R.nextDouble();
                Object p2 = kp.newInstance(x2, y2);
                
                Object segment = ks.newInstance(p1, p2);
                assertTrue("Problème à la construction d'un segment : attribut p1", 
                        ReflectUtilities.equals(p1, ReflectUtilities.getAttribut(ClasseSegment, segment, "p1"))
                );
                assertTrue("Problème à la construction d'un segment : attribut p2", 
                        ReflectUtilities.equals(p2, ReflectUtilities.getAttribut(ClasseSegment, segment, "p2"))
                );
            }
        } catch (ClassNotFoundException ex) {
            fail("Classe Point ou Segment absente");
        } catch (NoSuchMethodException ex) {
            fail("Constructeur Point(double, double) ou Segment(Point, Point) absent");
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Constructeur Point(double, double) ou Segment(Point, Point) inaccessible");
        } catch (NoSuchFieldException ex) {
            fail("Attributs de Point ou de Segment manquant ou inaccessible");
        }
    }
}
