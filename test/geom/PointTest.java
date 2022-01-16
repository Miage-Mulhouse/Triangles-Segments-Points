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
public class PointTest {
    private static Random R = new Random();

    @Test
    @Grade(2)
    public void testConstruteurPointDoubleDouble() {
        try {
            Class<?> ClassePoint = Class.forName("geom.Point");
            Constructor<?> k = ClassePoint.getDeclaredConstructor(double.class, double.class);
            for(int i = 0; i < 10; ++i) {
                double x = R.nextDouble();
                double y = R.nextDouble();
                Object point = k.newInstance(x, y);
                assertTrue("Problème à la construction d'un point : attribut x", (double)ReflectUtilities.getAttribut(ClassePoint, point, "x") == x);
                assertTrue("Problème à la construction d'un point : attribut y", (double)ReflectUtilities.getAttribut(ClassePoint, point, "y") == y);
            }
        } catch (ClassNotFoundException ex) {
            fail("Classe Point absente");
        } catch (NoSuchMethodException ex) {
            fail("Constructeur Point(double, double) absent");
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Constructeur Point(double, double) inaccessible");
        } catch (NoSuchFieldException ex) {
            fail("Classe Point : attribut x ou y manquant ou inaccessible");
        }
    }
    
}
