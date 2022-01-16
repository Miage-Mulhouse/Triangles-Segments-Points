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

public class Triangle {

    @ToDo("3.01. Les trois segments du triangle : s1, s2, s3")
    @ToCheck(priority = 300, grade = 2)
    private Segment s1, s2, s3;

    @ToDo("3.02. Écrire le constructeur avec ses trois sommets")
    @ToCheck(value = "Bien relire l'énoncé du constructeur de Triangle", priority = 310, grade = 5)
    public Triangle(Point p1, Point p2, Point p3) {
        this.s1 = new Segment(p1, p2);
        this.s2 = new Segment(p2, p3);
        this.s3 = new Segment(p3, p1);
    }

    @ToDo("3.03. getP1() qui retourne le premier sommet de ce triangle")
    @ToCheck(priority = 320)
    @ToCompare(priority = 321, grade = 2, 
            requiersUnitTestsBefore = "320", 
            testSetsMethodName = "geom.TriangleTest.testSetsTrianglesMethodSansParam")
    public Point getP1() {
        return s1.getP1();
    }

    @ToDo("3.04. getP2() qui retourne le deuxième sommet de ce triangle")
    @ToCheck(priority = 322, grade = 1)
    @ToCompare(priority = 323, grade = 2, 
            requiersUnitTestsBefore = "322", 
            testSetsMethodName = "geom.TriangleTest.testSetsTrianglesMethodSansParam")
    public Point getP2() {
        return s2.getP1();
    }

    @ToDo("3.05. getP3() qui retourne le troisième sommet de ce triangle")
    @ToCheck(priority = 324)
    @ToCompare(priority = 325, grade = 2, 
            requiersUnitTestsBefore = "324", 
            testSetsMethodName = "geom.TriangleTest.testSetsTrianglesMethodSansParam")
    public Point getP3() {
        return s3.getP1();
    }

    @ToDo("3.06. setP1() qui met à jour le premier sommet de ce triangle")
    @ToCheck(priority = 330)
    @ToCompare(priority = 331, grade = 4, 
            requiersUnitTestsBefore = "330", 
            testSetsMethodName = "geom.TriangleTest.testSetsTrianglesMethodUnParam",
            comparatorMethodName = "geom.TriangleTest.cmpSetters")
    public void setP1(Point p1) {
        this.s1 = new Segment(p1, s2.getP1());
        this.s3 = new Segment(s3.getP1(), p1);
    }

    @ToDo("3.07. setP2() qui met à jour le deuxième sommet de ce triangle")
    @ToCheck(priority = 332)
    @ToCompare(priority = 333, grade = 4, 
            requiersUnitTestsBefore = "332", 
            testSetsMethodName = "geom.TriangleTest.testSetsTrianglesMethodUnParam",
            comparatorMethodName = "geom.TriangleTest.cmpSetters")
    public void setP2(Point p2) {
        this.s1 = new Segment(s1.getP1(), p2);
        this.s2 = new Segment(p2, s3.getP1());
    }

    @ToDo("3.08. setP3() qui met à jour le troisième sommet de ce triangle")
    @ToCheck(priority = 334)
    @ToCompare(priority = 335, grade = 4, 
            requiersUnitTestsBefore = "334", 
            testSetsMethodName = "geom.TriangleTest.testSetsTrianglesMethodUnParam",
            comparatorMethodName = "geom.TriangleTest.cmpSetters")
    public void setP3(Point p3) {
        this.s2 = new Segment(s2.getP1(), p3);
        this.s3 = new Segment(p3, s1.getP1());
    }

    @ToDo("3.09. Retourner le périmètre de ce triangle")
    @ToCheck(priority = 340, grade = 1)
    @ToCompare(priority = 350, grade = 3)
    public double getPerimetre() {
        return s1.getLongueur() + s2.getLongueur() + s3.getLongueur();
    }

    @ToDo("3.10. Retourner le barycentre de ce triangle")
    @ToCheck(priority = 360, grade = 1)
    @ToCompare(priority = 370, grade = 3)
    public Point getBaryCentre() {
        Point a = s1.getP1();
        Point b = s2.getP1();
        Point c = s3.getP1();

        return new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
    }

    @ToCheck(priority = 380, grade = 1)
    @ToDo("3.11. Retourner la surface de ce triangle")
    @ToCompare(priority = 390, grade = 4)
    public double getSurface() {
        double a = s1.getLongueur();
        double b = s2.getLongueur();
        double c = s3.getLongueur();
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @ToCheck(priority = 400, grade = 1)
    @ToDo("3.12. La méthode toString() qui retourne une description de ce triangle sous la forme : <[(1.0; 1.0) ; (1.5; 3.0)] ;[(2.0; 1.3) ; (2.5; 3.7)] ;[(8.4; 1.9) ; (3.8; 4.7)] >")
    @ToCompare(
            priority = 500, 
            grade = 2, 
            testSetsMethodName = "geom.TriangleTest.testSetsToStringTriangle", 
            comparatorMethodName = "geom.TriangleTest.cmpToString", 
            requiersUnitTestsBefore = "400"
    )
    @Override
    public String toString() {
        return "<" + s1 + " ; " + s2 + " ; " + s3 + ">";
    }
}
