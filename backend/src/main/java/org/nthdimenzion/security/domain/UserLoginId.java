package org.nthdimenzion.security.domain;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

@ValueObject
@Embeddable
@EqualsAndHashCode
public class UserLoginId implements Serializable{

    private String uid;

    public UserLoginId(final String uid) {
        Preconditions.checkNotNull(uid);
        this.uid = uid;
    }

    UserLoginId() {
    }

    public String getUid() {
        return uid;
    }

    void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return uid;
    }


}
