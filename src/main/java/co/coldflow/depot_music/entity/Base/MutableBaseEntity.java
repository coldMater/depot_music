package co.coldflow.depot_music.entity.Base;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.sun.istack.Nullable;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class MutableBaseEntity extends ImmutableBaseEntity {
    @LastModifiedBy
    @Column(nullable = true)
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public String getModifiedBy() {
        return modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
