//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3-
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2011.01.20 at 04:00:28 PM MEZ
//

package com.sos.scheduler.model.answers;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/** <p>
 * Java-Klasse f�r anonymous complex type.
 * 
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}file_based"/>
 *         &lt;element ref="{}file_order_source" minOccurs="0"/>
 *         &lt;element ref="{}job_chain_node" maxOccurs="unbounded"/>
 *         &lt;element name="order_history" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="order">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{}file_based"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="created" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="end_time" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="history_id" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="initial_state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="job_chain" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="order" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="start_time" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="orders" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="orders_recoverable" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="running_orders" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="state" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre> */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "fileBased", "fileOrderSource", "jobChainNode", "orderHistory" })
@XmlRootElement(name = "job_chain")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
public class JobChain implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4095836781745471174L;
    @XmlElement(name = "file_based", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected FileBased fileBased;
    @XmlElement(name = "file_order_source")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected FileOrderSource fileOrderSource;
    @XmlElement(name = "job_chain_node", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected List<JobChainNode> jobChainNode;
    @XmlElement(name = "order_history")
    protected JobChain.OrderHistory orderHistory;
    @XmlAttribute(name = "name", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String name;
    @XmlAttribute(name = "orders", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger orders;
    @XmlAttribute(name = "orders_recoverable", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String ordersRecoverable;
    @XmlAttribute(name = "path", required = true)
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String path;
    @XmlAttribute(name = "running_orders", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger runningOrders;
    @XmlAttribute(name = "state", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String state;
    @XmlAttribute(name = "title")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String title;

    /** Gets the value of the fileBased property.
     * 
     * @return possible object is {@link FileBased } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public FileBased getFileBased() {
        return fileBased;
    }

    /** Sets the value of the fileBased property.
     * 
     * @param value allowed object is {@link FileBased } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setFileBased(FileBased value) {
        this.fileBased = value;
    }

    /** Gets the value of the fileOrderSource property.
     * 
     * @return possible object is {@link FileOrderSource } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public FileOrderSource getFileOrderSource() {
        return fileOrderSource;
    }

    /** Sets the value of the fileOrderSource property.
     * 
     * @param value allowed object is {@link FileOrderSource } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setFileOrderSource(FileOrderSource value) {
        this.fileOrderSource = value;
    }

    /** Gets the value of the jobChainNode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the jobChainNode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getJobChainNode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JobChainNode } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public List<JobChainNode> getJobChainNode() {
        if (jobChainNode == null) {
            jobChainNode = new ArrayList<JobChainNode>();
        }
        return this.jobChainNode;
    }

    /** @return possible object is {@link JobChain.OrderHistory } */
    public JobChain.OrderHistory getOrderHistory() {
        return orderHistory;
    }

    /** @param value allowed object is {@link JobChain.OrderHistory } */
    public void setOrderHistory(JobChain.OrderHistory value) {
        this.orderHistory = value;
    }

    /** Gets the value of the name property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getName() {
        return name;
    }

    /** Sets the value of the name property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setName(String value) {
        this.name = value;
    }

    /** Gets the value of the orders property.
     * 
     * @return possible object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getOrders() {
        return orders;
    }

    /** Sets the value of the orders property.
     * 
     * @param value allowed object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setOrders(BigInteger value) {
        this.orders = value;
    }

    /** Gets the value of the ordersRecoverable property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getOrdersRecoverable() {
        return ordersRecoverable;
    }

    /** Sets the value of the ordersRecoverable property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setOrdersRecoverable(String value) {
        this.ordersRecoverable = value;
    }

    /** Gets the value of the path property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getPath() {
        return path;
    }

    /** Sets the value of the path property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setPath(String value) {
        this.path = value;
    }

    /** Gets the value of the runningOrders property.
     * 
     * @return possible object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getRunningOrders() {
        return runningOrders;
    }

    /** Sets the value of the runningOrders property.
     * 
     * @param value allowed object is {@link BigInteger } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setRunningOrders(BigInteger value) {
        this.runningOrders = value;
    }

    /** Gets the value of the state property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getState() {
        return state;
    }

    /** Sets the value of the state property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setState(String value) {
        this.state = value;
    }

    /** Gets the value of the title property.
     * 
     * @return possible object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getTitle() {
        return title;
    }

    /** Sets the value of the title property.
     * 
     * @param value allowed object is {@link String } */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setTitle(String value) {
        this.title = value;
    }

    /** <p>
     * Java-Klasse f�r anonymous complex type.
     * 
     * <p>
     * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
     * Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="order">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{}file_based"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="created" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="end_time" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="history_id" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="initial_state" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="job_chain" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="order" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="start_time" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    @XmlType(name = "", propOrder = { "order" })
    public static class OrderHistory {

        protected List<JobChain.OrderHistory.Order> order;

        /** Gets the value of the order property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
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
         * Objects of the following type(s) are allowed in the list
         * {@link JobChain.OrderHistory.Order } */
        public List<JobChain.OrderHistory.Order> getOrder() {
            if (order == null) {
                order = new ArrayList<JobChain.OrderHistory.Order>();
            }
            return this.order;
        }

        /** <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element ref="{}file_based"/>
         *       &lt;/sequence>
         *       &lt;attribute name="created" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="end_time" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="history_id" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="initial_state" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="job_chain" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="order" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="start_time" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre> */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "fileBased" })
        public static class Order {

            @XmlElement(name = "file_based", required = true)
            protected FileBased fileBased;
            @XmlAttribute(name = "created")
            protected String created;
            @XmlAttribute(name = "end_time")
            protected String endTime;
            @XmlAttribute(name = "history_id")
            protected BigInteger historyId;
            @XmlAttribute(name = "id")
            protected String id;
            @XmlAttribute(name = "initial_state")
            protected String initialState;
            @XmlAttribute(name = "job_chain")
            protected String jobChain;
            @XmlAttribute(name = "order")
            protected String order;
            @XmlAttribute(name = "path")
            protected String path;
            @XmlAttribute(name = "priority")
            protected String priority;
            @XmlAttribute(name = "start_time")
            protected String startTime;
            @XmlAttribute(name = "state")
            protected String state;

            /** @return possible object is {@link FileBased } */
            public FileBased getFileBased() {
                return fileBased;
            }

            /** @param value allowed object is {@link FileBased } */
            public void setFileBased(FileBased value) {
                this.fileBased = value;
            }

            /** @return possible object is {@link String } */
            public String getCreated() {
                return created;
            }

            /** @param value allowed object is {@link String } */
            public void setCreated(String value) {
                this.created = value;
            }

            /** @return possible object is {@link String } */
            public String getEndTime() {
                return endTime;
            }

            /** @param value allowed object is {@link String } */
            public void setEndTime(String value) {
                this.endTime = value;
            }

            /** @return possible object is {@link BigInteger } */
            public BigInteger getHistoryId() {
                return historyId;
            }

            /** @param value allowed object is {@link BigInteger } */
            public void setHistoryId(BigInteger value) {
                this.historyId = value;
            }

            /** @return possible object is {@link String } */
            public String getId() {
                return id;
            }

            /** @param value allowed object is {@link String } */
            public void setId(String value) {
                this.id = value;
            }

            /** @return possible object is {@link String } */
            public String getInitialState() {
                return initialState;
            }

            /** @param value allowed object is {@link String } */
            public void setInitialState(String value) {
                this.initialState = value;
            }

            /** @return possible object is {@link String } */
            public String getJobChain() {
                return jobChain;
            }

            /** @param value allowed object is {@link String } */
            public void setJobChain(String value) {
                this.jobChain = value;
            }

            /** @return possible object is {@link String } */
            public String getOrder() {
                return order;
            }

            /** @param value allowed object is {@link String } */
            public void setOrder(String value) {
                this.order = value;
            }

            /** @return possible object is {@link String } */
            public String getPath() {
                return path;
            }

            /** @param value allowed object is {@link String } */
            public void setPath(String value) {
                this.path = value;
            }

            /** @return possible object is {@link String } */
            public String getPriority() {
                return priority;
            }

            /** @param value allowed object is {@link String } */
            public void setPriority(String value) {
                this.priority = value;
            }

            /** @return possible object is {@link String } */
            public String getStartTime() {
                return startTime;
            }

            /** @param value allowed object is {@link String } */
            public void setStartTime(String value) {
                this.startTime = value;
            }

            /** @return possible object is {@link String } */
            public String getState() {
                return state;
            }

            /** @param value allowed object is {@link String } */
            public void setState(String value) {
                this.state = value;
            }

        }

    }

}
