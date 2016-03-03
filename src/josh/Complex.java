package josh;

/**
 * Complex class
 * @author  Richard Kick
 * @version May, 2004
 */

public class Complex
{
  // Instance Fields
  private double myReal;
  private double myImaginary;

  /**
   * Default Complex Constructor that creates the complex number 0 + 0i
   */
  public Complex( )
  {
    myReal = 0.0;
    myImaginary = 0.0;
  }

  /**
   * Complex Constructor that creates the complex number whose value equals c
   */
  public Complex(Complex c )
  {
    myReal = c.real();
    myImaginary = c.imaginary();
  }

  /**
   * Complex Constructor that creates the complex number real + 0i
   * @param real   real portion of the complex number
   */
  public Complex(double real )
  {
    myReal = real;
    myImaginary = 0.0;
  }

  /**
   * Complex Constructor that creates the complex number real + 0i
   * @param real   real portion of the complex number
   * @param imaginary   imaginary portion of the complex number
   */
  public Complex(double real, double imaginary )
  {
    myReal = real;
    myImaginary = imaginary;
  }

  /**
   * Calculates and returns the sum of the current Complex object and cmp
   * @param cmp Complex object that is added to the current Complex object
   */
  public Complex add(Complex cmp)
  {
    Complex sum = new Complex( real() + cmp.real(),  imaginary() + cmp.imaginary() );
    return sum;
  }

  /**
   * Calculates and returns the difference of the current Complex object and cmp
   * @param cmp Complex object that is subtracted from the current Complex object
   */
  public Complex subtract(Complex cmp)
  {
    Complex diff = new Complex( real() - cmp.real(),  imaginary() - cmp.imaginary() );
    return diff;
  }

  /**
   * Calculates and returns the product of the current Complex object and cmp
   * @param cmp Complex object that is multiplied by the current Complex object
   */
  public Complex multiply(Complex cmp)
  {
    Complex prod = new Complex( real() * cmp.real() - imaginary() * cmp.imaginary(),
                                imaginary() * cmp.real() + real() * cmp.imaginary());
    return prod;
  }

  /**
   * Calculates and returns the quotient of the current Complex object and cmp
   * cmp must be non-zero
   * @param cmp Complex object that is divided into the current Complex object
   */
  public Complex divide(Complex cmp)
  {
    double denom = cmp.real() * cmp.real() + cmp.imaginary() * cmp.imaginary();
    Complex quot = new Complex( ( real() * cmp.real() + imaginary() * cmp.imaginary() ) / denom,
                                ( imaginary() * cmp.real() - real() * cmp.imaginary() ) / denom );
    return quot;
  }
/*
  public Complex kick(Complex cmp) // define a non-traditional complex function
  {
    Complex sum = new Complex( Math.sin(real()*cmp.real() ) + Math.cos(imaginary() * cmp.imaginary() ),
                               real() * cmp.imaginary() + imaginary() * cmp.real() );
    return sum;
  }
*/
  
  public Complex kick(Complex cmp) // define a non-traditional complex function
  {
    Complex sum = new Complex( Math.tanh(real()*cmp.real() ) + Math.cos(imaginary() * cmp.imaginary() ),
            real() * cmp.imaginary() + imaginary() * cmp.real() );
    return sum;
  }
  
  /**
   * provides access to the real portion of the Complex number
   * @return      the real portion of the Complex number
   */
  public double real( )
  {
    return myReal;
  }

  /**
   * provides access to the imaginary portion of the Complex number
   * @return      the imaginary portion of the Complex number
   */
  public double imaginary( )
  {
    return myImaginary;
  }

  /**
   * provides access to the magnitude of the Complex number
   * @return      the magnitude of the Complex number
   */
  public double magnitude( )
  {
    return real() * real() + imaginary() * imaginary();
  }

  /**
   * provides access to the String form of the current Complex number
   * @return      the String form of the current Complex number
   */
  public String toString()
  {
    return "(" + real() + "+" + imaginary() + "i)";
  }
}