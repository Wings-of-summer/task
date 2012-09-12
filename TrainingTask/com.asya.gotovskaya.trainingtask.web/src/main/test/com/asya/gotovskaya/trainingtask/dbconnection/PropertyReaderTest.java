package com.asya.gotovskaya.trainingtask.dbconnection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;

/**
 * @author asya
 */
public class PropertyReaderTest {

    @Test
    public void testLoadResources() {
        Map<String, String> properties = PropertyReader.getDatabaseProperties();

        Assert.assertThat(properties.isEmpty(), is(false));
        Assert.assertThat(properties.size(), is(2));
    }
}
