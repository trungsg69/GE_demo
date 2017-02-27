package com.core.util;

import com.core.context.NoPropertyException;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Contextual read only properties.
 *
 * <h2>Context</h2>
 *
 * <p>Context is considered as possible prefix of property name. Non-empty prefix is separated from
 * requested name by dot. If properties context aren't strict and prefixed property name is absent,
 * then initial property name is tried.</p>
 *
 * <h2>Multiproperty</h2>
 *
 * <p>Multiproperty have several values being defined in form "${propertyName}.N" where N belongs
 * to the continuous range of integers starting from 1. Note that rules of contextual lookup is
 * applied to each value independently. This means that given properties 'p.1', 'p.2' and
 * 'ctx.p.2' and context set to 'ctx', multiproperty 'p' would have values of 'p.1' and 'ctx.p.2'.
 * </p>
 *
 */
public class ContextualReadOnlyProperties {

    /** Default context. */
    private static final String DEFAULT_CONTEXT = "";

    /** Properties. */
    protected final Properties m_properties;

    /** Current context. */
    private String m_context = DEFAULT_CONTEXT;

    /** Is strict lookup. */
    private boolean m_isStrict;

    /** Default constructor. */
    public ContextualReadOnlyProperties() {
        this(new Properties());
    }

    /**
     * Create contextual properties.
     * @param properties another create contextual properties.
     */
    public ContextualReadOnlyProperties(final ContextualReadOnlyProperties properties) {
        this(properties.m_properties);
    }

    /**
     * Constructor.
     * @param properties Properties
     */
    public ContextualReadOnlyProperties(final Properties properties) {
        m_properties = properties;
    }

    /**
     * Select context prefix.
     * @param context a new context prefix.
     */
    public void setContext(String context) {
        m_context = context;
        m_isStrict = false;
    }

    /**
     * Select strict context prefix.
     * @param context a new context prefix.
     */
    public void setStrictContext(String context) {
        m_context = context;
        m_isStrict = true;
    }

    /**
     * Reset context.
     */
    public void resetContext() {
        setContext(DEFAULT_CONTEXT);
    }

    /**
     * Returns property value.
     * <p>Tries to get property in current context. If fails and context isn't strict, then tries
     * property without context. If fails, returns default value.</p>.
     * @param propertyName Property name
     * @param defaultValue Default value
     * @return trimmed property value, or default value if property lookup is failed or ignored.
     */
    public String getProperty(final String propertyName, final String defaultValue) {
        String result = null;
        if (!m_context.isEmpty()) {
            result = m_properties.getProperty(propertyName(propertyName));
        }
        if (result == null) {
            result = lookupInDefaultContext(propertyName, defaultValue);
        }
        return (StringUtils.isNotBlank(result)) ? result.trim() : result;
    }

    /**
     * Returns property value.
     * <p>Acts as {@link #getProperty(String, String)} but returns {@code null} instead as
     * default.</p>
     * @param propertyName property name.
     * @return property value, or {@code null} if property lookup is failed or ignored.
     */
    public String getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    /**
     * Checks for property presence.
     * @param property property name.
     * @return {@code true} iff property is contained in properties respecting current context.
     */
    public boolean hasProperty(final String property) {
        boolean result = false;
        if (!m_context.isEmpty()) {
            result = m_properties.containsKey(propertyName(property));
        }

        return result || (!m_isStrict && m_properties.containsKey(property));
    }

    /**
     * Return multiproperty.
     *
     * @param propertyName multiproperty base name.
     * @return array of multiproperty values in order of definition, or empty array if property is
     *   absent.
     */
    public String[] getMultiproperty(String propertyName) {
        List<String> result = new ArrayList<>();

        int i = 1;
        while (true) {
            final String propertyAltName = propertyName + "." + i;
            String alternativeDocNumber = getProperty(propertyAltName);
            if (StringUtils.isNotBlank(alternativeDocNumber)) {
                result.add(alternativeDocNumber);
            } else {
                return result.toArray(new String[result.size()]);
            }
            ++i;
        }
    }

    /**
     * Get resolved property name.
     * @param elementName Name of element.
     * @throws NoPropertyException on missed property error.
     * @return resolved name of element.
     */
    public String resolveProperty(final String elementName) throws NoPropertyException {
        if (StringUtils.isEmpty(m_context)) {
            if (m_properties.containsKey(elementName)) {
                return elementName;
            } else {
                throw new NoPropertyException(
                    "Property " + elementName + "is not found for the empty context"
                );
            }
        } else {
            String contextElementName = propertyName(elementName);
            if (m_properties.containsKey(contextElementName)) {
                return contextElementName;
            } else if (!m_isStrict && m_properties.containsKey(elementName)) {
                return elementName;
            } else {
                throw new NoPropertyException(
                    "Property "
                        + elementName
                        + "is not found for "
                        + ((m_isStrict) ? "strict " : DEFAULT_CONTEXT)
                        + "context '"
                        + m_context
                        + "'"
                );
            }
        }
    }

    /**
     * Get property name with context.
     * @param elementName name of element.
     * @return contextual name of element.
     */
    private String propertyName(String elementName) {
        if (m_context == null || m_context.isEmpty()) {
            throw new IllegalStateException("Properties context is not set.");
        }
        return m_context + "." + elementName;
    }

    /**
     * Lookup property in default context for non-strict context.
     * @param propertyName Property name
     * @param defaultValue Default value
     * @return property from default context if it isn't strict context and default context property
     *   was founded, otherwise return default value.
     */
    private String lookupInDefaultContext(final String propertyName, final String defaultValue) {
        return !m_isStrict ? m_properties.getProperty(propertyName, defaultValue) : defaultValue;
    }
}
