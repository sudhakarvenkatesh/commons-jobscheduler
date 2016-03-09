package com.sos.auth.shiro.db;

/** \class SOSUserRightDBItem
 * 
 * \brief SOSUserRightDBItem -
 * 
 * \details
 *
 * \section SOSUserRightDBItem.java_intro_sec Introduction
 *
 * \section SOSUserRightDBItem.java_samples Some Samples
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author Uwe Risse \version 23.09.2011 \see reference
 *
 * Created on 11.12.2013 15:08:05 */

import javax.persistence.*;

import com.sos.hibernate.classes.DbItem;

@Entity
@Table(name = "SOS_USER_RIGHT")
public class SOSUserRightDBItem extends DbItem {

    private Long id;
    private Long roleId;
    private Long userId;
    private String sosUserRight;

    public SOSUserRightDBItem() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`ID`")
    public Long getId() {
        return id;
    }

    @Column(name = "`ID`")
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "`SOS_USER_RIGHT`", nullable = false)
    public void setSosUserRight(String sosUserRight) {
        this.sosUserRight = sosUserRight;
    }

    @Column(name = "`SOS_USER_RIGHT`", nullable = false)
    public String getSosUserRight() {
        return sosUserRight;
    }

    @Column(name = "`USER_ID`")
    public Long getUserId() {
        return userId;
    }

    @Column(name = "`USER_ID`")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "`ROLE_ID`")
    public Long getRoleId() {
        return roleId;
    }

    @Column(name = "`ROLE_ID`")
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
