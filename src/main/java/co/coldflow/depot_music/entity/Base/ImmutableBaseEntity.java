package co.coldflow.depot_music.entity.Base;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ImmutableBaseEntity {
    @Id @GeneratedValue
    private long id;

    @CreatedBy
    private long createdBy;

    @CreatedDate
    private long createdDate;

    public long getId() {
        return id;
    }
}
