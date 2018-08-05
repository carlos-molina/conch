package uk.ac.ncl.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test ccc and Ethereum configuration properties.
 */
class CCCPropertiesTest {

  private CCCProperties cccProperties;

  @BeforeEach
  void setup() {
    cccProperties = new CCCProperties();
    cccProperties.loadProperties();
  }

  @Test
  void loadProperties() {
    assertNotNull(cccProperties.getProperties());
    assertEquals(3, cccProperties.getProperties().size());
  }

  @Test
  void getProperties() {
    Properties properties = cccProperties.getProperties();
    assertNotNull(properties.getProperty("keyStoreFilePath"));
    assertNotNull(properties.getProperty("accountPassword"));
    assertNotNull(properties.getProperty("contractAddress"));
    assertEquals("0xAB52675ea8464963fda7c0B610D931dd87Ea4829", properties.get("contractAddress"));

  }
}