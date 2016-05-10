package realization.prediction.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.*;

/**
 * A UnivariateInterpolator is used to find a univariate real-valued function f which for a given set of ordered pairs (xi,yi)
 * yields f(xi)=yi to the best accuracy possible.
 * <p>
 * The result is provided as an object implementing the UnivariateFunction interface.
 * It can therefore be evaluated at any point, including point not belonging to the original set.
 * <p>
 * <p>
 * Created by hdhamee on 4/25/16.
 */
public class InterpolationBasedPrediction {

    public static void main(String[] args) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        double x[] = {0.0, 1.0, 2.0};
        double y[] = {1.0, -1.0, 2.0};
        UnivariateInterpolator splineInterpolator = new SplineInterpolator();
        UnivariateFunction function = splineInterpolator.interpolate(x, y);
        double interpolationX = 0.5;
        double interpolatedY = function.value(interpolationX);
        System.out.println("SplineInterpolator:  f(" + interpolationX + ") = " + interpolatedY);


        /////////////////////////////////////////////////////////////////////////////////////////////
        HermiteInterpolator hermiteInterpolator = new HermiteInterpolator();
        // at x = 0, we provide both value and first derivative
        hermiteInterpolator.addSamplePoint(0.0, new double[]{1.0}, new double[]{2.0});
        // at x = 1, we provide only function value
        hermiteInterpolator.addSamplePoint(1.0, new double[]{4.0});
        // at x = 2, we provide both value and first derivative
        hermiteInterpolator.addSamplePoint(2.0, new double[]{5.0}, new double[]{2.0});
        // should print "value at x = 0.5: 2.5625"
        System.out.println("HermiteInterpolator: f(0.5)=" + hermiteInterpolator.value(8)[0]);
        // should print "interpolation polynomial: 1 + 2 x + 4 x^2 - 4 x^3 + x^4"
        System.out.println("HermiteInterpolator Polynomial: " + hermiteInterpolator.getPolynomials()[0]);


        ////////////////////////////////////////////////////////////////////////////////////////////
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        UnivariateFunction univariateFunction = linearInterpolator.interpolate(x, y);
        double polateX = 0.5;
        double polateY = univariateFunction.value(polateX);
        System.out.println("LinearInterpolator: f(" + polateX + ") = " + polateY);


        ////////////////////////////////////////////////////////////////////////////////////////////////
        DividedDifferenceInterpolator dividedDifferenceInterpolator = new DividedDifferenceInterpolator();
        double polatedX = 0.5;
        double polatedY = dividedDifferenceInterpolator.interpolate(x, y).value(polatedX);
        System.out.println("DividedDifferenceInterpolator: f(" + polatedX + ") = " + polatedY);

    }
}
