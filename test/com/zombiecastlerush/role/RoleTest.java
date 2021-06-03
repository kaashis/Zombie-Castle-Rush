package com.zombiecastlerush.role;

import com.zombiecastlerush.building.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This Role test provides testing method for Role class
 * edge and normal conditions have been tested
 * TODO: when the Room class is updated, I have to update this RoleTest
 */
public class RoleTest {
    private Role role = new Role(1);
    @Before
    public void setUp() throws Exception {
        role.setCurrentPosition(new Room("1", "i am room 1"));
    }

    @Test
    public void testIncreaseHealth_returnMaxHealthValueIfTotalHealthBiggerThanMax() {
        Assert.assertEquals(role.getHealth(), 100); // current health
        role.increaseHealth(1); // change it to 101
        Assert.assertEquals(role.getHealth(), 100);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIncreaseHealth_throwExceptionIfIncreasedHealthNegative() {
        role.increaseHealth(-1); // cannot increase negative points
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDecreaseHealth_throwExceptionIfDecreasedHealthNegative() {
        role.decreaseHealth(-1); // cannot decrease negative points
    }

    @Test
    public void testDecreaseHealth_returnMinHealthIfTotalHealthLessThanMin() {
        Assert.assertEquals(role.getHealth(), 100); // current health
        role.decreaseHealth(101); // change it to -1
        Assert.assertEquals(role.getHealth(), 0);
    }

    @Test
    public void testGetId_returnCurrentId() {
        Assert.assertEquals(role.getId(), 1);
        role.setId(Integer.MAX_VALUE);
        Assert.assertEquals(role.getId(), Integer.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetId_throwExceptionIfInputNegative(){
        role.setId(-1);
    }

    @Test
    public void testGetHealth_returnPredefinedValue() {
        role.setHealth(100);
        Assert.assertEquals(role.getHealth(), 100);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetHealth_throwExceptionNegativeHealth() {
        role.setHealth(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetHealth_throwExceptionOverMaxHealth() {
        role.setHealth(101);
    }

    @Test
    public void testSetHealth_allowInputValidHealthValue() {
        role.setHealth(0);
        role.setHealth(100);
        role.setHealth(50);
        Assert.assertEquals(role.getHealth(), 50);
    }
    @Test
    public void testGetCurrentPosition_returnCurrentRoomNumber() {
        Assert.assertEquals(role.getCurrentPosition().getName(), "1");
    }

    @Test
    public void testSetCurrentPosition_returnChangedRoomNumber() {
        role.getCurrentPosition();
        Assert.assertEquals(role.getCurrentPosition().getName(), "1");
        Room roomTest = new Room("test2", "I am Room test2");
        role.setCurrentPosition(roomTest);
        Assert.assertEquals(role.getCurrentPosition().getName(), "test2");
    }

}
