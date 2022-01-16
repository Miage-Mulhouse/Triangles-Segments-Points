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


/**
 * 
 * Les annotations dans ces trois fichiers Point.java, Segment.java et
 * Triangle.java ainsi que les fichiers de tests unitaires PointTest.java,
 * SegmentTest.java et TriangleTest.java suffisent à définir totalement un VPL
 * très complet sur la plateforme Caséine (https://moodle.caseine.org/) à partir
 * de votre machine locale.
 * 
 */
import caseine.tags.*;

public class Segment {
    @ToDo("2.1. Déclarer les attributs p1 et p2, les extrémités de ce segment")
    @ToCheck(priority = 200, grade = 2)
    @GetterToCheck(priority = 220, grade = 2)
    @SetterToCheck(priority = 230, grade = 2)
    private Point p1, p2;

    @ToDo("2.2. Écrire le constructeur attendant les extrémités de ce segment")
    @ToCheck(priority = 210, grade = 1)
    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @ToDo("2.3. Écrire un getter pour chaque extrémité")
    public Point getP1() {
        return p1;
    }

    @ToDo
    public void setP1(Point p1) {
        this.p1 = p1;
    }

    @ToDo("2.4. Écrire un setter pour chaque extrémité")
    public Point getP2() {
        return p2;
    }

    @ToDo
    public void setP2(Point p2) {
        this.p2 = p2;
    }
    
    @ToDo("2.5. Écrire getLongueur() qui retourne la longeur de ce segment")
    @ToCheck(priority =240, grade = 1)
    @ToCompare(priority =250, grade = 3)
    public double getLongueur() {
        return Math.sqrt((p2.getX()-p1.getX())*(p2.getX()-p1.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY()));
    }
    
    @ToDo("2.6. Écrire toString() qui retourne une description de ce segment sous la forme : [(1.0; 1.0) ; (1.5; 3.0)]")
    @ToCheck(priority =260, grade = 1)
    @ToCompare(
            priority =270, 
            grade = 4, 
            testSetsMethodName = "geom.TriangleTest.testSetsToStringSegment", 
            comparatorMethodName = "geom.TriangleTest.cmpToString", 
            requiersUnitTestsBefore = "260"
    )
    @Override
    public String toString() {
        return "["+p1+" ; "+p2+"]";
    }
}
