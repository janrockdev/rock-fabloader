package com.janrock.kdb;

import static org.junit.Assert.assertTrue;

import java.util.UUID;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import org.junit.Test;
import org.junit.Assert;

public class connectorTest
{
    @Test
    public void testGetNullValuesFromArray()
    {
        Assert.assertNull(connector.NULL[0]);
        Assert.assertEquals(false,connector.NULL[1]);
        Assert.assertEquals(new UUID(0,0),connector.NULL[2]);
        Assert.assertNull(connector.NULL[3]);
        Assert.assertEquals((byte) 0,connector.NULL[4]);
        Assert.assertEquals(Short.MIN_VALUE,connector.NULL[5]);
        Assert.assertEquals(Integer.MIN_VALUE,connector.NULL[6]);
        Assert.assertEquals(Long.MIN_VALUE,connector.NULL[7]);
        Assert.assertEquals((float) Double.NaN,connector.NULL[8]);
        Assert.assertEquals(Double.NaN,connector.NULL[9]);
        Assert.assertEquals(' ',connector.NULL[10]);
        Assert.assertEquals("",connector.NULL[11]);
        Assert.assertEquals(new Timestamp(Long.MIN_VALUE),connector.NULL[12]);
        Assert.assertEquals(new connector.Month(Integer.MIN_VALUE),connector.NULL[13]);
        Assert.assertEquals(new Date(Long.MIN_VALUE),connector.NULL[14]);
        Assert.assertEquals(new java.util.Date(Long.MIN_VALUE),connector.NULL[15]);
        Assert.assertEquals(new connector.Timespan(Long.MIN_VALUE),connector.NULL[16]);
        Assert.assertEquals(new connector.Minute(Integer.MIN_VALUE),connector.NULL[17]);
        Assert.assertEquals(new connector.Second(Integer.MIN_VALUE),connector.NULL[18]);
        Assert.assertEquals(new Time(Long.MIN_VALUE),connector.NULL[19]);
    }

    @Test
    public void testGetNullValues()
    {
        Assert.assertNull(connector.NULL(' '));
        Assert.assertEquals(false, connector.NULL('b'));
        Assert.assertEquals(new UUID(0,0), connector.NULL('g'));
        Assert.assertEquals((byte) 0, connector.NULL('x'));
        Assert.assertEquals(Short.MIN_VALUE, connector.NULL('h'));
        Assert.assertEquals(Integer.MIN_VALUE, connector.NULL('i'));
        Assert.assertEquals(Long.MIN_VALUE, connector.NULL('j'));
        Assert.assertEquals((float) Double.NaN, connector.NULL('e'));
        Assert.assertEquals(Double.NaN, connector.NULL('f'));
        Assert.assertEquals(' ', connector.NULL('c'));
        Assert.assertEquals("", connector.NULL('s'));
        Assert.assertEquals(new Timestamp(Long.MIN_VALUE), connector.NULL('p'));
        Assert.assertEquals(new connector.Month(Integer.MIN_VALUE), connector.NULL('m'));
        Assert.assertEquals(new Date(Long.MIN_VALUE), connector.NULL('d'));
        Assert.assertEquals(new java.util.Date(Long.MIN_VALUE), connector.NULL('z'));
        Assert.assertEquals(new connector.Timespan(Long.MIN_VALUE), connector.NULL('n'));
        Assert.assertEquals(new connector.Minute(Integer.MIN_VALUE), connector.NULL('u'));
        Assert.assertEquals(new connector.Second(Integer.MIN_VALUE), connector.NULL('v'));
        Assert.assertEquals(new Time(Long.MIN_VALUE), connector.NULL('t'));
    }

    @Test
    public void testIncorrectNullType()
    {
        try {
            connector.NULL('a');
            Assert.fail("Expected an ArrayIndexOutOfBoundsException to be thrown");
        } catch (ArrayIndexOutOfBoundsException e) {
            // do nothing
        }
    }

