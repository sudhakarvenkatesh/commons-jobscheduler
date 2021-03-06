//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3-
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2011.01.17 at 03:00:56 PM MEZ
//

package com.sos.scheduler.model.objects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/** <p>
 * Java class for environment complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="environment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="variable" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="name" use="required" type="{}String" />
 *                 &lt;attribute name="value" use="required" type="{}String" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre> */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "environment", propOrder = { "variable" })
public class Environment extends JSObjBase {

    protected List<Environment.Variable> variable;

    /** Gets the value of the variable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the variable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getVariable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Environment.Variable } */
    public List<Environment.Variable> getVariable() {
        if (variable == null) {
            variable = new ArrayList<Environment.Variable>();
        }
        return this.variable;
    }

    /** <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="name" use="required" type="{}String" />
     *       &lt;attribute name="value" use="required" type="{}String" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre> */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Variable {

        @XmlAttribute(name = "name", required = true)
        protected String name;
        @XmlAttribute(name = "value", required = true)
        protected String value;

        /** Gets the value of the name property.
         * 
         * @return possible object is {@link String } */
        public String getName() {
            return name;
        }

        /** Sets the value of the name property.
         * 
         * @param value allowed object is {@link String } */
        public void setName(String value) {
            this.name = value;
        }

        /** Gets the value of the value property.
         * 
         * @return possible object is {@link String } */
        public String getValue() {
            return value;
        }

        /** Sets the value of the value property.
         * 
         * @param value allowed object is {@link String } */
        public void setValue(String value) {
            this.value = value;
        }

    }

}
