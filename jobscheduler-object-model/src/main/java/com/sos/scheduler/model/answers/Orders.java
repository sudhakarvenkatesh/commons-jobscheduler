//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3-
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2011.01.20 at 05:15:15 PM MEZ
//

package com.sos.scheduler.model.answers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/** <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}order" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre> */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "order" })
@XmlRootElement(name = "orders")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
public class Orders implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 9153899945467384291L;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected List<Order> order;

    /** Gets the value of the order property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the order property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Order } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public List<Order> getOrder() {
        if (order == null) {
            order = new ArrayList<Order>();
        }
        return this.order;
    }

}