    @Test
    public void testValueIsNull()
    {
        assertTrue( connector.qn("") );
        Assert.assertFalse(connector.qn(" "));
        assertTrue( connector.qn(new Timestamp(Long.MIN_VALUE)));
        assertTrue( connector.qn(new connector.Month(Integer.MIN_VALUE)));
        assertTrue( connector.qn(new Date(Long.MIN_VALUE)));
        assertTrue( connector.qn(new java.util.Date(Long.MIN_VALUE)));
        assertTrue( connector.qn(new connector.Timespan(Long.MIN_VALUE)));
        assertTrue( connector.qn(new connector.Minute(Integer.MIN_VALUE)));
        assertTrue( connector.qn(new connector.Second(Integer.MIN_VALUE)));
        assertTrue( connector.qn(new Time(Long.MIN_VALUE)) );
    }

    @Test
    public void testValueIsNotNull()
    {
        Assert.assertFalse(connector.qn(" "));
        Assert.assertFalse(connector.qn(new StringBuffer()));
    }

    @Test
    public void testGetAtomType()
    {
        Assert.assertEquals(-1, connector.t(Boolean.FALSE));
        Assert.assertEquals(-2, connector.t(new UUID(0,0)));
        Assert.assertEquals(-4, connector.t(Byte.valueOf("1")));
        Assert.assertEquals(-5, connector.t(Short.valueOf("1")));
        Assert.assertEquals(-6, connector.t(1111));
        Assert.assertEquals(-7, connector.t(1111L));
        Assert.assertEquals(-8, connector.t(1.2f));
        Assert.assertEquals(-9, connector.t(1.2));
        Assert.assertEquals(-10, connector.t(' '));
        Assert.assertEquals(-11, connector.t(""));
        Assert.assertEquals(-14, connector.t(new Date(Long.MIN_VALUE)));
        Assert.assertEquals(-19, connector.t(new Time(Long.MIN_VALUE)));
        Assert.assertEquals(-12, connector.t(new Timestamp(Long.MIN_VALUE)));
        Assert.assertEquals(-15, connector.t(new java.util.Date(Long.MIN_VALUE)));
        Assert.assertEquals(-16, connector.t(new connector.Timespan(Long.MIN_VALUE)));
        Assert.assertEquals(-13, connector.t(new connector.Month(Integer.MIN_VALUE)));
        Assert.assertEquals(-17, connector.t(new connector.Minute(Integer.MIN_VALUE)));
        Assert.assertEquals(-18, connector.t(new connector.Second(Integer.MIN_VALUE)));
    }

    @Test
    public void testGetType()
    {
        Assert.assertEquals(1, connector.t(new boolean[2]));
        Assert.assertEquals(2, connector.t(new UUID[2]));
        Assert.assertEquals(4, connector.t(new byte[2]));
        Assert.assertEquals(5, connector.t(new short[2]));
        Assert.assertEquals(6, connector.t(new int[2]));
        Assert.assertEquals(7, connector.t(new long[2]));
        Assert.assertEquals(8, connector.t(new float[2]));
        Assert.assertEquals(9, connector.t(new double[2]));
        Assert.assertEquals(10, connector.t(new char[2]));
        Assert.assertEquals(11, connector.t(new String[2]));
        Assert.assertEquals(14, connector.t(new Date[2]));
        Assert.assertEquals(19, connector.t(new Time[2]));
        Assert.assertEquals(12, connector.t(new Timestamp[2]));
        Assert.assertEquals(15, connector.t(new java.util.Date[2]));
        Assert.assertEquals(16, connector.t(new connector.Timespan[2]));
        Assert.assertEquals(13, connector.t(new connector.Month[2]));
        Assert.assertEquals(17, connector.t(new connector.Minute[2]));
        Assert.assertEquals(18, connector.t(new connector.Second[2]));
        connector.Dict dict = new connector.Dict(new String[] {"Key"}, new String[][] {{"Value1","Value2","Value3"}});
        Assert.assertEquals(98, connector.t(new connector.Flip(dict)));
        Assert.assertEquals(99, connector.t(dict));
    }

    @Test
    public void testGetUnknownType()
    {
        Assert.assertEquals(0, connector.t(new StringBuffer()));
    }

    @Test
    public void testDictConstructor()
    {
        String[] x = new String[] {"Key"};
        String[][] y = new String[][] {{"Value1","Value2","Value3"}};
        connector.Dict dict = new connector.Dict(x, y);
        Assert.assertEquals(x, dict.x);
        Assert.assertEquals(y, dict.y);
    }

