/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vector;

/**
 * Vector3d is a class dedicated to working with vectors of 3 components.
 * Includes various mathematical functions, such as dot products, cross
 * products, projections, and the angle between two vectors.
 *
 * @author Nick
 * @version 1.0
 */
public class Vector3d {

    /**
     * Vector's first component.
     */
    private double x;

    /**
     * Vector's second component.
     */
    private double y;

    /**
     * Vector's third component.
     */
    private double z;

    /**
     * Vector's length.
     */
    private double length;

    /**
     * Vector's default constructor, initializes all components to 0.
     */
    public Vector3d() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
        length = 0.0;
    }

    /**
     * Constructs and initializes a Vector3d from the array of length 3.
     *
     * @param v an array, must be of length 3 containing xyz in order
     */
    public Vector3d(double[] v) {
        try {
            x = v[0];
            y = v[1];
            z = v[2];
            length = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
    }

    /**
     * Constructs and initializes a Vector3d from the parameter of type
     * Vector3d.
     *
     * @param v a Vector3d containing the initializing xyz data
     */
    public Vector3d(Vector3d v) {
        x = v.getX();
        y = v.getY();
        z = v.getZ();
    }

    /**
     * Constructs and initializes a Vector3d from the specified x y z
     * coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        length = Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Checks is the specified vector is the zero vector <0, 0, 0>
     *
     * @param v the vector to be checked
     * @return if the vector is the zero vector
     */
    public static boolean isZero(Vector3d v) {
        return (v.getX() == 0 && v.getY() == 0 && v.getZ() == 0);
    }

    /**
     * Sets the value of this vector to the specified xyz coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        length = Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Sets the value of this vector from the array of length 3.
     *
     * @param v an array of length 3 containing xyz in order
     */
    public void set(double[] v) {
        try {
            x = v[0];
            y = v[1];
            z = v[2];
            length = Math.sqrt(x * x + y * y + z * z);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
    }

    /**
     * Constructs and initializes a Vector3d from the parameter of type
     * Vector3d.
     *
     * @param v a Vector3d containing the initializing xyz data
     */
    public void set(Vector3d v) {
        x = v.getX();
        y = v.getY();
        z = v.getZ();
        length = Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Returns the sum of two vectors.
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the sum of the vectors
     */
    public static Vector3d add(Vector3d v1, Vector3d v2) {
        return new Vector3d(v1.getX() + v2.getX(),
                            v1.getY() + v2.getY(),
                            v1.getZ() + v2.getZ());
    }
    
    /**
     * Returns the difference of two vectors.
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the difference of the vectors
     */
    public static Vector3d sub(Vector3d v1, Vector3d v2) {
        return add(v1, negate(v2));
    }

    /**
     * Negates the value of each component of the vector.
     * @param v the vector to be negated
     * @return the negated vector
     */
    public static Vector3d negate(Vector3d v) {
        return new Vector3d(-v.getX(), -v.getY(), -v.getZ());
    }

    /**
     * Returns the scalar multiplication of a vector.
     *
     * @param v the vector to be scaled
     * @param scalar the value to be multiplied by
     * @return the scaled vector
     */
    public static Vector3d mult(Vector3d v, double scalar) {
        return new Vector3d(v.getX()*scalar,
                            v.getY()*scalar,
                            v.getZ()*scalar);
    }
    
    /**
     * Returns a unit vector with the same direction as the vector.
     *
     * @param v the vector to be normalized
     * @return the normalized vector
     */
    public static Vector3d normalize(Vector3d v) {
        if (v.getLength() == 1) {
            return v;
        }
        return mult(v, 1/v.getLength());
    }

    /**
     * Returns the dot product of two vectors.
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the dot product of v1 and v2
     */
    public static double dot(Vector3d v1, Vector3d v2) {
        return  v1.getX()*v2.getX() + 
                v1.getY()*v2.getY() +
                v1.getZ()*v2.getZ();
    }

    /**
     * Returns the cross product of two vectors.
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the cross product of v1 and v2
     */
    public static Vector3d cross(Vector3d v1, Vector3d v2) {
        double iCo = v1.getY() * v2.getZ() - v1.getZ() * v2.getY();
        double jCo = v1.getZ() * v2.getX() - v1.getX() * v2.getZ();
        double kCo = v1.getX() * v2.getY() - v1.getY() * v2.getX();

        return new Vector3d(iCo, jCo, kCo);
    }

    /**
     * Returns the angle between two vectors.
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the angle, in radians, between v1 and v2
     */
    public static double angle(Vector3d v1, Vector3d v2) {

        // if either vector is the zero vector, return 0
        if (isZero(v1) || isZero(v2)) {
            return 0.0;
        }

        double ratio = dot(v1, v2) / (v1.getLength() * v2.getLength());

        // account for accuracy errors
        if (Math.abs(1 - ratio) <= 1e-14) {
            ratio = 1;
        }

        return Math.acos(ratio);
    }
    
    /**
     * Returns the scalar projection of v2 onto v1.
     * 
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the signed magnitude of the vector projection
     */
    public static double comp(Vector3d v1, Vector3d v2) {
        return (dot(v1, v2) / v1.getLength());
    }
    
    /**
     * Returns the vector projection of v2 onto v1.
     * 
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the vector projection of v2 onto v1
     */
    public static Vector3d proj(Vector3d v1, Vector3d v2) {
        double coeff = dot(v1, v2) / (v1.getLength() * v1.getLength());
        return mult(v1, coeff);
    }

    @Override
    public final String toString() {
        return "<" + getX() + ", " + getY() + ", " + getZ() + ">";
    }

    /**
     * @return the length of the vector
     */
    public double getLength() {
        return length;
    }

    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(double z) {
        this.z = z;
        length = Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
        length = Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
        length = Math.sqrt(x * x + y * y + z * z);
    }
}
