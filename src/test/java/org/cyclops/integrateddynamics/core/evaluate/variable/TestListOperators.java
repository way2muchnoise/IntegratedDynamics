package org.cyclops.integrateddynamics.core.evaluate.variable;

import org.cyclops.integrateddynamics.api.evaluate.EvaluationException;
import org.cyclops.integrateddynamics.api.evaluate.variable.IValue;
import org.cyclops.integrateddynamics.api.evaluate.variable.IVariable;
import org.cyclops.integrateddynamics.core.evaluate.operator.Operators;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test the different integer operators.
 * @author rubensworks
 */
public class TestListOperators {

    private static final DummyValueType DUMMY_TYPE = DummyValueType.TYPE;
    private static final DummyVariable<DummyValueType.DummyValue> DUMMY_VARIABLE =
            new DummyVariable<DummyValueType.DummyValue>(DUMMY_TYPE, DummyValueType.DummyValue.of());

    private DummyVariableList labc;
    private DummyVariableList lintegers;
    private DummyVariableList lempty;

    private DummyVariableInteger i0;
    private DummyVariableInteger i1;
    private DummyVariableInteger i2;
    private DummyVariableInteger i3;
    private DummyVariableInteger i4;

    private DummyVariableOperator oRelationalEquals;

    @BeforeClass
    public static void beforeClass() {
        ValueTypeListProxyFactories.load();
    }

