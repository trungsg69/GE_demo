package com.core.context;

import java.io.*;
import java.util.Properties;

import com.core.util.ContextualReadOnlyProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test context.
 *
 * <p>Holds global settings for tests.</p>
 */
public class TestContext {
    /** Logger. */
    private static Logger LOGGER = LoggerFactory.getLogger(TestContext.class);

    /** Property to store current test name. */
    private static final String TEST_NAME_PROPERTY = "test.name";

    /**
     * Singleton instance.
     */
    private static TestContext s_instance = new TestContext();

    /**
     * Properties file directory.
     */
    private String m_propertiesDir;

    /**
     * Properties.
     */
    private Properties m_properties = new Properties();

    /**
     * Is current test running.
     */
    private boolean m_started = false;

    /**
     * Name of the test scenario
     */
    private String m_testName;

    /** Hidden constructor. */
    protected TestContext() {
    }

    /**
     * Get single instance.
     * @return instance.
     */
    public static TestContext instance() {
        return s_instance;
    }

    /**
     * Reset test context.
     *
     * <p>Clears all data.</p>
     */
    public static void reset() {
        s_instance = new TestContext();
    }

    /**
     * Load settings from file.
     *
     * @param filename settings file name.
     */
    public void loadSettings(final String filename) throws IOException {
        LOGGER.info("Load settings from file: {}", filename);

        try (final Reader reader = new FileReader(filename)) {
            m_properties.load(reader);
        } catch (final IOException e) {
            LOGGER.error("Error loading file", e);
            throw e;
        }

        m_propertiesDir = new File(filename).getParent();
    }

    /**
     * Set properties file directory.
     * @param propertiesDir properties file directory.
     */
    protected void setPropertiesDir(final String propertiesDir) {
        m_propertiesDir = propertiesDir;
    }

    /**
     * Get directory of loaded properties file.
     * @return directory of loaded properties file.
     */
    public String getPropertiesDir() {
       return m_propertiesDir;
    }

    /**
     * Check property existence.
     * @param name property name.
     * @return {@code true}, if property defined.
     */
    public boolean hasProperty(final String name) {
        return m_properties.containsKey(name);
    }

    /**
     * Get property value.
     * @param name property name.
     * @return value of property, or {@code null} if property not present.
     */
    public String getProperty(final String name) {
        return m_properties.getProperty(name);
    }

    /**
     * Get property value.
     * @param name property name.
     * @param defaultValue default value.
     * @return value of property, or {@code null} if property not present.
     */
    public String getProperty(final String name, final String defaultValue) {
        return m_properties.getProperty(name, defaultValue);
    }

    /**
     * Get property which blank value is considered default.
     * @param name property name.
     * @param defaultValue value to return, if property is absent or blank.
     * @return property value, or {@code defaultValue} if property absent or blank.
     */
    public String getNonblankProperty(final String name, final String defaultValue) {
        final String value = getProperty(name);
        return StringUtils.isNotBlank(value) ? value : defaultValue;
    }

    /**
     * Set property value.
     * @param name property name.
     * @param value property value.
     */
    public void setProperty(final String name, final String value) {
        m_properties.setProperty(name, value);
    }

    /**
     * Open file with name stored in property.
     *
     * <p>File path is considered as being relative to the directory of loaded properties file.</p>
     * @param propertyName property name.
     * @return input stream for file.
     * @throws NoPropertyException in case of property absence.
     * @throws IOException is case of IO error.
     * @see #getPropertiesDir()
     */
    public InputStream openFile(final String propertyName) throws NoPropertyException, IOException {
        final File filename = getPathProperty(propertyName);
        if (filename == null) {
            throw new NoPropertyException(propertyName);
        }

        return new FileInputStream(filename);
    }

    /**
     * Get path stored in property.
     * @param propertyName property name.
     * @return path stored in property, or {@code null}, if property absent or empty.
     */
    public File getPathProperty(final String propertyName) {
        final String filename = m_properties.getProperty(propertyName);
        return StringUtils.isNotBlank(filename) ? new File(m_propertiesDir, filename) : null;
    }

    /**
     * Get boolean value of property.
     * @param propertyName property name.
     * @return value of property casted to boolean, or {@code null}, if property absent.
     */
    public Boolean getBooleanProperty(final String propertyName) {
        final String value = m_properties.getProperty(propertyName);
        return value != null ? Boolean.parseBoolean(value) : null;
    }

    /**
     * Get contextual read-only view of properties.
     * @return properties.
     */
    public ContextualReadOnlyProperties getProperties() {
        return new ContextualReadOnlyProperties(m_properties);
    }

    /**
     * Get name of test scenario.
     * @return name of test scenario.
     */
    public String getTestName() {
        return m_testName;
    }

    /**
     * Begin test.
     *
     * <p>Set test name attribute and notes test start.</p>
     * @param testName test name
     */
    public void beginTest(final String testName) {
        if (m_started) {
            throw new RuntimeException(
                "Trying to make test current while current test is not finished"
            );
        }
        setTestName(testName);
        m_started = true;

        LOGGER.info(
            "==================================TEST START"
                + "==================================="
        );
        LOGGER.info("Test: {}", testName);

    }

    /**
     * Note that current test has been ended.
     *
     * <p>Remove information about current test.</p>
     */
    public void endTest() {
        setTestName(null);
        m_started = false;

        LOGGER.info(getTestName());
        LOGGER.info(
            "==================================TEST FINISHED"
                + "==================================="
        );
    }

    /**
     * Set new test scenario name.
     * @param testName scenario name.
     */
    private void setTestName(final String testName) {
        m_testName = testName;
        if (testName != null) {
            m_properties.setProperty(TEST_NAME_PROPERTY, testName);
        } else {
            m_properties.remove(TEST_NAME_PROPERTY);
        }
    }
}
