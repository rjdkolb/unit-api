/**
 * Unit-API - Units of Measurement API for Java
 * Copyright (c) 2014 Jean-Marie Dautelle, Werner Keil, V2COM
 * All rights reserved.
 *
 * See LICENSE.txt for details.
 */
package javax.measure;

import java.util.List;

/**
 * A converter of numeric values between different units.
 *
 * <p>Instances of this class are obtained through the {@link Unit#getConverterTo(Unit)} method.</p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.7.1, 2014-09-07
 *
 * @see <a href="http://en.wikipedia.org/wiki/Conversion_of_units"> Wikipedia:
 *      Conversion of units</a>
 */
public interface UnitConverter {
	
    /**
     * Indicates if this converter is an identity converter.
     * The identity converter returns its input argument ({@code convert(x) == x}).
     *
     * @return {@code true} if this converter is an identity converter.
     */
    boolean isIdentity();
    
    /**
     * Indicates if this converter is linear. A converter is linear if:
     *
     * <ul>
     *   <li>{@code convert(u + v) == convert(u) + convert(v)}</li>
     *   <li>{@code convert(r * u) == r * convert(u)}</li>
     * </ul>
     *
     * <p>For linear converters the following property always hold:</p>
     *
     * [code]
     *   y1 = c1.convert(x1);
     *   y2 = c2.convert(x2);
     *   assert y1*y2 == c1.concatenate(c2).convert(x1*x2);
     * [/code]
     *
     * @return {@code true} if this converter is linear; {@code false} otherwise.
     */
    boolean isLinear();

    /**
     * Returns the inverse of this converter. If {@code x} is a valid value,
     * then {@code x == inverse().convert(convert(x))} to within the accuracy
     * of computer arithmetic.
     *
     * @return the inverse of this converter.
     */
    UnitConverter inverse();
    
    /**
     * Converts a {@code Number} value.
     *
     * @param  value the {@code Number} value to convert.
     * @return the {@code Number} value after conversion.
     */
    Number convert(Number value);

    /**
     * Converts a {@code double} value.
     *
     * @param  value the numeric value to convert.
     * @return the {@code double} value after conversion.
     */
    double convert(double value);

    /**
     * Concatenates this converter with another converter. The resulting
     * converter is equivalent to first converting by the specified converter
     * (right converter), and then converting by this converter (left converter).
     *
     * @param  converter the other converter to concatenate with this converter.
     * @return the concatenation of this converter with the other converter.
     */
    UnitConverter concatenate(UnitConverter converter);

    /**
     * <p>Returns the steps of fundamental converters making up this converter or
     * {@code this} if the converter is a fundamental converter.</p>
     * <p>
     * For example, {@code converter1.getConversionSteps()} returns {@code {converter1} while {@code converter1.concatenate(converter2).getConversionSteps()} returns {@code {converter1, converter2}.</p>
     * @return the list of fundamental converters which concatenated make up this converter.
     */
    List<? extends UnitConverter> getConversionSteps();
}
