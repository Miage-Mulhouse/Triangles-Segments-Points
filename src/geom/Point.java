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
 * Cet exercice s'inspire de celui des triangles plutôt classes de Céline Fouard
 * <Celine.Fouard@univ-grenoble-alpes.fr>.
 *
 * Il a été instrumentalisé par Yvan Maillot <yvan.maillot@uha.fr> à l'aide du
 * plugin de conception Java de sorte à produire un VPL complet.
 *
 * Il est fourni ici en guise d'exemple d'emploi de nombreuses annotations dans
 * les trois fichiers Point.java, Segment.java et Triangle.java ainsi que les
 * fichiers de tests unitaires PointTest.java, SegmentTest.java et
 * TriangleTest.java.
 * 
 * Ils suffisent à définir totalement un VPL très complet sur la
 * plateforme Caséine (https://moodle.caseine.org/) à partir de votre machine
 * locale.
 *
 */
import caseine.tags.*;

@RelativeEvaluation
public class Point {

    @ToDo("1.1. Déclarer x et y, les coordonnées cartésiennes de ce point")
    @ToCheck(value = "x ou y : déclaration incorrecte ou manquante", priority = 1, grade = 1)
    @GetterToCheck(priority = 3, grade = 1)
    @SetterToCheck(priority = 4, grade = 1)
    private double x, y;

    @ToDo("1.2. Écrire le constructeur aux coordonnées cartésiennes")
    @ToCheck(priority = 2, grade = 1)
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @ToDo("1.3. Écrire un getter pour x")
    public double getX() {
        return x;
    }

    @ToDo("1.4. Écrire un getter pour y")
    public double getY() {
        return y;
    }

    @ToDo("1.5. Écrire un setter pour x")
    public void setX(double x) {
        this.x = x;
    }

    @ToDo("1.6. Écrire un setter pour y")
    public void setY(double y) {
        this.y = y;
    }

    @ToDo(value = "1.7. public toString() qui retourne une description de ce point sous la forme : (1.5; 3.0)")
    @ToCheck(priority = 5, grade = 1)
    @ToCompare(
            priority = 10,
            grade = 2,
            testSetsMethodName = "geom.TriangleTest.testSetsToString",
            comparatorMethodName = "geom.TriangleTest.cmpToString",
            requiersUnitTestsBefore = "5"
    )
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