    @Test
    public void testFlipConstructor()
    {
        String[] x = new String[] {"Key"};
        String[][] y = new String[][] {{"Value1","Value2","Value3"}};
        connector.Dict dict = new connector.Dict(x, y);
        connector.Flip flip = new connector.Flip(dict);
        Assert.assertArrayEquals(x, flip.x);
        Assert.assertArrayEquals(y, flip.y);
    }

    @Test
    public void testFlipColumnPosition()
    {
        String[] x = new String[] {"Key"};
        String[][] y = new String[][] {{"Value1","Value2","Value3"}};
        connector.Dict dict = new connector.Dict(x, y);
        connector.Flip flip = new connector.Flip(dict);
        Assert.assertEquals(y[0], flip.at("Key"));
    }

    @Test
    public void testFlipRemoveKeyWithFlip()
    {
        try {
            String[] x = new String[] {"Key"};
            String[][] y = new String[][] {{"Value1","Value2","Value3"}};
            connector.Dict dict = new connector.Dict(x, y);
            connector.Flip flip = new connector.Flip(dict);
            connector.Flip newflip = connector.td(flip);
            Assert.assertEquals(flip, newflip);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }

        try {
            String[] x = new String[] {"Key"};
            String[][] y = new String[][] {{"Value1","Value2","Value3"}};
            connector.Dict dict = new connector.Dict(x, y);
            connector.Flip flip = new connector.Flip(dict);
            connector.Dict dictOfFlips = new connector.Dict(flip, flip);
            connector.Flip newflip = connector.td(dictOfFlips);
            Assert.assertArrayEquals(new String[] {"Key","Key"}, newflip.x);
            Assert.assertArrayEquals(new String[][] {{"Value1","Value2","Value3"},{"Value1","Value2","Value3"}}, newflip.y);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testFlipUnknownColumn()
    {
        String[] x = new String[] {"Key"};
        String[][] y = new String[][] {{"Value1","Value2","Value3"}};
        connector.Dict dict = new connector.Dict(x, y);
        connector.Flip flip = new connector.Flip(dict);
        try {
            flip.at("RUBBISH");
            Assert.fail("Expected an ArrayIndexOutOfBoundsException to be thrown");
        } catch (ArrayIndexOutOfBoundsException e) {
            // do nothing
        }
    }

    @Test
    public void testMonthToString()
    {
        connector.Month mon = new connector.Month(22);
        Assert.assertEquals("2001-11", mon.toString());
        mon = new connector.Month(Integer.MIN_VALUE);
        Assert.assertEquals("", mon.toString());
    }

    @Test
    public void testMonthEquals()
    {
        connector.Month mon1 = new connector.Month(22);
        connector.Month mon2 = new connector.Month(22);
        connector.Month mon3 = new connector.Month(1);
        Assert.assertEquals(mon1,mon1);
        Assert.assertEquals(mon1,mon2);
        Assert.assertNotEquals(mon1,mon3);
        Assert.assertNotEquals(mon1,"test");
    }

    @Test
    public void testMonthHashCode()
    {
        connector.Month mon1 = new connector.Month(22);
        connector.Month mon2 = new connector.Month(22);
        connector.Month mon3 = new connector.Month(1);
        Assert.assertEquals(mon1.hashCode(),mon1.hashCode());
        Assert.assertEquals(mon1.hashCode(),mon2.hashCode());
        Assert.assertNotEquals(mon1.hashCode(),mon3.hashCode());
    }

    @Test
    public void testMonthCompareTo()
    {
        connector.Month mon1 = new connector.Month(22);
        connector.Month mon2 = new connector.Month(22);
        connector.Month mon3 = new connector.Month(1);
        Assert.assertEquals(0,mon1.compareTo(mon1));
        Assert.assertEquals(0,mon1.compareTo(mon2));
        Assert.assertEquals(21,mon1.compareTo(mon3));
    }

    @Test
    public void testMinuteToString()
    {
        connector.Minute mon = new connector.Minute(22);
        Assert.assertEquals("00:22", mon.toString());
        mon = new connector.Minute(Integer.MIN_VALUE);
        Assert.assertEquals("", mon.toString());
    }

    @Test
    public void testMinuteEquals()
    {
        connector.Minute mon1 = new connector.Minute(22);
        connector.Minute mon2 = new connector.Minute(22);
        connector.Minute mon3 = new connector.Minute(1);
        Assert.assertEquals(mon1,mon1);
        Assert.assertEquals(mon1,mon2);
        Assert.assertNotEquals(mon1,mon3);
        Assert.assertNotEquals(mon1,"test");
    }

    @Test
    public void testMinuteHashCode()
    {
        connector.Minute mon1 = new connector.Minute(22);
        connector.Minute mon2 = new connector.Minute(22);
        connector.Minute mon3 = new connector.Minute(1);
        Assert.assertEquals(mon1.hashCode(),mon1.hashCode());
        Assert.assertEquals(mon1.hashCode(),mon2.hashCode());
        Assert.assertNotEquals(mon1.hashCode(),mon3.hashCode());
    }

    @Test
    public void testMinuteCompareTo()
    {
        connector.Minute mon1 = new connector.Minute(22);
        connector.Minute mon2 = new connector.Minute(22);
        connector.Minute mon3 = new connector.Minute(1);
        Assert.assertEquals(0,mon1.compareTo(mon1));
        Assert.assertEquals(0,mon1.compareTo(mon2));
        Assert.assertEquals(21,mon1.compareTo(mon3));
    }

    @Test
    public void testSecondToString()
    {
        connector.Second mon = new connector.Second(22);
        Assert.assertEquals("00:00:22", mon.toString());
        mon = new connector.Second(Integer.MIN_VALUE);
        Assert.assertEquals("", mon.toString());
    }

    @Test
    public void testSecondEquals()
    {
        connector.Second mon1 = new connector.Second(22);
        connector.Second mon2 = new connector.Second(22);
        connector.Second mon3 = new connector.Second(1);
        Assert.assertEquals(mon1,mon1);
        Assert.assertEquals(mon1,mon2);
        Assert.assertNotEquals(mon1,mon3);
        Assert.assertNotEquals(mon1,"test");
    }

    @Test
    public void testSecondHashCode()
    {
        connector.Second mon1 = new connector.Second(22);
        connector.Second mon2 = new connector.Second(22);
        connector.Second mon3 = new connector.Second(1);
        Assert.assertEquals(mon1.hashCode(),mon1.hashCode());
        Assert.assertEquals(mon1.hashCode(),mon2.hashCode());
        Assert.assertNotEquals(mon1.hashCode(),mon3.hashCode());
    }

    @Test
    public void testSecondCompareTo()
    {
        connector.Second mon1 = new connector.Second(22);
        connector.Second mon2 = new connector.Second(22);
        connector.Second mon3 = new connector.Second(1);
        Assert.assertEquals(0,mon1.compareTo(mon1));
        Assert.assertEquals(0,mon1.compareTo(mon2));
        Assert.assertEquals(21,mon1.compareTo(mon3));
    }

    @Test
    public void testTimespanToString()
    {
        connector.Timespan mon = new connector.Timespan(22);
        Assert.assertEquals("00:00:00.000000022", mon.toString());
        mon = new connector.Timespan(-22);
        Assert.assertEquals("-00:00:00.000000022", mon.toString());
        mon = new connector.Timespan(0);
        Assert.assertEquals("00:00:00.000000000", mon.toString());
        mon = new connector.Timespan(86400000000000L);
        Assert.assertEquals("1D00:00:00.000000000", mon.toString());
        mon = new connector.Timespan(Long.MIN_VALUE);
        Assert.assertEquals("", mon.toString());
    }

    @Test
    public void testTimespanEquals()
    {
        connector.Timespan mon1 = new connector.Timespan(22);
        connector.Timespan mon2 = new connector.Timespan(22);
        connector.Timespan mon3 = new connector.Timespan();
        Assert.assertEquals(mon1,mon1);
        Assert.assertEquals(mon1,mon2);
        Assert.assertNotEquals(mon1,mon3);
        Assert.assertNotEquals(mon1,"test");
    }

    @Test
    public void testTimespanHashCode()
    {
        connector.Timespan mon1 = new connector.Timespan(22);
        connector.Timespan mon2 = new connector.Timespan(22);
        connector.Timespan mon3 = new connector.Timespan();
        Assert.assertEquals(mon1.hashCode(),mon1.hashCode());
        Assert.assertEquals(mon1.hashCode(),mon2.hashCode());
        Assert.assertNotEquals(mon1.hashCode(),mon3.hashCode());
    }

    @Test
    public void testTimespanCompareTo()
    {
        connector.Timespan mon1 = new connector.Timespan(22);
        connector.Timespan mon2 = new connector.Timespan(22);
        connector.Timespan mon3 = new connector.Timespan(1);
        connector.Timespan mon4 = new connector.Timespan(-1);
        Assert.assertEquals(0,mon1.compareTo(mon1));
        Assert.assertEquals(0,mon1.compareTo(mon2));
        Assert.assertEquals(1,mon1.compareTo(mon3));
        Assert.assertEquals(-1,mon4.compareTo(mon1));
    }

    @Test
    public void testSerializeStringLen()
    {
        try {
            Assert.assertEquals(0,connector.ns(null));
        } catch (Exception e){
            Assert.fail(e.toString());
        }
        try {
            Assert.assertEquals(2,connector.ns("hi"));
        } catch (Exception e){
            Assert.fail(e.toString());
        }
        try {
            char[] ch = {'g', 'o', (char)0, 'd', ' ', 'm', 'o', 'r', 'n', 'i', 'n', 'g'};
            String str = new String(ch);
            Assert.assertEquals(2,connector.ns(str));
        } catch (Exception e){
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testGetObjectAtIndex(){
        String[] x = new String[] {"Key"};
        Object found = connector.at(x,0);
        Assert.assertEquals(x[0],found);
    }

    @Test
    public void testGetNullObjectAtIndex(){
        String[] x = new String[] {""};
        Object found = connector.at(x,0);
        Assert.assertEquals(null,found);
    }

    @Test
    public void testSetObjectAtIndex(){
        String[] x = new String[] {"Key"};
        connector.set(x,0,"Value");
        Assert.assertArrayEquals(new String[]{"Value"},x);
    }

    @Test
    public void testSetNullObjectAtIndex(){
        String[] x = new String[] {"Key"};
        connector.set(x,0,null);
        Assert.assertArrayEquals(new String[]{""},x);
    }

    @Test
    public void testBytesRequiredForDict(){
        connector.Dict dict = new connector.Dict(new String[] {"Key"}, new String[][] {{"Value1","Value2","Value3"}});
        connector c=new connector();
        try {
            Assert.assertEquals(44,connector.nx(dict));
        } catch (Exception e){
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testBytesRequiredForFlip(){
        connector.Dict dict = new connector.Dict(new String[] {"Key"}, new String[][] {{"Value1","Value2","Value3"}});
        connector.Flip flip = new connector.Flip(dict);
        connector c=new connector();
        try {
            Assert.assertEquals(46,connector.nx(flip));
        } catch (Exception e){
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testElementsInObject(){
        try {
            char[] ch = {'g', 'o'};
            Assert.assertEquals(2,connector.n(ch));
            int[] ints = {1,2};
            Assert.assertEquals(2,connector.n(ints));
            connector.Dict dict = new connector.Dict(new String[] {"Key"}, new String[][] {{"Value1","Value2","Value3"}});
            Assert.assertEquals(1,connector.n(dict));
            connector.Flip flip = new connector.Flip(dict);
            Assert.assertEquals(3,connector.n(flip));
        } catch (Exception e){
            Assert.fail(e.toString());
        }
    }

    class DefaultMsgHandler implements connector.MsgHandler
    {
    }

    @Test
    public void testDefaultMsgHandler(){
        DefaultMsgHandler msgHandler = new DefaultMsgHandler();
        connector c=new connector();
        try {
            msgHandler.processMsg(c,(byte)0,"test");
        } catch (Exception e){
            Assert.fail(e.toString());
        }
        try {
            msgHandler.processMsg(c,(byte)6,"test");
            Assert.fail("Expected an IOException to be thrown");
        } catch (Exception e){
            // do nothing, exception expected
        }
    }

}
