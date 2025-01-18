package com.sierra.skyTeam;
import com.sierra.skyTeam.model.Field;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.view.FieldView;
import com.sierra.skyTeam.model.Dice;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



public class FieldTest {

    private Field field;
    private FieldModel fieldModel;
    private FieldView mockFieldView;
    private Dice mockDice;

    @Before
    public void setUp() {
        field = new Field("TestField");
        fieldModel = new FieldModel(false, false, true, new int[]{1, 2});
        mockFieldView = mock(FieldView.class);
        mockDice = mock(Dice.class);
    }

    @Test
    public void testFieldInitialState() {
        assertFalse(field.isOccupied());
        assertEquals(-1, field.getDiceValue());
        assertFalse(field.isSwitchedOn());
        assertNull(field.getOwner());
    }

    @Test
    public void testFieldPlaceDice() {
        boolean placed = field.placeDice(2);
        assertTrue(placed);
        assertTrue(field.isOccupied());
        assertEquals(2, field.getDiceValue());
    }

    @Test
    public void testFieldReset() {
        field.placeDice(3);
        field.resetField();
        assertFalse(field.isOccupied());
        assertEquals(-1, field.getDiceValue());
    }

    @Test
    public void testFieldSwitchOn() {
        field.setSwitchOn();
        assertTrue(field.isSwitchedOn());
    }

    @Test
    public void testFieldModelInitialState() {
        assertFalse(fieldModel.isOccupied());
        assertFalse(fieldModel.isDiceProcessed());
        assertFalse(fieldModel.getIsSwitchOn());
    }

    @Test
    public void testFieldModelPlaceDice() {
        when(mockFieldView.getBounds()).thenReturn(new com.badlogic.gdx.math.Rectangle(0, 0, 100, 100));
        when(mockDice.getDiceSprite()).thenReturn(mock(com.badlogic.gdx.graphics.g2d.Sprite.class));

        boolean result = fieldModel.placeDice(mockDice, false, mockFieldView);
        assertTrue(result);
        assertTrue(fieldModel.isOccupied());
        assertTrue(fieldModel.getIsSwitchOn());
    }

    @Test
    public void testFieldModelRemoveDice() {
        when(mockFieldView.getBounds()).thenReturn(new com.badlogic.gdx.math.Rectangle(0, 0, 100, 100));
        when(mockDice.getDiceSprite()).thenReturn(mock(com.badlogic.gdx.graphics.g2d.Sprite.class));
        fieldModel.placeDice(mockDice, false, mockFieldView);

        fieldModel.removeDice();

        assertFalse(fieldModel.isOccupied());
        assertNull(fieldModel.getPlacedDice());
    }

    @Test
    public void testFieldModelDiceProcessed() {
        fieldModel.setDiceProcessed(true);
        assertTrue(fieldModel.isDiceProcessed());
    }
}