    @Before
    public void before() {
        i0 = new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(0));
        i1 = new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(1));
        i2 = new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(2));
        i3 = new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(3));
        i4 = new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(4));

        oRelationalEquals = new DummyVariableOperator(ValueTypeOperator.ValueOperator.of(Operators.RELATIONAL_EQUALS));

        labc = new DummyVariableList(ValueTypeList.ValueList.ofAll(
                ValueTypeString.ValueString.of("a"),
                ValueTypeString.ValueString.of("b"),
                ValueTypeString.ValueString.of("c")
        ));
        lintegers = new DummyVariableList(ValueTypeList.ValueList.ofAll(i0.getValue(), i1.getValue(), i2.getValue(), i3.getValue()));
        lempty = new DummyVariableList(ValueTypeList.ValueList.ofAll());
    }

    /**
     * ----------------------------------- LENGTH -----------------------------------
     */

    @Test
    public void testListLength() throws EvaluationException {
        IValue res1 = Operators.LIST_LENGTH.evaluate(new IVariable[]{labc});
        assertThat("result is an integer", res1, instanceOf(ValueTypeInteger.ValueInteger.class));
        assertThat("len(abc) = 3", ((ValueTypeInteger.ValueInteger) res1).getRawValue(), is(3));
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeLengthLarge() throws EvaluationException {
        Operators.LIST_LENGTH.evaluate(new IVariable[]{labc, labc});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeLengthSmall() throws EvaluationException {
        Operators.LIST_LENGTH.evaluate(new IVariable[]{});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputTypeLength() throws EvaluationException {
        Operators.LIST_LENGTH.evaluate(new IVariable[]{DUMMY_VARIABLE});
    }

    /**
     * ----------------------------------- EMPTY -----------------------------------
     */

    @Test
    public void testListEmpty() throws EvaluationException {
        IValue res1 = Operators.LIST_EMPTY.evaluate(new IVariable[]{labc});
        assertThat("result is an boolean", res1, instanceOf(ValueTypeBoolean.ValueBoolean.class));
        assertThat("empty(abc) = false", ((ValueTypeBoolean.ValueBoolean) res1).getRawValue(), is(false));

        IValue res2 = Operators.LIST_EMPTY.evaluate(new IVariable[]{lempty});
        assertThat("empty(empty) = true", ((ValueTypeBoolean.ValueBoolean) res2).getRawValue(), is(true));
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeEmptyLarge() throws EvaluationException {
        Operators.LIST_EMPTY.evaluate(new IVariable[]{labc, labc});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeEmptySmall() throws EvaluationException {
        Operators.LIST_EMPTY.evaluate(new IVariable[]{});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputTypeEmpty() throws EvaluationException {
        Operators.LIST_EMPTY.evaluate(new IVariable[]{DUMMY_VARIABLE});
    }

    /**
     * ----------------------------------- NOT_EMPTY -----------------------------------
     */

    @Test
    public void testListNotEmpty() throws EvaluationException {
        IValue res1 = Operators.LIST_NOT_EMPTY.evaluate(new IVariable[]{labc});
        assertThat("result is an boolean", res1, instanceOf(ValueTypeBoolean.ValueBoolean.class));
        assertThat("empty(abc) = false", ((ValueTypeBoolean.ValueBoolean) res1).getRawValue(), is(true));

        IValue res2 = Operators.LIST_NOT_EMPTY.evaluate(new IVariable[]{lempty});
        assertThat("empty(empty) = true", ((ValueTypeBoolean.ValueBoolean) res2).getRawValue(), is(false));
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeNotEmptyLarge() throws EvaluationException {
        Operators.LIST_NOT_EMPTY.evaluate(new IVariable[]{labc, labc});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeNotEmptySmall() throws EvaluationException {
        Operators.LIST_NOT_EMPTY.evaluate(new IVariable[]{});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputTypeNotEmpty() throws EvaluationException {
        Operators.LIST_NOT_EMPTY.evaluate(new IVariable[]{DUMMY_VARIABLE});
    }

    /**
     * ----------------------------------- GET -----------------------------------
     */

    @Test
    public void testListElement() throws EvaluationException {
        IValue res1 = Operators.LIST_ELEMENT.evaluate(new IVariable[]{labc, new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(0))});
        assertThat("result is a string", res1, instanceOf(ValueTypeString.ValueString.class));
        assertThat("get(abc, 0) = 'a'", ((ValueTypeString.ValueString) res1).getRawValue(), is("a"));

        IValue res2 = Operators.LIST_ELEMENT.evaluate(new IVariable[]{labc, new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(1))});
        assertThat("result is a string", res2, instanceOf(ValueTypeString.ValueString.class));
        assertThat("get(abc, 1) = 'b'", ((ValueTypeString.ValueString) res2).getRawValue(), is("b"));

        IValue res3 = Operators.LIST_ELEMENT.evaluate(new IVariable[]{labc, new DummyVariableInteger(ValueTypeInteger.ValueInteger.of(2))});
        assertThat("result is a string", res3, instanceOf(ValueTypeString.ValueString.class));
        assertThat("get(abc, 2) = 'c'", ((ValueTypeString.ValueString) res3).getRawValue(), is("c"));
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeElementLarge() throws EvaluationException {
        Operators.LIST_ELEMENT.evaluate(new IVariable[]{labc, labc, labc});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeElementSmall() throws EvaluationException {
        Operators.LIST_ELEMENT.evaluate(new IVariable[]{labc});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputTypeElement() throws EvaluationException {
        Operators.LIST_ELEMENT.evaluate(new IVariable[]{DUMMY_VARIABLE, DUMMY_VARIABLE});
    }

    /**
     * ----------------------------------- CONTAINS_PREDICATE -----------------------------------
     */

    @Test
    public void testListContainsPredicate() throws EvaluationException {
        DummyVariableOperator equalsTwo = new DummyVariableOperator((ValueTypeOperator.ValueOperator)
                Operators.OPERATOR_APPLY.evaluate(new IVariable[]{oRelationalEquals, i2}));

        IValue res1 = Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{lintegers, equalsTwo, i0});
        assertThat("result is a boolean", res1, instanceOf(ValueTypeBoolean.ValueBoolean.class));
        assertThat("contains([0, 1, 2, 3], 2==, 0) = false", ((ValueTypeBoolean.ValueBoolean) res1).getRawValue(), is(false));

        IValue res2 = Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{lintegers, equalsTwo, i1});
        assertThat("contains([0, 1, 2, 3], 2==, 1) = false", ((ValueTypeBoolean.ValueBoolean) res2).getRawValue(), is(false));

        IValue res3 = Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{lintegers, equalsTwo, i2});
        assertThat("contains([0, 1, 2, 3], 2==, 2) = true", ((ValueTypeBoolean.ValueBoolean) res3).getRawValue(), is(true));

        IValue res4 = Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{lintegers, equalsTwo, i3});
        assertThat("contains([0, 1, 2, 3], 2==, 3) = false", ((ValueTypeBoolean.ValueBoolean) res4).getRawValue(), is(false));

        IValue res5 = Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{lintegers, equalsTwo, i4});
        assertThat("contains([0, 1, 2, 3],2 ==, 4) = false", ((ValueTypeBoolean.ValueBoolean) res5).getRawValue(), is(false));
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeContainsPredicateLarge() throws EvaluationException {
        Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{lintegers, oRelationalEquals, i2, i0});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeContainsPredicateSmall() throws EvaluationException {
        Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{lintegers, oRelationalEquals});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputTypeContainsPredicate() throws EvaluationException {
        Operators.LIST_CONTAINS_PREDICATE.evaluate(new IVariable[]{DUMMY_VARIABLE, DUMMY_VARIABLE, DUMMY_VARIABLE});
    }

    /**
     * ----------------------------------- CONTAINS -----------------------------------
     */

    @Test
    public void testListContains() throws EvaluationException {
        IValue res1 = Operators.LIST_CONTAINS.evaluate(new IVariable[]{lintegers, i0});
        assertThat("result is a boolean", res1, instanceOf(ValueTypeBoolean.ValueBoolean.class));
        assertThat("contains([0, 1, 2, 3], 0) = true", ((ValueTypeBoolean.ValueBoolean) res1).getRawValue(), is(true));

        IValue res2 = Operators.LIST_CONTAINS.evaluate(new IVariable[]{lintegers, i1});
        assertThat("contains([0, 1, 2, 3, 1) = true", ((ValueTypeBoolean.ValueBoolean) res2).getRawValue(), is(true));

        IValue res3 = Operators.LIST_CONTAINS.evaluate(new IVariable[]{lintegers, i2});
        assertThat("contains([0, 1, 2, 3], 2) = true", ((ValueTypeBoolean.ValueBoolean) res3).getRawValue(), is(true));

        IValue res4 = Operators.LIST_CONTAINS.evaluate(new IVariable[]{lintegers, i3});
        assertThat("contains([0, 1, 2, 3], 3) = true", ((ValueTypeBoolean.ValueBoolean) res4).getRawValue(), is(true));

        IValue res5 = Operators.LIST_CONTAINS.evaluate(new IVariable[]{lintegers, i4});
        assertThat("contains([0, 1, 2, 3], 4) = false", ((ValueTypeBoolean.ValueBoolean) res5).getRawValue(), is(false));
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeContainsLarge() throws EvaluationException {
        Operators.LIST_CONTAINS.evaluate(new IVariable[]{lintegers, i2, i0});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputSizeContainsSmall() throws EvaluationException {
        Operators.LIST_CONTAINS.evaluate(new IVariable[]{lintegers});
    }

    @Test(expected = EvaluationException.class)
    public void testInvalidInputTypeContains() throws EvaluationException {
        Operators.LIST_CONTAINS.evaluate(new IVariable[]{DUMMY_VARIABLE, DUMMY_VARIABLE});
    }

}
