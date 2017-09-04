//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3-
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2011.09.26 at 11:23:43 AM MESZ
//

package com.sos.scheduler.model.answers;

import java.io.Serializable;
import java.math.BigInteger;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
 *       &lt;attribute name="absolute_repeat" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="repeat" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="begin" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="end" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="job" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="job_chain" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="order" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre> */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "period")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
public class Period implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7521427814869241614L;
    @XmlAttribute(name = "absolute_repeat")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger absoluteRepeat;
    @XmlAttribute(name = "repeat")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger repeat;
    @XmlAttribute(name = "begin", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String begin;
    @XmlAttribute(name = "end", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String end;
    @XmlAttribute(name = "job")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String job;
    @XmlAttribute(name = "job_chain")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String jobChain;
    @XmlAttribute(name = "order")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String order;

    /** Gets the value of the absoluteRepeat property.
     * 
     * @return possible object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getAbsoluteRepeat() {
        return absoluteRepeat;
    }

    /** Sets the value of the absoluteRepeat property.
     * 
     * @param value allowed object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setAbsoluteRepeat(BigInteger value) {
        this.absoluteRepeat = value;
    }

    /** Gets the value of the repeat property.
     * 
     * @return possible object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getRepeat() {
        return repeat;
    }

    /** Sets the value of the repeat property.
     * 
     * @param value allowed object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setRepeat(BigInteger value) {
        this.repeat = value;
    }

    /** Gets the value of the begin property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getBegin() {
        return begin;
    }

    /** Sets the value of the begin property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setBegin(String value) {
        this.begin = value;
    }

    /** Gets the value of the end property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getEnd() {
        return end;
    }

    /** Sets the value of the end property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setEnd(String value) {
        this.end = value;
    }

    /** Gets the value of the job property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getJob() {
        return job;
    }

    /** Sets the value of the job property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setJob(String value) {
        this.job = value;
    }

    /** Gets the value of the jobChain property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getJobChain() {
        return jobChain;
    }

    /** Sets the value of the jobChain property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setJobChain(String value) {
        this.jobChain = value;
    }

    /** Gets the value of the order property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getOrder() {
        return order;
    }

    /** Sets the value of the order property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-09-26T11:23:43+02:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setOrder(String value) {
        this.order = value;
    }

}
