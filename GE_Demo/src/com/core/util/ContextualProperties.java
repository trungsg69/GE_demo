package com.core.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Contextual properties.
 *
 * <p>Context is considered as possible prefix of property name. Non-empty prefix is separated from
 * requested name by dot. If prefixed property name is absent, then initial property name is tried.
 * If property not exists then property name itself is returned.</p>
 *
 * @see com.core.selenium.page.AbstractPage
 *
 */
public class ContextualProperties extends ContextualReadOnlyProperties {

    /** Default constructor. */
    public ContextualProperties() {
        super();
    }

    /**
     * Constructor.
     * @param properties properties objects.
     */
    public ContextualProperties(final Properties properties) {
        super(properties);
    }

    /**
     * Add new properties.
     * <p>Existing properties are overwritten.</p>
     * @param newProperties new properties.
     */
    public void addProperties(Properties newProperties) {
        for (String property : newProperties.stringPropertyNames()) {
            m_properties.setProperty(property, newProperties.getProperty(property));
        }
    }

    /**
     * Set property value (regardless the context).
     * @param key full property name.
     * @param value value.
     */
    public void setProperty(final String key, final String value) {
        m_properties.setProperty(key, value);
    }

    /**
     * Remove property regardless the context.
     * @param key full property name.
     */
    public void remove(final String key) {
        m_properties.remove(key);
    }

    /**
     * Load properties from reader.
     * @param propertiesReader Properties reader
     * @throws IOException on i/o error.
     */
    public void load(final Reader propertiesReader) throws IOException {
        m_properties.load(propertiesReader);
    }

}
