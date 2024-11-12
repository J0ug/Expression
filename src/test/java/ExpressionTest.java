import org.example.Expression;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ExpressionTest {

    Expression expression;

    @Before
    public void setUp() {
        expression = new Expression();
    }

    @Test
    public void testBasicOperations() {
        assertEquals(5.0, expression.evaluate("2 + 3"), 0.001);
        assertEquals(6.0, expression.evaluate("2 * 3"), 0.001);
        assertEquals(2.0, expression.evaluate("5 - 3"), 0.001);
        assertEquals(2.5, expression.evaluate("5 / 2"), 0.001);
    }

    @Test
    public void testFunctions() {
        assertEquals(Math.sin(0), expression.evaluate("sin(0)"), 0.001);
        assertEquals(Math.cos(0), expression.evaluate("cos(0)"), 0.001);
        assertEquals(Math.sqrt(4), expression.evaluate("sqrt(4)"), 0.001);
        assertEquals(Math.log(1), expression.evaluate("log(1)"), 0.001);
        assertEquals(Math.abs(-5), expression.evaluate("abs(-5)"),0.001);
    }

    @Test
    public void testPowFunction() {
        assertEquals(8.0, expression.evaluate("pow(2 3)"), 0.001);
        assertEquals(1.0, expression.evaluate("pow(10 0)"), 0.001);
    }

    @Test
    public void testVariables() {
        expression.setVariables(Map.of("x", 2.0, "y", 3.0));
        assertEquals(5.0, expression.evaluate("x + y"), 0.001);
        assertEquals(6.0, expression.evaluate("x * y"), 0.001);
    }

    @Test
    public void testComplexExpression() {
        expression.setVariables(Map.of("x", 2.0, "y", 3.0));
        assertEquals(10.0, expression.evaluate("x * y + pow(x  2)"), 0.001); // (2 * 3) + (2^2) = 6 + 4 = 10
    }

    @Test
    public void testInvalidExpression() {
        assertThrows(IllegalArgumentException.class, () -> expression.evaluate("(2 + 3"));
        assertThrows(IllegalArgumentException.class, () -> expression.evaluate("2 + * 3"));
    }
}
