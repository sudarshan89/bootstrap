package org.nthdimenzion.ddd.domain.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public final class LifeCycle {
    public static enum EntityStatus {
        ACTIVE, ARCHIVE
    }

    private String createdBy;

    private DateTime createdTxTimestamp;

    private String updatedBy;

    private DateTime updatedTxTimestamp;

    private EntityStatus entityStatus = EntityStatus.ACTIVE;

    public void markAsArchived() {
        entityStatus = EntityStatus.ARCHIVE;
    }

    public void markAsActive() {
        entityStatus = EntityStatus.ACTIVE;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getCreatedTxTimestamp() {
        return createdTxTimestamp;
    }

    public void setCreatedTxTimestamp(DateTime createdTxTimestamp) {
        this.createdTxTimestamp = createdTxTimestamp;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getUpdatedTxTimestamp() {
        return updatedTxTimestamp;
    }

    public void setUpdatedTxTimestamp(DateTime updatedTxTimestamp) {
        this.updatedTxTimestamp = updatedTxTimestamp;
    }


    @Enumerated(EnumType.ORDINAL)
    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }


}
