package co.coldflow.depot_music.entity;

import co.coldflow.depot_music.entity.Base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity {
    private String authorName;
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name="report_id")
    private Report report;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
