/**
 * Unit-API - Units of Measurement API for Java
 * Copyright (c) 2014 Jean-Marie Dautelle, Werner Keil, V2COM
 * All rights reserved.
 *
 * See LICENSE.txt for details.
 */
package javax.measure.test.quantity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.measure.quantity.Volume;
import javax.measure.test.unit.AreaUnit;
import javax.measure.test.unit.DistanceUnit;
import javax.measure.test.unit.VolumeUnit;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Werner Keil
 */
public class AreaQuantityTest {

    AreaQuantity area;
    AreaUnit m2;

    @Before
    public void setUp() {
        m2 = AreaUnit.sqmetre;
        area = new AreaQuantity(100, m2);
    }

    @Test
    public void testAreaQuantity() {
        assertNotNull(area);
    }

    @Test
    public void testAdd() {
        AreaQuantity area2 = new AreaQuantity(50, m2);
        AreaQuantity result = area.add(area2);
        assertEquals(150d, result.scalar, 0);
    }

    @Test
    public void testSubtract() {
        AreaQuantity area2 = new AreaQuantity(50, m2);
        AreaQuantity result = area.subtract(area2);
        assertEquals(50d, result.scalar, 0);
    }

    @Test
    public void testEq() {
        AreaQuantity area2 = new AreaQuantity(100, m2);
        assertTrue(area2.eq(area));
    }

    @Test
    public void testGt() {
        AreaQuantity area2 = new AreaQuantity(120, m2);
        assertTrue(area2.gt(area));
    }

    @Test
    public void testLt() {
        AreaQuantity area2 = new AreaQuantity(20, m2);
        assertTrue(area2.lt(area));
    }

    @Test
    public void testGe() {
        AreaQuantity area2 = new AreaQuantity(120, m2);
        assertTrue(area2.ge(area));
        area2 = new AreaQuantity(100, m2);
        assertTrue(area2.ge(area));
    }

    @Test
    public void testLe() {
        AreaQuantity area2 = new AreaQuantity(20, m2);
        assertTrue(area2.le(area));
        area2 = new AreaQuantity(100, m2);
        assertTrue(area2.le(area));
    }

    @Test
    public void testMultiplyDouble() {
        AreaQuantity result = area.multiply(3d);
        assertEquals(300d, result.scalar, 0d);
    }

    @Test
    public void testDivideDouble() {
        AreaQuantity result = area.divide(10d);
        assertEquals(10d, result.scalar, 0d);
    }

    @Test
    public void testDivideDistanceQuantity() {
        DistanceQuantity distance = new DistanceQuantity(10, DistanceUnit.m);
        DistanceQuantity result = area.divide(distance);
        assertEquals(10d, result.scalar, 0d);
    }

    @Test
    public void testMultiplyDistanceQuantity() {
        DistanceQuantity distance = new DistanceQuantity(15, DistanceUnit.m);
        VolumeQuantity result = area.multiply(distance);
        assertEquals(VolumeUnit.class, result.getUnit().getClass());
        assertEquals(VolumeQuantity.class, result.getClass());
        assertEquals(Volume.class, result.getType());
        assertEquals(1500d, result.getValue());
    }

    @Test
    public void testConvert() {
        AreaQuantity result = area.convert(AreaUnit.acre);
        assertEquals(100d, result.scalar, 0d);
    }

    @Test
    public void testShowInUnits() {
        String result = area.showInUnits(AreaUnit.hectare, 2);
        assertEquals("0.01 hectare", result);
    }
}
