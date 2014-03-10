/*
 * header file
 */
package org.nthdimenzion.ddd.domain.model;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@ValueObject
@Immutable
@EqualsAndHashCode(exclude = "completionComments")
public final class Interval implements Serializable {

    private static final long serialVersionUID = 1L;

    private DateTime fromDate;

    private DateTime thruDate;

    private String completionComments;

    public static Interval start() {
        return new Interval();
    }


    public Interval() {
        fromDate = DateTime.now();
    }

    public Interval(DateTime fromDate, DateTime thruDate) {
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    public Interval(DateTime fromDate, DateTime thruDate, String completionComments) {
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.completionComments = completionComments;
    }

    public Interval(DateTime fromDate) {
        this.fromDate = fromDate;
    }


    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @NotNull
    public DateTime getFromDate() {
        return fromDate;
    }

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getThruDate() {
        return thruDate;
    }

    void setCompletionComments(String completionComments) {
        this.completionComments = completionComments;
    }

    void setFromDate(DateTime fromDate) {
        this.fromDate = fromDate;
    }

    void setThruDate(DateTime thruDate) {
        this.thruDate = thruDate;
    }

    @Transient
    public boolean isCompleted() {
        return thruDate != null && thruDate.isBeforeNow();
    }

    public Interval complete(String comments) {
        return new Interval(fromDate, DateTime.now(), comments);
    }

    public Interval complete() {
        return complete("No Remarks");
    }

    @Transient
    public boolean isInProgress() {
        return !isCompleted();
    }

    public Interval extendThruDateByDays(int days) {
        return new Interval(fromDate, thruDate.plusDays(days));
    }


}
